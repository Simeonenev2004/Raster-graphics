package rastereditor.transformations;

import rastereditor.model.ImageFile;

public abstract class Transformation {
    public abstract String getName();
    public abstract ImageFile apply(ImageFile img);
}
