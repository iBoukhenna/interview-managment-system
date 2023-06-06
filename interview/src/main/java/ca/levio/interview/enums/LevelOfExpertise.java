package ca.levio.interview.enums;

public enum LevelOfExpertise {
    
    JUNIOR("A", "Junior"),
    INTERMEDIATE("B", "Intermédiaire"),
    SENIOR("C", "Senior"),
    SENIOR_PLUS("D", "Senior +"),
    SKILLED("E", "Emérite");
    
    private final String code;
    private final String value;
    
    LevelOfExpertise(String code, String value) {
        this.code = code;
        this.value = value;
    }
    
    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
