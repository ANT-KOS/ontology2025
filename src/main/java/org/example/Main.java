package org.example;

import Constants.DeveloperPredicate;
import Constants.SurveyColumn;
import com.opencsv.CSVReader;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.model.util.ModelBuilder;
import org.eclipse.rdf4j.model.vocabulary.OWL;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.RDFS;
import org.eclipse.rdf4j.rio.*;

import java.io.*;
import java.util.*;

public class Main {
    private static final String NS = "http://example.org/";
    private static final String PREFIX = "ex";

    public static void main(String[] args) throws Exception {

        String filename = args[0];

        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.setNamespace(PREFIX, NS);

        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            String[] headers = reader.readNext();
            String[] row;

            while ((row = reader.readNext()) != null) {
                Map<SurveyColumn, String> surveyRow = new EnumMap<>(SurveyColumn.class);

                for (int i = 0; i < headers.length && i < row.length; i++) {
                    String header = headers[i];
                    String value = row[i];

                    for (SurveyColumn column : SurveyColumn.values()) {
                        if (column.getFieldName().equals(header) && !value.equals("NA")) {
                            surveyRow.put(column, value);
                            break;
                        }
                    }
                }

                IRI dev = SimpleValueFactory.getInstance().createIRI(NS + "dev/" + surveyRow.get(SurveyColumn.RESPONSE_ID));
                modelBuilder.subject(dev).add(RDF.TYPE, "ex:Developer");
                for (Map.Entry<SurveyColumn, String> entry : surveyRow.entrySet()) {
                    DeveloperPredicate predicate = DeveloperPredicate.fromColumn(entry.getKey());
                    if (predicate != null) {
                        if (predicate.column() == SurveyColumn.COUNTRY) {
                            modelBuilder.add(predicate.getPredicate(PREFIX), checkCountryIRI(modelBuilder, entry.getValue(), dev));
                        } else {
                            modelBuilder.add(predicate.getPredicate(PREFIX), entry.getValue());
                        }
                    }
                }
            }
        }

        /*
        TEST ADD RULE
        IRI test = SimpleValueFactory.getInstance().createIRI(NS + "test");
                        modelBuilder.subject(test)
                                .add(RDF.TYPE, RDF.PROPERTY)           // declare hasCountry as rdf:Property
                                .add(RDFS.DOMAIN, SimpleValueFactory.getInstance().createIRI(NS + "testDomain/"))           // domain Developer
                                .add(RDFS.RANGE, SimpleValueFactory.getInstance().createIRI(NS + "testRange/"))
                                .subject(dev);
         */

        try (OutputStream outputStream = new FileOutputStream("output.ttl")) {
            Rio.write(modelBuilder.build(), outputStream, RDFFormat.TURTLE);
            System.out.println("RDF written to output.ttl using OutputStream.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static IRI checkCountryIRI(ModelBuilder modelBuilder, String country, IRI subject) {
        ValueFactory vf = SimpleValueFactory.getInstance();
        IRI countryIRI = vf.createIRI(NS + "country/" + country);

        if (!modelBuilder.build().contains(countryIRI, null, null)) {
            modelBuilder.subject(countryIRI)
                    .add(RDF.TYPE, "ex:Country")
                    .add("ex:countryName", country)
                    .add(OWL.SAMEAS, vf.createIRI("http://www.wikidata.org/entity/" + "Q30"));
            modelBuilder.subject(subject);
        }

        return countryIRI;
    }
}