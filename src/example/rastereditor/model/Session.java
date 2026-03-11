package example.rastereditor.model;

import java.util.ArrayList;
import java.util.List;

public class Session {

    private int sessionId;
    private List<ImageFile> images = new ArrayList<>();
    private List<String> transformations = new ArrayList<>();

    public Session(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public List<ImageFile> getImages() {
        return images;
    }

    public void addImage(ImageFile img) {
        images.add(img);
    }

    public void addTransformation(String transformation) {
        transformations.add(transformation);
    }

    public List<String> getTransformations() {
        return transformations;
    }

    public void printSessionInfo() {

        System.out.print("Name of images in the session: ");

        for (ImageFile img : images) {
            System.out.print(img.getFilename() + " ");
        }

        System.out.println();

        if (transformations.isEmpty()) {
            System.out.println("Pending transformations: none");
        } else {
            System.out.print("Pending transformations: ");
            for (String t : transformations) {
                System.out.print(t + " ");
            }
            System.out.println();
        }
    }
}
