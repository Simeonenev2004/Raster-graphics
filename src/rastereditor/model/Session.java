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
            sb.append(img.getShortName()).append(" ");
        }

        sb.append("\nPending transformations: ");
        if (transformations.isEmpty()) {
            sb.append("none");
        } else {
            for (int i = 0; i < transformations.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(transformations.get(i).getName());
            }
        }

        return sb.toString().trim();
    }

    public String undoLastTransformation() {

        if (transformations.isEmpty()) {
            return "No transformations to undo.";
        }

        Transformation removed = transformations.remove(transformations.size() - 1);
        return "Removed last transformation: " + removed.getName();
    }

    public String createCollage(String direction, String img1Name, String img2Name, String outName) {

        ImageFile img1 = null;
        ImageFile img2 = null;

        for (ImageFile img : images) {
            if (img.getFilename().equals(img1Name) || img.getShortName().equals(img1Name)) {
                img1 = img;
            }
            if (img.getFilename().equals(img2Name) || img.getShortName().equals(img2Name)) {
                img2 = img;
            }
        }

        if (img1 == null || img2 == null) {
            return "One or both images not found in session.";
        }

        if (img1.getType() != img2.getType()) {
            return "Cannot make a collage from different types! (."
                    + img1.getType().name().toLowerCase()
                    + " and ." + img2.getType().name().toLowerCase() + ")";
        }

        if (img1.getWidth() != img2.getWidth() || img1.getHeight() != img2.getHeight()) {
            return "Cannot make a collage from images with different dimensions!";
        }

        int channels = img1.getMagicNumber().equals("P3") ? 3 : 1;
        int newWidth;
        int newHeight;
        int[][][] newPixels;

        if (direction.equals("horizontal")) {

            newWidth = img1.getWidth() + img2.getWidth();
            newHeight = img1.getHeight();
            newPixels = new int[newHeight][newWidth][channels];

            for (int row = 0; row < newHeight; row++) {
                for (int col = 0; col < img1.getWidth(); col++) {
                    newPixels[row][col] = img1.getPixels()[row][col].clone();
                }
                for (int col = 0; col < img2.getWidth(); col++) {
                    newPixels[row][img1.getWidth() + col] = img2.getPixels()[row][col].clone();
                }
            }

        } else if (direction.equals("vertical")) {

            newWidth = img1.getWidth();
            newHeight = img1.getHeight() + img2.getHeight();
            newPixels = new int[newHeight][newWidth][channels];

            // Копираме пикселите на img1 отгоре
            for (int row = 0; row < img1.getHeight(); row++) {
                for (int col = 0; col < newWidth; col++) {
                    newPixels[row][col] = img1.getPixels()[row][col].clone();
                }
            }
            // Копираме пикселите на img2 отдолу
            for (int row = 0; row < img2.getHeight(); row++) {
                for (int col = 0; col < newWidth; col++) {
                    newPixels[img1.getHeight() + row][col] = img2.getPixels()[row][col].clone();
                }
            }

        } else {
            return "Invalid direction. Use horizontal or vertical.";
        }

        ImageFile newImage = new ImageFile(outName, img1.getMagicNumber(), newWidth, newHeight, img1.getMaxVal(), newPixels);
        images.add(newImage);
        return "New collage \"" + outName + "\" created";
    }
}