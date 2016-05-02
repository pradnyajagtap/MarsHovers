package model;


public class Command {
    private Character command;

    public Command(Character command) {
        this.command = command;
    }

    Character getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return command.toString();
    }
}
