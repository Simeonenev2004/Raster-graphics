package example.rastereditor.model;

import example.rastereditor.transformations.Transformation;

import java.util.ArrayList;
import java.util.List;

public class Session {

    private int sessionId;
    private List<ImageFile> images = new ArrayList<>();
    private List<Transformation> transformations = new ArrayList<>();

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

    public void addTransformation(Transformation t) {
        transformations.add(t);
    }

    public void printSessionInfo() {

        StringBuilder sb = new StringBuilder();

        sb.append("Name of images in the session: ");
        for (ImageFile img : images) {
            sb.append(img.getFilename()).append(" ");
        }

        sb.append("\n");

        if (transformations.isEmpty()) {
            sb.append("Pending transformations: none");
        } else {
            sb.append("Pending transformations: ");
            for (Transformation t : transformations) {
                sb.append(t.getName()).append(" ");
            }
        }

        System.out.println(sb);
    }

    public void undoLastTransformation() {
        if (!transformations.isEmpty()) {
            Transformation removed = transformations.remove(transformations.size() - 1);
            StringBuilder sb = new StringBuilder();
            sb.append("Removed last transformation: ").append(removed.getName());
            System.out.println(sb);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("No transformations to undo.");
            System.out.println(sb);
        }
    }
}