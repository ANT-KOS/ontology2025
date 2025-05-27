package Constants;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum DeveloperPredicate {
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
    YEARS_OF_CODING("yearsOfCoding", SurveyColumn.YEARS_CODE);

    private final String localName;
    private final SurveyColumn column;

    DeveloperPredicate(String localName, SurveyColumn column) {
        this.localName = localName;
        this.column = column;
    }

    public SurveyColumn column() {
        return column;
    }

    public String getPredicate(String prefix) {
        return prefix + ":" + localName;
    }

    private static final Map<SurveyColumn, DeveloperPredicate> lookup = Arrays.stream(values())
            .collect(Collectors.toMap(DeveloperPredicate::column, Function.identity()));

    public static DeveloperPredicate fromColumn(SurveyColumn column) {
        return lookup.get(column);
    }
}
