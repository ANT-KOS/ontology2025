package Constants;

public enum SurveyColumn {
    RESPONSE_ID("ResponseId"),
    MAIN_BRANCH("MainBranch"),
    AGE("Age"),
    EMPLOYMENT("Employment"),
    REMOTE_WORK("RemoteWork"),
    ED_LEVEL("EdLevel"),
    LEARN_CODE_ONLINE("LearnCodeOnline"),
    TECH_DOC("TechDoc"),
    YEARS_CODE("YearsCode"),
    YEARS_CODE_PRO("YearsCodePro"),
    DEV_TYPE("DevType"),
    ORG_SIZE("OrgSize"),
    COUNTRY("Country"),
    CURRENCY("Currency"),
    COMP_TOTAL("CompTotal"),
    LANGUAGE_HAVE_WORKED_WITH("LanguageHaveWorkedWith"),
    LANGUAGE_WANT_TO_WORK_WITH("LanguageWantToWorkWith"),
    LANGUAGE_ADMIRED("LanguageAdmired"),
    DATABASE_HAVE_WORKED_WITH("DatabaseHaveWorkedWith"),
    DATABASE_WANT_TO_WORK_WITH("DatabaseWantToWorkWith"),
    DATABASE_ADMIRED("DatabaseAdmired"),
    PLATFORM_HAVE_WORKED_WITH("PlatformHaveWorkedWith"),
    PLATFORM_WANT_TO_WORK_WITH("PlatformWantToWorkWith"),
    PLATFORM_ADMIRED("PlatformAdmired"),
    WEBFRAME_HAVE_WORKED_WITH("WebframeHaveWorkedWith"),
    WEBFRAME_WANT_TO_WORK_WITH("WebframeWantToWorkWith"),
    WEBFRAME_ADMIRED("WebframeAdmired"),
    EMBEDDED_HAVE_WORKED_WITH("EmbeddedHaveWorkedWith"),
    EMBEDDED_WANT_TO_WORK_WITH("EmbeddedWantToWorkWith"),
    EMBEDDED_ADMIRED("EmbeddedAdmired"),
    TOOLS_TECH_HAVE_WORKED_WITH("ToolsTechHaveWorkedWith"),
    TOOLS_TECH_WANT_TO_WORK_WITH("ToolsTechWantToWorkWith"),
    TOOLS_TECH_ADMIRED("ToolsTechAdmired"),
    NEW_COLLAB_TOOLS_HAVE_WORKED_WITH("NEWCollabToolsHaveWorkedWith"),
    NEW_COLLAB_TOOLS_WANT_TO_WORK_WITH("NEWCollabToolsWantToWorkWith"),
    NEW_COLLAB_TOOLS_ADMIRED("NEWCollabToolsAdmired"),
    OPSYS_PERSONAL_USE("OpSysPersonal use"),
    OPSYS_PROFESSIONAL_USE("OpSysProfessional use"),
    OFFICE_STACK_ASYNC_HAVE_WORKED_WITH("OfficeStackAsyncHaveWorkedWith"),
    OFFICE_STACK_ASYNC_WANT_TO_WORK_WITH("OfficeStackAsyncWantToWorkWith"),
    OFFICE_STACK_ASYNC_ADMIRED("OfficeStackAsyncAdmired"),
    OFFICE_STACK_SYNC_HAVE_WORKED_WITH("OfficeStackSyncHaveWorkedWith"),
    OFFICE_STACK_SYNC_WANT_TO_WORK_WITH("OfficeStackSyncWantToWorkWith"),
    OFFICE_STACK_SYNC_ADMIRED("OfficeStackSyncAdmired"),
    AI_SEARCH_DEV_HAVE_WORKED_WITH("AiSearchDevHaveWorkedWith"),
    AI_SEARCH_DEV_WANT_TO_WORK_WITH("AiSearchDevWantToWorkWith"),
    AI_SEARCH_DEV_ADMIRED("AiSearchDevAdmired"),
    WORK_EXP("WorkExp"),
    INDUSTRY("Industry"),
    JOB_SAT("JobSat");

    private final String fieldName;

    SurveyColumn(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
