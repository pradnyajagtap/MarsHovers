package model;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;


public class SquadTest extends TestCase {

    public void testNavigate() throws Exception {
        int xCoordinate = 5;
        int yCoordinate = 5;
        String positionInput = "1 2 N";
        String commandInput = "LMLMLMLMM";


        Position endPosition = new Position(xCoordinate, yCoordinate);
        Plateau mars = new Plateau(endPosition);
        String inputArray[] = positionInput.split(" ");
        String xCoordinateStr = inputArray[0];
        String yCoordinateStr = inputArray[1];
        String face = inputArray[2];

        Position roverPosition = new Position(Integer.parseInt(xCoordinateStr), Integer.parseInt(yCoordinateStr));
        List<Command> roverCommand = new ArrayList<>();
        for (int i = 0; i < commandInput.length(); i++)
            roverCommand.add(new Command(commandInput.charAt(i)));

        List<Rover> rovers = new ArrayList<>();
        rovers.add(new Rover(mars, roverPosition, face.charAt(0), roverCommand));

        Squad marSquad = new Squad(mars, rovers);
        marSquad.Navigate();
        List<Rover> outputRovers = marSquad.getRovers();
        Rover outputRover = outputRovers.get(0);


        Position expectedPosition = new Position(1, 3);
        RoverFace expectedFace = RoverFace.getFace(outputRover,'N');

        assertEquals("Wrong Position after Navigation", expectedPosition, outputRover.getRoverPosition());
        assertEquals("Wrong Face Direction after Navigation", expectedFace.getDirection(), outputRover.getRoverFace().getDirection());

    }

}