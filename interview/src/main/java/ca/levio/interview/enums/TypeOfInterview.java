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
