package model;


public class NorthFace extends RoverFace {
    private Rover roverObj;

    public NorthFace(Rover roverObj) {
        this.roverObj = roverObj;
        this.setDirection('N');
    }


    @Override
    public Boolean canMove(Plateau roverPlateau, int nextCoordinate) {
        return roverPlateau.getStartPosition().getYCoordinate() <= nextCoordinate && roverPlateau.getEndPosition().getYCoordinate() >= nextCoordinate;
    }

    @Override
    public Boolean move(Plateau roverPlateau) {
        int nextCoordinate = roverObj.getRoverPosition().getYCoordinate() + 1;
        if (canMove(roverPlateau, nextCoordinate)) {
            roverObj.getRoverPosition().setYCoordinate(nextCoordinate);

            return true;
        }
        return false;
    }

    @Override
    public void spinLeft() {
        roverObj.setFace(roverObj.getWestFace());
    }

    @Override
    public void spinRight() {
        roverObj.setFace(roverObj.getEastFace());
    }


}
