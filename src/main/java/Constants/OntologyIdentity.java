package Constants;

public enum OntologyIdentity {
    NAMESPACE("http://example.org/"),
    PREFIX("ex"),
    RDF_NAMESPACE("http://www.w3.org/1999/02/22-rdf-syntax-ns#"),
    RDF_PREFIX("rdf"),
    RDFS_NAMESPACE("http://www.w3.org/2000/01/rdf-schema#"),
    RDFS_PREFIX("rdfs"),
    OWL_NAMESPACE("http://www.w3.org/2002/07/owl#"),
    OWL_PREFIX("owl");

    private final String value;

    OntologyIdentity(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
