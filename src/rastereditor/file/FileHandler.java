package rastereditor.file;

import rastereditor.model.ImageFile;

public class FileHandler {

    public static ImageFile load(String filename) {
        return new ImageFile(filename);
    }

    public static String save(ImageFile img) {
        return "Successfully saved " + img.getFilename();
    }

    public static String saveAs(ImageFile img, String newName) {
        return "Successfully saved as " + newName;
    }
}