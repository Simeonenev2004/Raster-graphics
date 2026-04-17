package rastereditor.model;

import rastereditor.transformations.Transformation;
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

    public List<Transformation> getTransformations() {
        return transformations;
    }

    public void addImage(ImageFile img) {
        images.add(img);
    }

    public void addTransformation(Transformation t) {
        transformations.add(t);
    }

    public String getSessionInfo() {
        StringBuilder sb = new StringBuilder();

        sb.append("Name of images in the session: ");
        for (ImageFile img : images) {
            sb.append(img.getFilename()).append(" ");
        }

        sb.append("\nPending transformations: ");
        if (transformations.isEmpty()) {
            sb.append("none");
        } else {
            for (Transformation t : transformations) {
                sb.append(t.getName()).append(" ");
            }
        }

        return sb.toString();
    }

    public String undoLastTransformation() {
        if (!transformations.isEmpty()) {
            Transformation removed = transformations.remove(transformations.size() - 1);
            return "Removed last transformation: " + removed.getName();
        } else {
            return "No transformations to undo.";
        }
    }

    public String createCollage(String direction, String img1Name, String img2Name, String outName) {
        ImageFile img1 = null;
        ImageFile img2 = null;

        for (ImageFile img : images) {
            if (img.getFilename().equals(img1Name)) img1 = img;
            if (img.getFilename().equals(img2Name)) img2 = img;
        }

        if (img1 == null || img2 == null) {
            return "One or both images not found in session.";
        }

        if (img1.getType() != img2.getType()) {
            return "Cannot make a collage from different types! (" + img1.getType() + " and " + img2.getType() + ")";
        }

        ImageFile newImage = new ImageFile(outName);
        images.add(newImage);
        return "New collage \"" + outName + "\" created";
    }
}
