package model;


import java.util.List;

public class Squad {
    private Plateau plateau;
    private List<Rover> rovers;

    public Squad(Plateau plateau, List<Rover> rovers) {
        this.plateau = plateau;
        this.rovers = rovers;
    }

    public List<Rover> getRovers() {
        return rovers;
    }

    @Override
    public String toString() {
        return "\n\n****Squad  :: "+ "\n***Plateau ::  "+plateau+"\n***Rovers ::  "+rovers;
    }

    public void Navigate() {
        rovers.forEach(Rover::executeCommands);
    }
}

