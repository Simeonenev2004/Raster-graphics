package rastereditor.transformations;

import rastereditor.model.ImageFile;

public class Rotate extends Transformation {
    private String direction;

    public Rotate(String direction) {
        this.direction = direction;
    }

    @Override
    public String getName() {
        return "rotate " + direction;
    }

    @Override
    public ImageFile apply(ImageFile img) {
        int oldWidth = img.getWidth();
        int oldHeight = img.getHeight();
        int[][][] oldPixels = img.getPixels();
        int channels = img.getMagicNumber().equals("P3") ? 3 : 1;
        int newWidth = oldHeight;
        int newHeight = oldWidth;
        int[][][] newPixels = new int[newHeight][newWidth][channels];

        for (int row = 0; row < oldHeight; row++) {
            for (int col = 0; col < oldWidth; col++) {
                if (direction.equals("right")) {
                    newPixels[col][oldHeight - 1 - row] = oldPixels[row][col].clone();
                } else {
                    newPixels[oldWidth - 1 - col][row] = oldPixels[row][col].clone();
                }
            }
        }
        return new ImageFile(img.getFilename(), img.getMagicNumber(), newWidth, newHeight, img.getMaxVal(), newPixels);
    }
}