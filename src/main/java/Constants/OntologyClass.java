package Constants;

public enum OntologyClass {
    DEVELOPER("developer"),
    COUNTRY("country"),
    PROGRAMMING_LANGUAGE("programmingLanguage"),
    DATABASE("database"),
    PLATFORM("platform"),
    WEB_FRAMEWORK("webFramework"),
    EMBEDDED("embedded"),
    TOOLS_TECH("toolsTech"),
    COLLAB_TOOL("collabTools"),
    OFFICE_STACK_ASYNC("officeStackAsync"),
    OFFICE_STACK_SYNC("officeStackSync"),
    AI_SEARCH("aiSearch"),
    OPERATING_SYSTEM("operatingSystem");

    private final String className;

    OntologyClass(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}
