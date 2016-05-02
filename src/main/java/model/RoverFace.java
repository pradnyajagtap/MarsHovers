package model;


public abstract class RoverFace {

    private char direction;

    public static RoverFace getFace(Rover roverObj, Character direction) {
        RoverFace roverFaceObj = null;
        switch (direction) {
            case 'N':
                roverFaceObj = new NorthFace(roverObj);
                break;
            case 'W':
                roverFaceObj = new WestFace(roverObj);
                break;
            case 'S':
                roverFaceObj = new SouthFace(roverObj);
                break;
            case 'E':
                roverFaceObj = new EastFace(roverObj);
                break;
        }
        return roverFaceObj;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public abstract Boolean canMove(Plateau roverPlateau, int nextCoordinate);

    public abstract Boolean move(Plateau roverPlateau);

    public abstract void spinLeft();

    public abstract void spinRight();
}
