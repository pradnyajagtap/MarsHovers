package model;


import java.util.List;

public class Rover {
    private Plateau roverPlateau;
    private Position roverPosition;
    private Face roverFace;
    private List<Command> roverCommand;

    public Rover(Plateau roverPlateau, Position roverPosition, Face roverFace, List<Command> roverCommand) {
        this.roverPlateau = roverPlateau;
        this.roverPosition = roverPosition;
        this.roverFace = roverFace;
        this.roverCommand = roverCommand;
    }

    Position getRoverPosition() {
        return roverPosition;
    }

    Face getRoverFace() {
        return roverFace;
    }

    /**
     * Execute commands of rover from command list
     */
    void executeCommands() {
        Boolean validCommand = true;
        for (Command command : roverCommand) {
            if (!validCommand) {
                System.out.println("\nCommand list terminated................");
                break;
            }
            switch (command.getCommand()) {
                case 'L':
                    spinLeft();
                    break;
                case 'R':
                    spinRight();
                    break;
                case 'M':
                    if (!moveForward()) {
                        validCommand = false;
                    }
                    break;
            }
        }
    }

    /**
     * Changes the face direction of rover by spinning left
     */
    private void spinLeft() {
        switch (roverFace.getFacing()) {
            case 'N':
                roverFace.setFacing('W');
                break;
            case 'W':
                roverFace.setFacing('S');

                break;
            case 'S':
                roverFace.setFacing('E');
                break;
            case 'E':
                roverFace.setFacing('N');
                break;
        }
    }

    /**
     * Changes the face direction of rover by spinning right
     */
    private void spinRight() {
        switch (roverFace.getFacing()) {
            case 'N':
                roverFace.setFacing('E');
                break;
            case 'E':
                roverFace.setFacing('S');

                break;
            case 'S':
                roverFace.setFacing('W');
                break;
            case 'W':
                roverFace.setFacing('N');
                break;
        }
    }

    /**
     * Rover will Move forward by one grid
     * @return True if moved successfully or else False if invalid movement
     */
    private boolean moveForward() {
        if (canMoveTowards()) {
            switch (roverFace.getFacing()) {
                case 'E':
                    roverPosition.setXCoordinate(roverPosition.getXCoordinate() + 1);
                    break;
                case 'W':
                    roverPosition.setXCoordinate(roverPosition.getXCoordinate() - 1);
                    break;
                case 'N':
                    roverPosition.setYCoordinate(roverPosition.getYCoordinate() + 1);
                    break;
                case 'S':
                    roverPosition.setYCoordinate(roverPosition.getYCoordinate() - 1);
                    break;
            }
            return true;
        } else {
            System.out.println("\nInvalid Movement!!!Can not move forward.........");
            return false;
        }
    }

    /**
     * Check that rover can move forward or not
     * @return True if rover can move or else False
     */
    private boolean canMoveTowards() {
        Boolean canMove = false;
        switch (roverFace.getFacing()) {
            case 'E':
                if (checkRangeOfXCoordinates(roverPosition.getXCoordinate() + 1))
                    canMove = true;
                break;
            case 'W':
                if (checkRangeOfXCoordinates(roverPosition.getXCoordinate() - 1))
                    canMove = true;
                break;
            case 'N':
                if (checkRangeOfYCoordinates(roverPosition.getYCoordinate() + 1))
                    canMove = true;
                break;
            case 'S':
                if (checkRangeOfYCoordinates(roverPosition.getYCoordinate() - 1))
                    canMove = true;
                break;
        }
        return canMove;

    }

    /**
     * To move forward will check range of Plateau of X coordinate
     * @param coordinate X coordinate of rover to check
     * @return True if rover can move within range of plateau or else False
     */
    private boolean checkRangeOfXCoordinates(int coordinate) {
        return roverPlateau.getStartPosition().getXCoordinate() <= coordinate && roverPlateau.getEndPosition().getXCoordinate() >= coordinate;
    }

    /**
     * To move forward will check range of Plateau of Y coordinate
     * @param coordinate Y coordinate of rover to check
     * @return True if rover can move within range of plateau or else False
     */
    private boolean checkRangeOfYCoordinates(int coordinate) {
        return roverPlateau.getStartPosition().getYCoordinate() <= coordinate && roverPlateau.getEndPosition().getYCoordinate() >= coordinate;
    }

    @Override
    public String toString() {
        return "\n*****Rover[Position " + roverPosition + "Face " + roverFace + "***Commands " + roverCommand + ']';
    }

}
