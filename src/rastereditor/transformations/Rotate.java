package rastereditor.transformations;

import rastereditor.model.ImageFile;

/**
 * Трансформация която завърта изображението на 90 градуса наляво или надясно.
 * След завъртане ширината и височината на изображението се разменят.
 */
public class Rotate extends Transformation {

    private String direction;

    /**
     * Създава нова Rotate трансформация с дадена посока.
     *
     * @param direction посоката на завъртане — "left" или "right"
     */
    public Rotate(String direction) {
        this.direction = direction;
    }

    /**
     * Връща името на трансформацията заедно с посоката.
     *
     * @return "rotate left" или "rotate right"
     */
    @Override
    public String getName() {
        return "rotate " + direction;
    }

    /**
     * Завърта изображението на 90 градуса в зададената посока.
     * При завъртане надясно: newPixels[col][height-1-row] = oldPixels[row][col].
     * При завъртане наляво: newPixels[width-1-col][row] = oldPixels[row][col].
     *
     * @param img изображението за завъртане
     * @return ново завъртяно изображение
     */
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