package ca.levio.interview.enums;

public enum TypeOfInterview {
    URGENT("URGENT", 2, 5),
    NOT_URGENT("NOT_URGENT", 2, 5);
    
    private final String code;
    private final int numberOfTechnicalAdvisorByBatch;
    private final int delayBeforeRetrying;
    
    TypeOfInterview(String code, int numberOfTechnicalAdvisorByBatch, int delayBeforeRetrying) {
        this.code = code;
        this.numberOfTechnicalAdvisorByBatch = numberOfTechnicalAdvisorByBatch;
        this.delayBeforeRetrying = delayBeforeRetrying;
    }

    public static TypeOfInterview getTypeOfInterviewFromNumberOfTechnicalAdvisorByBatch(int numberOfTechnicalAdvisorByBatch) {
        TypeOfInterview typeOfInterview = null;
        for (TypeOfInterview type : TypeOfInterview.values()) {
            if (type.getNumberOfTechnicalAdvisorByBatch() == numberOfTechnicalAdvisorByBatch) {
                typeOfInterview = type;
                break;
            }
        }
        return typeOfInterview;
    }
    
    public String getCode() {
        return code;
    }

    public int getNumberOfTechnicalAdvisorByBatch() {
        return numberOfTechnicalAdvisorByBatch;
    }

    public int getDelayBeforeRetrying() {
        return delayBeforeRetrying;
    }

}
