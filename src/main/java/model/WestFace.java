package model;


public class WestFace extends RoverFace {
    Rover roverObj;

    public WestFace(Rover roverObj) {
        this.roverObj = roverObj;
        this.setDirection('W');
    }

    @Override
    public Boolean canMove(Plateau roverPlateau, int nextCoordinate) {
        return roverPlateau.getStartPosition().getXCoordinate() <= nextCoordinate && roverPlateau.getEndPosition().getXCoordinate() >= nextCoordinate;
    }

    @Override
    public Boolean move(Plateau roverPlateau) {
        int nextCoordinate = roverObj.getRoverPosition().getXCoordinate() - 1;
        if (canMove(roverPlateau, nextCoordinate)) {
            roverObj.getRoverPosition().setXCoordinate(nextCoordinate);
            return true;
        }
        return false;
    }

    @Override
    public void spinLeft() {
        roverObj.setFace(roverObj.getSouthFace());
    }

    @Override
    public void spinRight() {
        roverObj.setFace(roverObj.getNorthFace());
    }


}
