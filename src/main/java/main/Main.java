package main;

import Constants.OntologyClass;
import Constants.OntologyIdentity;
import Constants.Predicate;
import Constants.SurveyColumn;
import Services.IRIFactory;
import com.opencsv.CSVReader;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.model.impl.TreeModel;
import org.eclipse.rdf4j.model.util.ModelBuilder;
import org.eclipse.rdf4j.model.util.Models;
import org.eclipse.rdf4j.model.vocabulary.OWL;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.RDFS;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.config.RepositoryConfig;
import org.eclipse.rdf4j.repository.config.RepositoryConfigSchema;
import org.eclipse.rdf4j.repository.manager.RemoteRepositoryManager;
import org.eclipse.rdf4j.repository.manager.RepositoryManager;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFParser;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.rio.helpers.StatementCollector;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.Map;
import java.util.Scanner;

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

        Scanner scanner = new Scanner(System.in);
        System.out.println("Model Saved ! Do you want to save it to file or upload it in the local GraphDB server ?");
        System.out.println("1. Upload to GraphDB");
        System.out.println("2. Save to file");
        System.out.print("Enter choice (1 or 2): ");

        String choice = scanner.nextLine().trim().toLowerCase();

        switch (choice) {
            case "1":
                uploadToGraphDB(modelBuilder);
                break;
            case "2":
                saveToFile(modelBuilder);
                break;
            default:
                System.out.println("Invalid choice. Please enter 1 or 2.");
        }
        scanner.close();
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

    private static void uploadToGraphDB(ModelBuilder modelBuilder) throws IOException {
        Path path = Paths.get(".").toAbsolutePath().normalize();
        String graphDBRepoConfig = path.toFile().getAbsolutePath() + "/src/main/resources/repoConfig.ttl";
        String strServerUrl = "http://localhost:7200";

        RepositoryManager repositoryManager = new RemoteRepositoryManager(strServerUrl);
        repositoryManager.init();
        repositoryManager.getAllRepositories();

        TreeModel graph = new TreeModel();

        InputStream config = new FileInputStream(graphDBRepoConfig);
        RDFParser rdfParser = Rio.createParser(RDFFormat.TURTLE);
        rdfParser.setRDFHandler(new StatementCollector(graph));
        rdfParser.parse(config, RepositoryConfigSchema.NAMESPACE);
        config.close();

        Resource repositoryNode =  Models.subject(graph
                        .filter(null, RDF.TYPE, RepositoryConfigSchema.REPOSITORY))
                .orElseThrow(() -> new RuntimeException(
                        "Oops, no <http://www.openrdf.org/config/repository#> subject found!"));


        RepositoryConfig repositoryConfig = RepositoryConfig.create(graph, repositoryNode);
        repositoryManager.addRepositoryConfig(repositoryConfig);

        Repository repository = repositoryManager.getRepository("miniproject");

        try (OutputStream outputStream = new FileOutputStream(path.toFile().getAbsolutePath() + "/src/main/resources/output.ttl")) {
            Rio.write(modelBuilder.build(), outputStream, RDFFormat.TURTLE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (RepositoryConnection con = repository.getConnection()) {
            con.add((new FileInputStream(path.toFile().getAbsolutePath() + "/src/main/resources/output.ttl")), RDFFormat.TURTLE);
            System.out.println("✅ RDF model successfully uploaded to GraphDB.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static void saveToFile(ModelBuilder modelBuilder) {
        Path path = Paths.get(".").toAbsolutePath().normalize();
        try (OutputStream outputStream = new FileOutputStream(path.toFile().getAbsolutePath() + "/src/main/resources/output.ttl")) {
            Rio.write(modelBuilder.build(), outputStream, RDFFormat.TURTLE);
            System.out.println("✅ RDF written to output.ttl using OutputStream.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}