package rastereditor.transformations;

public class Rotate extends Transformation {

    private String direction;

    public Rotate(String direction) {
        this.direction = direction;
    }

    @Override
    public String getName() {
        return "rotate " + direction;
    }
}