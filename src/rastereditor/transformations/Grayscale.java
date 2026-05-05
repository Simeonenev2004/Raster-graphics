package rastereditor.transformations;

import rastereditor.model.ImageFile;

/**
 * Трансформация която преобразува цветно PPM изображение в сиво PGM изображение.
 * Използва стандартната формула за luminance: gray = 0.299*R + 0.587*G + 0.114*B.
 * Ако изображението вече е PGM или PBM, то не се променя.
 */
public class Grayscale extends Transformation {

    /**
     * Връща името на трансформацията.
     *
     * @return "grayscale"
     */
    @Override
    public String getName() {
        return "grayscale";
    }

    /**
     * Преобразува цветно PPM изображение в сиво PGM изображение.
     * Ако изображението не е PPM, то се връща непроменено.
     *
     * @param img изображението за преобразуване
     * @return ново PGM изображение или непроменено изображение ако не е PPM
     */
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