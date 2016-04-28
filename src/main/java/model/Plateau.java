package model;

public class Plateau {
    private Position startPosition;
    private Position endPosition;

    public Plateau(Position endPosition) {
        this.startPosition = new Position(0, 0);
        this.endPosition = endPosition;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getEndPosition() {
        return endPosition;
    }

    @Override
    public String toString() {
        return "\n*** startPosition ::  " + startPosition +
                ", endPosition ::  " + endPosition ;
    }
}
