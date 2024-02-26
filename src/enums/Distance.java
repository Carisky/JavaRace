package enums;

public enum Distance {
    SMALL(2000),
    MEDIUM(3500),
    LARGE(4200),
    HUGE(5100);

    private final int distance;
    Distance(int distance) {
        this.distance = distance;
    }
    public int getDistance() {
        return distance;
    }
}
