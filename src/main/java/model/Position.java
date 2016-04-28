package model;


public class Position {
    private int xCoordinate;
    private int yCoordinate;

    public Position(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    @Override
    public boolean equals(Object obj) {
        Position position = (Position) obj;
        if (xCoordinate == position.xCoordinate && yCoordinate == position.yCoordinate)
            return true;
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "***[" +
                "xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate +
                "]***";
    }
}
