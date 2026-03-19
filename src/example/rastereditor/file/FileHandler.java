package example.rastereditor.file;

import example.rastereditor.model.ImageFile;

public class FileHandler {

    public static ImageFile load(String filename) {

        StringBuilder sb = new StringBuilder();
        sb.append("Successfully loaded ").append(filename);

        System.out.println(sb);

        return new ImageFile(filename);
    }

    public static void save(ImageFile img) {

        StringBuilder sb = new StringBuilder();
        sb.append("Saved ").append(img.getFilename());

        System.out.println(sb);
    }

    public static void saveAs(ImageFile img, String newName) {

        StringBuilder sb = new StringBuilder();
        sb.append("Saved as ").append(newName);

        System.out.println(sb);
    }
}