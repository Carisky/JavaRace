package enums;

public enum MaxSpeed {
    SLOW(100),
    MEDIUM(150),
    FAST(200),
    VERY_FAST(250);

    private final int speed;

    MaxSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
}