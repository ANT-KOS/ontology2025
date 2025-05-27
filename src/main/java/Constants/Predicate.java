package Constants;

public enum Predicate {
    HAS_MAIN_BRANCH("hasMainBranch", SurveyColumn.MAIN_BRANCH),
    AGE("age", SurveyColumn.AGE),
    COMPENSATION("compensation", SurveyColumn.COMP_TOTAL),
    DEV_TYPE("devType", SurveyColumn.DEV_TYPE),
    HAS_COUNTRY("hasCountry", SurveyColumn.COUNTRY),
    HAS_EDUCATION_LEVEL("hasEducationLevel", SurveyColumn.ED_LEVEL),
    HAS_EMPLOYMENT_TYPE("hasEmploymentType", SurveyColumn.EMPLOYMENT),
    HAS_JOB_SATISFACTION("hasJobSatisfaction", SurveyColumn.JOB_SAT),
    INDUSTRY("industry", SurveyColumn.INDUSTRY),
    ORG_SIZE("orgSize", SurveyColumn.ORG_SIZE),
    REMOTE_WORK("remoteWork", SurveyColumn.REMOTE_WORK),
    USES_CURRENCY("usesCurrency", SurveyColumn.CURRENCY),
    WORK_EXPERIENCE("workExperience", SurveyColumn.WORK_EXP),
    YEARS_AS_PRO("yearsAsPro", SurveyColumn.YEARS_CODE_PRO),
    YEARS_OF_CODING("yearsOfCoding", SurveyColumn.YEARS_CODE),
    LEARNED_CODING_ONLINE("learnedCodingOnline", SurveyColumn.LEARN_CODE_ONLINE),
    USES_TECH_DOC("usesTechDoc", SurveyColumn.TECH_DOC),
    LANG_USED("languagesUsed", SurveyColumn.LANGUAGE_HAVE_WORKED_WITH),
    LANG_WANT("languagesWanted", SurveyColumn.LANGUAGE_WANT_TO_WORK_WITH),
    LANG_ADMIRED("languagesAdmired", SurveyColumn.LANGUAGE_ADMIRED),
    DB_USED("databasesUsed", SurveyColumn.DATABASE_HAVE_WORKED_WITH),
    DB_WANT("databasesWanted", SurveyColumn.DATABASE_WANT_TO_WORK_WITH),
    DB_ADMIRED("databasesAdmired", SurveyColumn.DATABASE_ADMIRED),
    PLATFORM_USED("platformsUsed", SurveyColumn.PLATFORM_HAVE_WORKED_WITH),
    PLATFORM_WANT("platformsWanted", SurveyColumn.PLATFORM_WANT_TO_WORK_WITH),
    PLATFORM_ADMIRED("platformsAdmired", SurveyColumn.PLATFORM_ADMIRED),
    WEBFRAME_USED("webFrameworksUsed", SurveyColumn.WEBFRAME_HAVE_WORKED_WITH),
    WEBFRAME_WANT("webFrameworksWanted", SurveyColumn.WEBFRAME_WANT_TO_WORK_WITH),
    WEBFRAME_ADMIRED("webFrameworksAdmired", SurveyColumn.WEBFRAME_ADMIRED),
    EMBEDDED_USED("embeddedUsed", SurveyColumn.EMBEDDED_HAVE_WORKED_WITH),
    EMBEDDED_WANT("embeddedWanted", SurveyColumn.EMBEDDED_WANT_TO_WORK_WITH),
    EMBEDDED_ADMIRED("embeddedAdmired", SurveyColumn.EMBEDDED_ADMIRED),
    TOOLS_TECH_USED("toolsTechUsed", SurveyColumn.TOOLS_TECH_HAVE_WORKED_WITH),
    TOOLS_TECH_WANT("toolsTechWanted", SurveyColumn.TOOLS_TECH_WANT_TO_WORK_WITH),
    TOOLS_TECH_ADMIRED("toolsTechAdmired", SurveyColumn.TOOLS_TECH_ADMIRED),
    COLLAB_TOOLS_USED("collabToolsUsed", SurveyColumn.NEW_COLLAB_TOOLS_HAVE_WORKED_WITH),
    COLLAB_TOOLS_WANT("collabToolsWanted", SurveyColumn.NEW_COLLAB_TOOLS_WANT_TO_WORK_WITH),
    COLLAB_TOOLS_ADMIRED("collabToolsAdmired", SurveyColumn.NEW_COLLAB_TOOLS_ADMIRED),
    OFFICE_ASYNC_USED("officeAsyncUsed", SurveyColumn.OFFICE_STACK_ASYNC_HAVE_WORKED_WITH),
    OFFICE_ASYNC_WANT("officeAsyncWanted", SurveyColumn.OFFICE_STACK_ASYNC_WANT_TO_WORK_WITH),
    OFFICE_ASYNC_ADMIRED("officeAsyncAdmired", SurveyColumn.OFFICE_STACK_ASYNC_ADMIRED),
    OFFICE_SYNC_USED("officeSyncUsed", SurveyColumn.OFFICE_STACK_SYNC_HAVE_WORKED_WITH),
    OFFICE_SYNC_WANT("officeSyncWanted", SurveyColumn.OFFICE_STACK_SYNC_WANT_TO_WORK_WITH),
    OFFICE_SYNC_ADMIRED("officeSyncAdmired", SurveyColumn.OFFICE_STACK_SYNC_ADMIRED),
    AI_SEARCH_USED("aiSearchUsed", SurveyColumn.AI_SEARCH_DEV_HAVE_WORKED_WITH),
    AI_SEARCH_WANT("aiSearchWanted", SurveyColumn.AI_SEARCH_DEV_WANT_TO_WORK_WITH),
    AI_SEARCH_ADMIRED("aiSearchAdmired", SurveyColumn.AI_SEARCH_DEV_ADMIRED),
    OPSYS_PERSONAL("opSysPersonal", SurveyColumn.OPSYS_PERSONAL_USE),
    OPSYS_PROFESSIONAL("opSysProfessional", SurveyColumn.OPSYS_PROFESSIONAL_USE),
    COUNTRY_NAME("countryName", null),
    PROGRAMMING_LANGUAGE_NAME("programmingLanguageName", null);


    private final String localName;
    private final SurveyColumn column;

    Predicate(String localName, SurveyColumn column) {
        this.localName = localName;
        this.column = column;
    }

    public SurveyColumn column() {
        return column;
    }

    public String getLocalName() {
        return localName;
    }

    public String getLocalNameWithPrefix(String prefix) {
        return prefix + ":" + localName;
    }

    public static Predicate fromColumn(SurveyColumn column) {
        for (Predicate predicate : values()) {
            if (predicate.column == column) {
                return predicate;
            }
        }
        throw new IllegalArgumentException("No enum constant for column: " + column);
    }

    public OntologyClass requiredClass() {
        return switch (this) {
            case HAS_COUNTRY -> OntologyClass.COUNTRY;
            case LANG_ADMIRED,
                 LANG_USED,
                 LANG_WANT -> OntologyClass.PROGRAMMING_LANGUAGE;
            case DB_ADMIRED,
                 DB_USED,
                 DB_WANT -> OntologyClass.DATABASE;
            case TOOLS_TECH_USED,
                 TOOLS_TECH_WANT,
                 TOOLS_TECH_ADMIRED -> OntologyClass.TOOLS_TECH;
            case COLLAB_TOOLS_USED,
                 COLLAB_TOOLS_WANT,
                 COLLAB_TOOLS_ADMIRED -> OntologyClass.COLLAB_TOOL;
            case OFFICE_ASYNC_USED,
                 OFFICE_ASYNC_WANT,
                 OFFICE_ASYNC_ADMIRED -> OntologyClass.OFFICE_STACK_ASYNC;
            case OFFICE_SYNC_USED,
                 OFFICE_SYNC_WANT,
                 OFFICE_SYNC_ADMIRED -> OntologyClass.OFFICE_STACK_SYNC;
            case AI_SEARCH_USED,
                 AI_SEARCH_WANT,
                 AI_SEARCH_ADMIRED -> OntologyClass.AI_SEARCH;
            case OPSYS_PERSONAL,
                 OPSYS_PROFESSIONAL -> OntologyClass.OPERATING_SYSTEM;
            case PLATFORM_ADMIRED,
                 PLATFORM_USED,
                 PLATFORM_WANT -> OntologyClass.PLATFORM;
            case WEBFRAME_ADMIRED,
                 WEBFRAME_USED,
                 WEBFRAME_WANT -> OntologyClass.WEB_FRAMEWORK;
            default -> null;
        };
    }
}
