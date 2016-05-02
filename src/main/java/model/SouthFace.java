package model;


public class SouthFace extends RoverFace {
    Rover roverObj;

    public SouthFace(Rover roverObj) {
        this.roverObj = roverObj;
        this.setDirection('S');
    }


    @Override
    public Boolean canMove(Plateau roverPlateau, int nextCoordinate) {
        return roverPlateau.getStartPosition().getYCoordinate() <= nextCoordinate && roverPlateau.getEndPosition().getYCoordinate() >= nextCoordinate;
    }

    @Override
    public Boolean move(Plateau roverPlateau) {
        int nextCoordinate = roverObj.getRoverPosition().getYCoordinate() - 1;
        if (canMove(roverPlateau, nextCoordinate)) {
            roverObj.getRoverPosition().setYCoordinate(nextCoordinate);
            return true;
        }
        return false;
    }


    @Override
    public void spinLeft() {
        roverObj.setFace(roverObj.getEastFace());
    }

    @Override
    public void spinRight() {
        roverObj.setFace(roverObj.getWestFace());
    }


}
