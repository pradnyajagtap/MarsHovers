package mars;


import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String args[]) {
        App appObj = new App();
        Plateau mars = appObj.getPlateau();
        List<Rover> rovers = appObj.getRoverList(mars);
        Squad marSquad = new Squad(mars, rovers);
        System.out.println("\nBefore Navigating Plateau ::: " + marSquad);
        marSquad.Navigate();
        System.out.println("\nAfter Navigating Plateau ::: " + marSquad);
    }

    /**
     * Will create and return plateau object depending upon user input
     * @return Plateau object with user entered end position and start position will be 0 0 by default
     */
    private Plateau getPlateau() {
        String plateauPosition = null;
        while (plateauPosition == null) {
            plateauPosition = getStringPlateauFromPosition();
        }
        String inputArray[] = plateauPosition.split(" ");
        String xCoordinate = inputArray[0];
        String yCoordinate = inputArray[1];
        Position endPosition = new Position(Integer.parseInt(xCoordinate), Integer.parseInt(yCoordinate));
        return new Plateau(endPosition);
    }

    /**
     * Accepting user input for plateau end position and validating it
     * @return user input string for plateau end position if it is in correct format, else will return null
     */
    private String getStringPlateauFromPosition() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Plateau End Position - Ex. 5 5 :: ");
        String inputStr = scanner.nextLine();
        String inputArray[] = inputStr.split(" ");
        if (inputArray.length == 2) {
            String xCoordinate = inputArray[0];
            String yCoordinate = inputArray[1];
            try {
                int x = Integer.parseInt(xCoordinate);
                int y = Integer.parseInt(yCoordinate);
            } catch (Exception e) {
                System.out.println("Invalid Plateau Position!!Please Enter in number format ONLY");
                return null;
            }
            return inputStr;
        } else {
            System.out.println("Invalid Plateau Position!!!Please Enter only two input integers ONLY");
            return null;
        }
    }

    /**
     * Creating Rover list for input plateau object depending upon user input
     * @param plateauObj plateau for which to create rover list
     * @return created list of rovers.
     */
    private List<Rover> getRoverList(Plateau plateauObj) {
        List<Rover> roverList = new ArrayList<>();
        int totalRovers = -1;
        while (totalRovers == -1) {
            totalRovers = getTotalNumberOfRovers();
        }
        for (int i = 0; i < totalRovers; i++) {
            Rover roverObj = null;
            while (roverObj == null) {
                roverObj = getRoverFromPositionAndCommand(plateauObj);
            }
            roverList.add(roverObj);
        }
        return roverList;
    }

    /**
     *  Accepting user input for total number of rovers and validating user input
     * @return Integer of total number of rovers to accept
     */
    private int getTotalNumberOfRovers() {
        int totalRovers = -1;
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter Total Number Of Robotic Rovers in Squad :: ");
        try {
            totalRovers = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid Input!!!Please Enter in the integer format ONLY");
        }
        return totalRovers;
    }

    /**
     * Accepting user input for rover position and command to execute & validating user input
     * @param plateauObj plateau for which to create rover
     * @return Rover with user input position and command list to execute by rover
     */
    private Rover getRoverFromPositionAndCommand(Plateau plateauObj) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter Rover Position - Ex. 1 2 N ::  ");
        String positionInput = scanner.nextLine();
        try {
            String inputArray[] = positionInput.split(" ");
            if (inputArray.length == 3) {
                String xCoordinateStr = inputArray[0];
                String yCoordinateStr = inputArray[1];
                String face = inputArray[2];
                int xCoordinate = Integer.parseInt(xCoordinateStr);
                int yCoordinate = Integer.parseInt(yCoordinateStr);
                if (xCoordinate < plateauObj.getStartPosition().getXCoordinate() || xCoordinate > plateauObj.getEndPosition().getXCoordinate()) {
                    System.out.println("Invalid Rover Position,Position should be in Plateau Range");
                    return null;
                }
                if (yCoordinate < plateauObj.getStartPosition().getYCoordinate() || yCoordinate > plateauObj.getEndPosition().getYCoordinate()) {
                    System.out.println("Invalid Rover Position,Position should be in Plateau Range");
                    return null;
                }
                if (face.length() == 1 && face.matches("[EWNS]*")) {
                    Boolean validCommand = false;
                    String commandInput = "";
                    List<Command> roverCommand = new ArrayList<>();
                    while (!validCommand) {
                        System.out.print("Enter Rover command - Ex. LRM  :: ");
                        commandInput = scanner.nextLine();
                        validCommand = isValidCommand(commandInput);
                    }
                    for (int i = 0; i < commandInput.length(); i++)
                        roverCommand.add(new Command(commandInput.charAt(i)));
                    Position roverPosition = new Position(xCoordinate, yCoordinate);
                    Face roverFace = new Face(face.charAt(0));
                    return new Rover(plateauObj, roverPosition, roverFace, roverCommand);
                } else {
                    System.out.println("Invalid Rover Face direction should be E/W/N/S");
                    return null;
                }
            } else {
                System.out.println("Invalid Rover Position!!!Please Enter in format - XCoordinate YCoordinate facingDirection");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Invalid Rover Position!!!Please Enter - XCoordinate YCoordinate in the integer format ONLY");
            return null;
        }
    }

    /**
     * Validating command to rover is correct or not
     * @param commandInput String to validate as a command
     * @return True if command is correct Or Else false
     */
    private boolean isValidCommand(String commandInput) {
        if (commandInput.matches("[LRM]*"))
            return true;
        else {
            System.out.println("Invalid Command!!!Please Enter in command L/R/M");
            return false;
        }
    }

}
