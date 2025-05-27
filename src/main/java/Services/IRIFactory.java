package Services;

import Constants.*;
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
        switch (Objects.requireNonNull(Predicate.fromColumn(column.getKey()).requiredClass())) {
            case OntologyClass.COUNTRY -> checkCountryIRI(modelBuilder, column, fromSubject);
            case OntologyClass.PROGRAMMING_LANGUAGE -> createProgrammingLanguageIRI(modelBuilder, column, fromSubject);
        }

        modelBuilder.subject(fromSubject);
    }

    private static void checkCountryIRI(ModelBuilder modelBuilder, Map.Entry<SurveyColumn, String> column, IRI fromSubject) {
        IRI iriToCheck = vf.createIRI(
                OntologyIdentity.NAMESPACE.getValue()
                        + Objects.requireNonNull(Predicate.fromColumn(column.getKey()).requiredClass()).getClassName()
                        + "/"
                        + column.getValue());

        CountryData countryData = CountryData.fromCountryName(column.getValue());

        Predicate requiredPredicate = Predicate.fromColumn(column.getKey());
        modelBuilder.subject(iriToCheck)
                .add(RDF.TYPE, requiredPredicate.getLocalNameWithPrefix(OntologyIdentity.PREFIX.getValue()))
                .add(Predicate.COUNTRY_NAME.getLocalNameWithPrefix(OntologyIdentity.PREFIX.getValue()), countryData.getCountryName())
                .add(OWL.SAMEAS, vf.createIRI("https://www.wikidata.org/entity/" + countryData.getWikidataCode()));

        modelBuilder.subject(fromSubject)
                .add(requiredPredicate.getLocalNameWithPrefix(OntologyIdentity.PREFIX.getValue()), iriToCheck);

        IRI predicateProperty = SimpleValueFactory.getInstance().createIRI(OntologyIdentity.NAMESPACE.getValue() + requiredPredicate.getLocalName() + "/");
        modelBuilder.subject(predicateProperty)
                .add(RDF.TYPE, RDF.PROPERTY)
                .add(RDFS.DOMAIN, SimpleValueFactory
                        .getInstance()
                        .createIRI(OntologyIdentity.NAMESPACE.getValue() + OntologyClass.DEVELOPER.getClassName()))
                .add(RDFS.RANGE, SimpleValueFactory
                        .getInstance()
                        .createIRI(OntologyIdentity.NAMESPACE.getValue() + OntologyClass.COUNTRY.getClassName()));
    }

    private static void createProgrammingLanguageIRI(ModelBuilder modelBuilder, Map.Entry<SurveyColumn, String> column, IRI fromSubject) {
        String[] programmingLanguages = column.getValue().split(";");

        for (String programmingLanguage : programmingLanguages) {
            IRI iriToCheck = vf.createIRI(
                    OntologyIdentity.NAMESPACE.getValue()
                            + Objects.requireNonNull(Predicate.fromColumn(column.getKey()).requiredClass()).getClassName()
                            + "/"
                            + programmingLanguage);

            Predicate requiredPredicate = Predicate.fromColumn(column.getKey());
            modelBuilder.subject(iriToCheck)
                    .add(RDF.TYPE, requiredPredicate.getLocalNameWithPrefix(OntologyIdentity.PREFIX.getValue()))
                    .add(Predicate.PROGRAMMING_LANGUAGE_NAME.getLocalNameWithPrefix(OntologyIdentity.PREFIX.getValue()), programmingLanguage);

            modelBuilder.subject(fromSubject)
                    .add(requiredPredicate.getLocalNameWithPrefix(OntologyIdentity.PREFIX.getValue()), iriToCheck);

            IRI predicateProperty = SimpleValueFactory.getInstance().createIRI(OntologyIdentity.NAMESPACE.getValue() + requiredPredicate.getLocalName() + "/");
            modelBuilder.subject(predicateProperty)
                    .add(RDF.TYPE, RDF.PROPERTY)
                    .add(RDFS.DOMAIN, SimpleValueFactory
                            .getInstance()
                            .createIRI(OntologyIdentity.NAMESPACE.getValue() + OntologyClass.DEVELOPER.getClassName()))
                    .add(RDFS.RANGE, SimpleValueFactory
                            .getInstance()
                            .createIRI(OntologyIdentity.NAMESPACE.getValue() + OntologyClass.PROGRAMMING_LANGUAGE.getClassName()));
        }
    }
}
