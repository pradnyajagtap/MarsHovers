package model;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;


public class SquadTest extends TestCase {

    public void testNavigate() throws Exception {
        int xCoordinate = 5;
        int yCoordinate = 5;
        String positionInput = "3 3 E";
        String commandInput = "LRM";


        Position endPosition = new Position(xCoordinate, yCoordinate);
        Plateau mars = new Plateau(endPosition);
        String inputArray[] = positionInput.split(" ");
        String xCoordinateStr = inputArray[0];
        String yCoordinateStr = inputArray[1];
        String face = inputArray[2];

        Position roverPosition = new Position(Integer.parseInt(xCoordinateStr), Integer.parseInt(yCoordinateStr));
        Face roverFace = new Face(face.charAt(0));
        List<Command> roverCommand = new ArrayList<>();
        for (int i = 0; i < commandInput.length(); i++)
            roverCommand.add(new Command(commandInput.charAt(i)));

        List<Rover> rovers = new ArrayList<>();
        rovers.add(new Rover(mars, roverPosition, roverFace, roverCommand));

        Squad marSquad = new Squad(mars, rovers);
        marSquad.Navigate();
        List<Rover> outputRovers = marSquad.getRovers();
        Rover outputRover = outputRovers.get(0);


        Position expectedPosition = new Position(4, 3);
        Face expectedFace = new Face('E');

        assertEquals("Wrong Position after Navigation", expectedPosition, outputRover.getRoverPosition());
        assertEquals("Wrong Face Direction after Navigation", expectedFace, outputRover.getRoverFace());

    }

}