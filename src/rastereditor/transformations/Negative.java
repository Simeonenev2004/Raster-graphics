package rastereditor.transformations;

import rastereditor.model.ImageFile;

public class Negative extends Transformation {

    @Override
    public String getName() {
        return "negative";
    }

    @Override
    public ImageFile apply(ImageFile img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int maxVal = img.getMaxVal();
        int[][][] oldPixels = img.getPixels();
        int channels = img.getMagicNumber().equals("P3") ? 3 : 1;
        int[][][] newPixels = new int[height][width][channels];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                for (int ch = 0; ch < channels; ch++) {
                    newPixels[row][col][ch] = maxVal - oldPixels[row][col][ch];
                }
            }
        }
        return new ImageFile(img.getFilename(), img.getMagicNumber(), width, height, maxVal, newPixels);
    }
}
