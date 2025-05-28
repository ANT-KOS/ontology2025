package Constants;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.vocabulary.XSD;

public enum Predicate {
    HAS_MAIN_BRANCH("hasMainBranch", SurveyColumn.MAIN_BRANCH, false),
    AGE("age", SurveyColumn.AGE, false),
    COMPENSATION("compensation", SurveyColumn.COMP_TOTAL, true),
    DEV_TYPE("devType", SurveyColumn.DEV_TYPE, false),
    HAS_COUNTRY("hasCountry", SurveyColumn.COUNTRY, false),
    HAS_EDUCATION_LEVEL("hasEducationLevel", SurveyColumn.ED_LEVEL, false),
    HAS_EMPLOYMENT_TYPE("hasEmploymentType", SurveyColumn.EMPLOYMENT, false),
    HAS_JOB_SATISFACTION("hasJobSatisfaction", SurveyColumn.JOB_SAT, true),
    INDUSTRY("industry", SurveyColumn.INDUSTRY, false),
    ORG_SIZE("orgSize", SurveyColumn.ORG_SIZE, false),
    REMOTE_WORK("remoteWork", SurveyColumn.REMOTE_WORK, false),
    USES_CURRENCY("usesCurrency", SurveyColumn.CURRENCY, false),
    WORK_EXPERIENCE("workExperience", SurveyColumn.WORK_EXP, true),
    YEARS_AS_PRO("yearsAsPro", SurveyColumn.YEARS_CODE_PRO, true),
    YEARS_OF_CODING("yearsOfCoding", SurveyColumn.YEARS_CODE, true),
    LEARNED_CODING_ONLINE("learnedCodingOnline", SurveyColumn.LEARN_CODE_ONLINE, false),
    USES_TECH_DOC("usesTechDoc", SurveyColumn.TECH_DOC, false),
    LANG_USED("languagesUsed", SurveyColumn.LANGUAGE_HAVE_WORKED_WITH, false),
    LANG_WANT("languagesWanted", SurveyColumn.LANGUAGE_WANT_TO_WORK_WITH, false),
    LANG_ADMIRED("languagesAdmired", SurveyColumn.LANGUAGE_ADMIRED, false),
    DB_USED("databasesUsed", SurveyColumn.DATABASE_HAVE_WORKED_WITH, false),
    DB_WANT("databasesWanted", SurveyColumn.DATABASE_WANT_TO_WORK_WITH, false),
    DB_ADMIRED("databasesAdmired", SurveyColumn.DATABASE_ADMIRED, false),
    PLATFORM_USED("platformsUsed", SurveyColumn.PLATFORM_HAVE_WORKED_WITH, false),
    PLATFORM_WANT("platformsWanted", SurveyColumn.PLATFORM_WANT_TO_WORK_WITH, false),
    PLATFORM_ADMIRED("platformsAdmired", SurveyColumn.PLATFORM_ADMIRED, false),
    WEBFRAME_USED("webFrameworksUsed", SurveyColumn.WEBFRAME_HAVE_WORKED_WITH, false),
    WEBFRAME_WANT("webFrameworksWanted", SurveyColumn.WEBFRAME_WANT_TO_WORK_WITH, false),
    WEBFRAME_ADMIRED("webFrameworksAdmired", SurveyColumn.WEBFRAME_ADMIRED, false),
    EMBEDDED_USED("embeddedUsed", SurveyColumn.EMBEDDED_HAVE_WORKED_WITH, false),
    EMBEDDED_WANT("embeddedWanted", SurveyColumn.EMBEDDED_WANT_TO_WORK_WITH, false),
    EMBEDDED_ADMIRED("embeddedAdmired", SurveyColumn.EMBEDDED_ADMIRED, false),
    TOOLS_TECH_USED("toolsTechUsed", SurveyColumn.TOOLS_TECH_HAVE_WORKED_WITH, false),
    TOOLS_TECH_WANT("toolsTechWanted", SurveyColumn.TOOLS_TECH_WANT_TO_WORK_WITH, false),
    TOOLS_TECH_ADMIRED("toolsTechAdmired", SurveyColumn.TOOLS_TECH_ADMIRED, false),
    COLLAB_TOOLS_USED("collabToolsUsed", SurveyColumn.NEW_COLLAB_TOOLS_HAVE_WORKED_WITH, false),
    COLLAB_TOOLS_WANT("collabToolsWanted", SurveyColumn.NEW_COLLAB_TOOLS_WANT_TO_WORK_WITH, false),
    COLLAB_TOOLS_ADMIRED("collabToolsAdmired", SurveyColumn.NEW_COLLAB_TOOLS_ADMIRED, false),
    OFFICE_ASYNC_USED("officeAsyncUsed", SurveyColumn.OFFICE_STACK_ASYNC_HAVE_WORKED_WITH, false),
    OFFICE_ASYNC_WANT("officeAsyncWanted", SurveyColumn.OFFICE_STACK_ASYNC_WANT_TO_WORK_WITH, false),
    OFFICE_ASYNC_ADMIRED("officeAsyncAdmired", SurveyColumn.OFFICE_STACK_ASYNC_ADMIRED, false),
    OFFICE_SYNC_USED("officeSyncUsed", SurveyColumn.OFFICE_STACK_SYNC_HAVE_WORKED_WITH, false),
    OFFICE_SYNC_WANT("officeSyncWanted", SurveyColumn.OFFICE_STACK_SYNC_WANT_TO_WORK_WITH, false),
    OFFICE_SYNC_ADMIRED("officeSyncAdmired", SurveyColumn.OFFICE_STACK_SYNC_ADMIRED, false),
    AI_SEARCH_USED("aiSearchUsed", SurveyColumn.AI_SEARCH_DEV_HAVE_WORKED_WITH, false),
    AI_SEARCH_WANT("aiSearchWanted", SurveyColumn.AI_SEARCH_DEV_WANT_TO_WORK_WITH, false),
    AI_SEARCH_ADMIRED("aiSearchAdmired", SurveyColumn.AI_SEARCH_DEV_ADMIRED, false),
    OPSYS_PERSONAL("opSysPersonal", SurveyColumn.OPSYS_PERSONAL_USE, false),
    OPSYS_PROFESSIONAL("opSysProfessional", SurveyColumn.OPSYS_PROFESSIONAL_USE, false),
    COUNTRY_NAME("countryName", null, false),
    PROGRAMMING_LANGUAGE_NAME("programmingLanguageName", null, false),
    DATABASE_NAME("databaseName", null, false),
    PLATFORM_NAME("platformName", null, false),
    FRAMEWORK_NAME("frameworkName", null, false),
    EMBEDDED_NAME("embeddedName", null, false),
    TOOLS_TECH_NAME("toolsTechName", null, false),
    COLLAB_TOOLS_NAME("collabToolsName", null, false),
    OFFICE_ASYNC_NAME("officeAsyncName", null, false),
    OFFICE_SYNC_NAME("officeSyncName", null, false),
    AI_SEARCH_NAME("aiSearchName", null, false),
    OPSYS_NAME("opSysName", null, false),
    WEB_FRAMEWORK_NAME("webFrameworkName", null, false);


    private final String localName;
    private final SurveyColumn column;
    private final boolean needsLiteral;

    Predicate(String localName, SurveyColumn column, boolean needsLiteral) {
        this.localName = localName;
        this.column = column;
        this.needsLiteral = needsLiteral;
    }

    public SurveyColumn getSurveyColumn() {
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
            case EMBEDDED_ADMIRED,
                 EMBEDDED_USED,
                 EMBEDDED_WANT -> OntologyClass.EMBEDDED;
            default -> null;
        };
    }

    public boolean needsLiteral() {
        return needsLiteral;
    }

    public IRI getXSD()
    {
        return switch (this) {
            case COMPENSATION,
                 HAS_JOB_SATISFACTION,
                 WORK_EXPERIENCE -> XSD.INT;
            default -> XSD.STRING;
        };
    }

}
