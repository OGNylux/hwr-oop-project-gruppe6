package hwr.oop;

public enum EstimatedTime {
    SHORT,

    MEDIUM,

    LONG,

    XLONG;

    public int toInt() {
        switch (this) {
            case SHORT:
                return 0;
            case MEDIUM:
                return 1;
            case LONG:
                return 2;
            case XLONG:
                return 3;
        }
        return -1;
    }
}
