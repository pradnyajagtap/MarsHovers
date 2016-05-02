package model;


public class EastFace extends RoverFace {
    private Rover roverObj;

    public EastFace(Rover roverObj) {
        this.roverObj = roverObj;
        this.setDirection('E');
    }


    @Override
    public Boolean canMove(Plateau roverPlateau, int nextCoordinate) {
        return roverPlateau.getStartPosition().getXCoordinate() <= nextCoordinate && roverPlateau.getEndPosition().getXCoordinate() >= nextCoordinate;
    }

    @Override
    public Boolean move(Plateau roverPlateau) {
        int nextCoordinate = roverObj.getRoverPosition().getXCoordinate() + 1;
        if (canMove(roverPlateau, nextCoordinate)) {
            roverObj.getRoverPosition().setXCoordinate(nextCoordinate);
            return true;
        }
        return false;
    }

    @Override
    public void spinLeft() {
        roverObj.setFace(roverObj.getNorthFace());
    }

    @Override
    public void spinRight() {
        roverObj.setFace(roverObj.getSouthFace());
    }

}
