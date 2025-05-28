package Constants;

import java.util.ArrayList;

public enum OntologyClass {
    DEVELOPER("Developer"),
    COUNTRY("Country"),
    PROGRAMMING_LANGUAGE("ProgrammingLanguage"),
    DATABASE("Database"),
    PLATFORM("Platform"),
    WEB_FRAMEWORK("WebFramework"),
    EMBEDDED("Embedded"),
    TOOLS_TECH("ToolsTech"),
    COLLAB_TOOL("CollabTools"),
    OFFICE_STACK_ASYNC("OfficeStackAsync"),
    OFFICE_STACK_SYNC("OfficeStackSync"),
    AI_SEARCH("AiSearch"),
    OPERATING_SYSTEM("OperatingSystem"),
    FRAMEWORK("Framework");

    private final String className;

    OntologyClass(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public String getClassName(String prefix) {
        return prefix + ":" + className;
    }

    public ArrayList<Predicate> getPredicatesByOntologyClass()
    {
        ArrayList<Predicate> predicates = new ArrayList<>();
        switch (this) {
            case COUNTRY:
            {
                predicates.add(Predicate.COUNTRY_NAME);
            }
            break;
            case PROGRAMMING_LANGUAGE:
            {
                predicates.add(Predicate.PROGRAMMING_LANGUAGE_NAME);
            }
            break;
            case DATABASE:
            {
                predicates.add(Predicate.DATABASE_NAME);
            }
            break;
            case PLATFORM:
            {
                predicates.add(Predicate.PLATFORM_NAME);
            }
            break;
            case WEB_FRAMEWORK:
            {
                predicates.add(Predicate.WEB_FRAMEWORK_NAME);
            }
            break;
            case EMBEDDED:
            {
                predicates.add(Predicate.EMBEDDED_NAME);
            }
            break;
            case TOOLS_TECH:
            {
                predicates.add(Predicate.TOOLS_TECH_NAME);
            }
            break;
            case COLLAB_TOOL:
            {
                predicates.add(Predicate.COLLAB_TOOLS_NAME);
            }
            break;
            case OFFICE_STACK_ASYNC:
            {
                predicates.add(Predicate.OFFICE_ASYNC_NAME);
            }
            break;
            case OFFICE_STACK_SYNC:
            {
                predicates.add(Predicate.OFFICE_SYNC_NAME);
            }
            break;
            case AI_SEARCH:
            {
                predicates.add(Predicate.AI_SEARCH_NAME);
            }
            break;
            case OPERATING_SYSTEM:
            {
                predicates.add(Predicate.OPSYS_NAME);
            }
            break;
            case FRAMEWORK:
            {
                predicates.add(Predicate.FRAMEWORK_NAME);
            }
            break;
            default:
                break;
        }
        return predicates;
    }
}
