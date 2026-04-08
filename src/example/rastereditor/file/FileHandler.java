package example.rastereditor.file;

import example.rastereditor.model.ImageFile;

public class FileHandler {

    public static ImageFile load(String filename) {
        // просто създаваме ImageFile, без пиксели
        return new ImageFile(filename);
    }

    public static String save(ImageFile img) {
        // връща съобщение, което MainApp ще печата
        return "Successfully saved " + img.getFilename();
    }

    public static String saveAs(ImageFile img, String newName) {
        return "Successfully saved as " + newName;
    }
}