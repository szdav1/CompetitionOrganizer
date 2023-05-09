package support.constants;

public enum PositionConstants {
    TOP_POS(10),
    MID_TOP_POS(7),
    MID_POS(5),
    MID_LOW_POS(3),
    LOW_POS(1),
    BACKGROUND_POS(0);

    private final Integer zIndex;

    PositionConstants(Integer zIndex) {
        this.zIndex = zIndex;
    }

    public Integer getzIndex() {
        return this.zIndex;
    }
}
