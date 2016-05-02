package model;


import java.util.List;

public class Rover {
    private Plateau roverPlateau;
    private Position roverPosition;
    private RoverFace eastFace;
    private RoverFace westFace;
    private RoverFace northFace;
    private RoverFace southFace;
    private RoverFace face;
    private List<Command> roverCommand;

    public Rover(Plateau roverPlateau, Position roverPosition, char direction, List<Command> roverCommand) {
        this.roverPlateau = roverPlateau;
        this.roverPosition = roverPosition;
        this.roverCommand = roverCommand;
        eastFace = new EastFace(this);
        westFace = new WestFace(this);
        northFace = new NorthFace(this);
        southFace = new SouthFace(this);
        face = RoverFace.getFace(this, direction);
    }

    public Position getRoverPosition() {
        return roverPosition;
    }

    public RoverFace getRoverFace() {
        return face ;
    }

    public RoverFace getEastFace() {
        return eastFace;
    }

    public RoverFace getWestFace() {
        return westFace;
    }

    public RoverFace getNorthFace() {
        return northFace;
    }

    public RoverFace getSouthFace() {
        return southFace;
    }

    public void setFace(RoverFace face) {
        this.face = face;
    }

    /**
     * Execute commands of rover from Command List
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
                    face.spinLeft();
                    break;
                case 'R':
                    face.spinRight();
                    break;
                case 'M':
                    if (!face.move(roverPlateau)) {
                        validCommand = false;
                    }
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return "\n*****Rover[Position " + roverPosition + "Face " + face.getDirection() + "***Commands " + roverCommand + ']';
    }
}
