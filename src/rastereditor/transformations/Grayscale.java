package rastereditor.transformations;

import rastereditor.model.ImageFile;

public class Grayscale extends Transformation {

    @Override
    public String getName() {
        return "grayscale";
    }

    @Override
    public ImageFile apply(ImageFile img) {
        if (!img.getMagicNumber().equals("P3")) {
            return img;
        }

        int width = img.getWidth();
        int height = img.getHeight();
        int maxVal = img.getMaxVal();
        int[][][] oldPixels = img.getPixels();

        int[][][] newPixels = new int[height][width][1];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int r = oldPixels[row][col][0];
                int g = oldPixels[row][col][1];
                int b = oldPixels[row][col][2];
                int gray = (int) Math.round(0.299 * r + 0.587 * g + 0.114 * b);

                newPixels[row][col][0] = gray;
            }
        }
        return new ImageFile(img.getFilename(), "P2", width, height, maxVal, newPixels);
    }
}