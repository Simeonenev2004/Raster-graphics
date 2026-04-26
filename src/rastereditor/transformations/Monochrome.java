package rastereditor.transformations;

import rastereditor.model.ImageFile;

public class Monochrome extends Transformation {

    @Override
    public String getName() {
        return "monochrome";
    }

    @Override
    public ImageFile apply(ImageFile img) {
        if (img.getMagicNumber().equals("P1")) {
            return img;
        }

        int width = img.getWidth();
        int height = img.getHeight();
        int maxVal = img.getMaxVal();
        int[][][] oldPixels = img.getPixels();
        int[][][] newPixels = new int[height][width][1];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int gray;
                if (img.getMagicNumber().equals("P3")) {
                    int r = oldPixels[row][col][0];
                    int g = oldPixels[row][col][1];
                    int b = oldPixels[row][col][2];
                    gray = (int) Math.round(0.299 * r + 0.587 * g + 0.114 * b);
                } else {
                    gray = oldPixels[row][col][0];
                }
                newPixels[row][col][0] = (gray > maxVal / 2) ? 0 : 1;
            }
        }
        return new ImageFile(img.getFilename(), "P1", width, height, 1, newPixels);
    }
}