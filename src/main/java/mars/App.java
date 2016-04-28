package mars;


import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String aargs[]) {
        App appObj = new App();
        Plateau mars = appObj.getPlateau();
        List<Rover> rovers = appObj.getRoverList(mars);
        Squad marSquad = new Squad(mars, rovers);
        System.out.println("\nBefore Navigating Plateau ::: " + marSquad);
        marSquad.Navigate();
        System.out.println("\nAfter Navigating Plateau ::: " + marSquad);
    }

    private Plateau getPlateau() {
        String plateauPosition = null;
        while (plateauPosition == null) {
            plateauPosition = getStringPlateuFromPosition();
        }
        String inputArray[] = plateauPosition.split(" ");
        String xCoordinate = inputArray[0];
        String yCoordinate = inputArray[1];
        Position endPosition = new Position(Integer.parseInt(xCoordinate), Integer.parseInt(yCoordinate));
        return new Plateau(endPosition);
    }

    private String getStringPlateuFromPosition() {
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

    private Rover getRoverFromPositionAndCommand(Plateau plateauObj) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter Rover Position :: ");
        String positionInput = scanner.nextLine();
        try {
            String inputArray[] = positionInput.split(" ");
            if (inputArray.length == 3) {
                String xCoordinateStr = inputArray[0];
                String yCoordinateStr = inputArray[1];
                String face = inputArray[2];
                int xCoordinate = Integer.parseInt(xCoordinateStr);
                int yCoordinate = Integer.parseInt(yCoordinateStr);
                if(xCoordinate < plateauObj.getStartPosition().getXCoordinate() || xCoordinate > plateauObj.getEndPosition().getXCoordinate() )
                {
                    System.out.println("Invalid Rover Position,Position should be in Plateau Range");
                    return null;
                }
                if(yCoordinate < plateauObj.getStartPosition().getYCoordinate() || yCoordinate > plateauObj.getEndPosition().getYCoordinate() )
                {
                    System.out.println("Invalid Rover Position,Position should be in Plateau Range");
                    return null;
                }
                if (face.length() == 1 && face.matches("[EWNS]*")) {
                    Boolean validCommand = false;
                    String commandInput = "";
                    List<Command> roverCommand = new ArrayList<>();
                    while (!validCommand) {
                        System.out.print("Enter Rover command :: ");
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
                System.out.println("Invalid Rover Postion!!!Please Enter in format - XCoordinate YCoordinate facingDirection");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Invalid Rover Postion!!!Please Enter - XCoordinate YCoordinate in the integer format ONLY");
            return null;
        }
    }

    private boolean isValidCommand(String commandInput) {
        if (commandInput.matches("[LRM]*"))
            return true;
        else {
            System.out.println("Invalid Command!!!Please Enter in command L/R/M");
            return false;
        }
    }

}
