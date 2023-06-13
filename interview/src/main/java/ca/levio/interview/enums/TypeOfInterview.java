package ca.levio.interview.enums;

public enum TypeOfInterview {
    URGENT("URGENT", 2, 5),
    NOT_URGENT("NOT_URGENT", 2, 5);
    
    private final String code;
    private final int x;
    private final int y;
    
    TypeOfInterview(String code, int x, int y) {
        this.code = code;
        this.x = x;
        this.y = y;
    }

    public static TypeOfInterview getTypeOfInterviewFromX(int x) {
        TypeOfInterview typeOfInterview = null;
        for (TypeOfInterview type : TypeOfInterview.values()) {
            if (type.getX() == x) {
                typeOfInterview = type;
                break;
            }
        }
        return typeOfInterview;
    }
    
    public String getCode() {
        return code;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
