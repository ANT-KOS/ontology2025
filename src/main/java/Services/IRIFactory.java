package Services;

import Constants.OntologyClass;
import Constants.OntologyIdentity;
import Constants.Predicate;
import Constants.SurveyColumn;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.model.util.ModelBuilder;
import org.eclipse.rdf4j.model.vocabulary.OWL;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.RDFS;

import java.util.Map;
import java.util.Objects;

public class IRIFactory {
    private static final ValueFactory vf = SimpleValueFactory.getInstance();

    public static void checkIRI(ModelBuilder modelBuilder, Map.Entry<SurveyColumn, String> column, IRI fromSubject) {
        Predicate requiredPredicate = Predicate.fromColumn(column.getKey());
        switch (Objects.requireNonNull(Predicate.fromColumn(column.getKey()).requiredClass())) {
            case OntologyClass.COUNTRY -> createIRI(modelBuilder, column, fromSubject, requiredPredicate, OntologyClass.COUNTRY);
            case OntologyClass.PROGRAMMING_LANGUAGE -> createIRI(modelBuilder, column, fromSubject, requiredPredicate, OntologyClass.PROGRAMMING_LANGUAGE);
            case OntologyClass.DATABASE -> createIRI(modelBuilder, column, fromSubject, requiredPredicate, OntologyClass.DATABASE);
            case OntologyClass.PLATFORM -> createIRI(modelBuilder, column, fromSubject, requiredPredicate, OntologyClass.PLATFORM);
            case OntologyClass.WEB_FRAMEWORK -> createIRI(modelBuilder, column, fromSubject, requiredPredicate, OntologyClass.WEB_FRAMEWORK);
            case OntologyClass.AI_SEARCH -> createIRI(modelBuilder, column, fromSubject, requiredPredicate, OntologyClass.AI_SEARCH);
            case OntologyClass.COLLAB_TOOL ->  createIRI(modelBuilder, column, fromSubject, requiredPredicate, OntologyClass.COLLAB_TOOL);
            case OntologyClass.EMBEDDED -> createIRI(modelBuilder, column, fromSubject, requiredPredicate, OntologyClass.EMBEDDED);
            case OntologyClass.FRAMEWORK -> createIRI(modelBuilder, column, fromSubject, requiredPredicate, OntologyClass.FRAMEWORK);
            case OntologyClass.OFFICE_STACK_ASYNC -> createIRI(modelBuilder, column, fromSubject, requiredPredicate, OntologyClass.OFFICE_STACK_ASYNC);
            case OntologyClass.OFFICE_STACK_SYNC -> createIRI(modelBuilder, column, fromSubject, requiredPredicate, OntologyClass.OFFICE_STACK_SYNC);
            case OntologyClass.OPERATING_SYSTEM -> createIRI(modelBuilder, column, fromSubject, requiredPredicate, OntologyClass.OPERATING_SYSTEM);
            case OntologyClass.TOOLS_TECH -> createIRI(modelBuilder, column, fromSubject, requiredPredicate, OntologyClass.TOOLS_TECH);
        }

        modelBuilder.subject(fromSubject);
    }

    private static void createIRI(
            ModelBuilder modelBuilder,
            Map.Entry<SurveyColumn, String> column,
            IRI fromSubject,
            Predicate requiredPredicate,
            OntologyClass ontologyClass
    ) {
        String[] columnValues = column.getValue().split(";");

        for (String columnValue : columnValues) {
            IRI iriToCheck = vf.createIRI(
                    OntologyIdentity.NAMESPACE.getValue()
                            + ontologyClass.getClassName()
                            + "/"
                            + columnValue);

            modelBuilder.subject(iriToCheck)
                    .add(RDF.TYPE, ontologyClass.getClassName(OntologyIdentity.PREFIX.getValue()));
            for (Predicate predicate : ontologyClass.getPredicatesByOntologyClass()) {
                modelBuilder.add(predicate.getLocalNameWithPrefix(OntologyIdentity.PREFIX.getValue()), columnValue);
            }

            modelBuilder.subject(fromSubject)
                    .add(OntologyIdentity.NAMESPACE.getValue() + requiredPredicate.getLocalName() + "/", iriToCheck);

            IRI predicateProperty = SimpleValueFactory.getInstance().createIRI(OntologyIdentity.NAMESPACE.getValue() + requiredPredicate.getLocalName() + "/");
            modelBuilder.subject(predicateProperty)
                    .add(RDF.TYPE, OWL.OBJECTPROPERTY)
                    .add(RDFS.DOMAIN, SimpleValueFactory
                            .getInstance()
                            .createIRI(OntologyIdentity.NAMESPACE.getValue() + OntologyClass.DEVELOPER.getClassName()))
                    .add(RDFS.RANGE, SimpleValueFactory
                            .getInstance()
                            .createIRI(OntologyIdentity.NAMESPACE.getValue() + ontologyClass.getClassName()))
                    .add(RDFS.LABEL, requiredPredicate.getLocalName());
        }
    }
}
