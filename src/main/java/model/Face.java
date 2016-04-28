package model;


public class Face {
    private Character facing;

    public Face(Character facing) {
        this.facing = facing;
    }

    Character getFacing() {
        return facing;
    }

    void setFacing(Character facing) {
        this.facing = facing;
    }

    @Override
    public boolean equals(Object obj) {
        Face face = (Face) obj;
        if (facing==face.facing)
            return true;
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "***[" +
                "Direction ::  " + facing +
                "]***";
    }
}
