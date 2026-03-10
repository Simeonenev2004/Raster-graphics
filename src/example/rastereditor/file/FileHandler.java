package example.rastereditor.file;

import example.rastereditor.model.ImageFile;

public class FileHandler {

    public static ImageFile load(String filename) {
        System.out.println("Successfully loaded " + filename);
        return new ImageFile(filename);
    }

    public static void save(ImageFile img) {
        System.out.println("Saved " + img.getFilename());
    }

    public static void saveAs(ImageFile img, String newName) {
        System.out.println("Saved as " + newName);
    }
}
