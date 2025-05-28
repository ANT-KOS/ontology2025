package main;

import Constants.OntologyClass;
import Constants.OntologyIdentity;
import Constants.Predicate;
import Constants.SurveyColumn;
import Services.IRIFactory;
import com.opencsv.CSVReader;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.model.util.ModelBuilder;
import org.eclipse.rdf4j.model.vocabulary.OWL;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.RDFS;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.util.EnumMap;
import java.util.Map;

public class Main {
    private static final ValueFactory vf = SimpleValueFactory.getInstance();

    public static void main(String[] args) throws Exception {

        String filename = args[0];

        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.setNamespace(OntologyIdentity.PREFIX.getValue(), OntologyIdentity.NAMESPACE.getValue());
        modelBuilder.setNamespace(OntologyIdentity.RDF_PREFIX.getValue(), OntologyIdentity.RDF_NAMESPACE.getValue());
        modelBuilder.setNamespace(OntologyIdentity.RDFS_PREFIX.getValue(), OntologyIdentity.RDFS_NAMESPACE.getValue());
        modelBuilder.setNamespace(OntologyIdentity.OWL_PREFIX.getValue(), OntologyIdentity.OWL_NAMESPACE.getValue());
        modelBuilder.setNamespace(OntologyIdentity.XSD_PREFIX.getValue(), OntologyIdentity.XSD_NAMESPACE.getValue());

        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            String[] headers = reader.readNext();
            String[] row;

            while ((row = reader.readNext()) != null) {
                Map<SurveyColumn, String> surveyRow = new EnumMap<>(SurveyColumn.class);

                for (int i = 0; i < headers.length && i < row.length; i++) {
                    String header = headers[i];
                    String value = row[i];

                    for (SurveyColumn column : SurveyColumn.values()) {
                        if (column.getFieldName().equals(header) && !value.equals("NA") && !value.isEmpty()) {
                            surveyRow.put(column, value);
                            break;
                        }
                    }
                }

                IRI dev = SimpleValueFactory.getInstance().createIRI(OntologyIdentity.NAMESPACE.getValue() + OntologyClass.DEVELOPER.getClassName() + "/" + surveyRow.get(SurveyColumn.RESPONSE_ID));
                surveyRow.remove(SurveyColumn.RESPONSE_ID);

                modelBuilder.subject(dev).add(RDF.TYPE, "ex:Developer");
                for (Map.Entry<SurveyColumn, String> surveyColumn : surveyRow.entrySet()) {
                    Predicate predicate = Predicate.fromColumn(surveyColumn.getKey());
                    OntologyClass requiredClass = predicate.requiredClass();
                    if (requiredClass != null) {
                        IRIFactory.checkIRI(modelBuilder, surveyColumn, dev);
                    } else {
                        modelBuilder
                                .add(
                                vf.createIRI(OntologyIdentity.NAMESPACE.getValue(), surveyColumn.getKey().getFieldName()),
                                predicate.needsLiteral()
                                        ? vf.createLiteral(surveyColumn.getValue(), predicate.getXSD())
                                        : surveyColumn.getValue()
                        );

                        addDataProperties(modelBuilder, dev, predicate);
                    }
                }
            }
        }

        try (OutputStream outputStream = new FileOutputStream("output.ttl")) {
            Rio.write(modelBuilder.build(), outputStream, RDFFormat.TURTLE);
            System.out.println("RDF written to output.ttl using OutputStream.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addDataProperties(ModelBuilder modelBuilder, IRI fromSubject, Predicate predicate)
    {
        modelBuilder.subject(vf.createIRI(OntologyIdentity.NAMESPACE.getValue(), predicate.getSurveyColumn().getFieldName()))
                .add(RDF.TYPE, OWL.DATATYPEPROPERTY)
                .add(RDFS.DOMAIN, SimpleValueFactory
                        .getInstance()
                        .createIRI(OntologyIdentity.NAMESPACE.getValue() + OntologyClass.DEVELOPER.getClassName()))
                .add(RDFS.RANGE, predicate.getXSD())
                .add(RDFS.LABEL, predicate.getSurveyColumn().getFieldName());

        modelBuilder.subject(fromSubject);
    }
}