package info3.game.entity;

public enum Direction {
    LEFT(-1,0),
    RIGHT(1,0),
    UPPER(0,-1),
    BOTTOM(0,1),
    IDLE(0, 0),

    LEFT_TOP(0, 0),
    LEFT_BOTTOM(0, 0),
    RIGHT_TOP(0, 0),
    RIGHT_BOTTOM(0, 0),

    EMPTY(0, 0);

    public final int x;
    public final int y;

    private Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Direction fromString(String directionString) {
        switch (directionString.toUpperCase()) {
            case "N":
                return UPPER;
            case "S":
                return BOTTOM;
            case "W":
                return LEFT;
            case "E":
                return RIGHT;
            case "NONE":
                return EMPTY;
            default:
                throw new IllegalArgumentException("Invalid direction string: " + directionString);
        }
    }
}
