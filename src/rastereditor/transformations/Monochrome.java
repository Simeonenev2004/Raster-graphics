package rastereditor.transformations;

import rastereditor.model.ImageFile;

/**
 * Трансформация която преобразува изображение до монохромно (само черно и бяло).
 * Ако изображението е PPM, първо се преобразува в сиво, след което се прилага праг.
 * Ако изображението вече е PBM, то не се променя.
 * Забележка: В PBM формата 0 означава бяло, а 1 означава черно.
 */
public class Monochrome extends Transformation {

    /**
     * Връща името на трансформацията.
     *
     * @return "monochrome"
     */
    @Override
    public String getName() {
        return "monochrome";
    }

    /**
     * Преобразува изображението до монохромно PBM изображение.
     * Използва праг maxVal/2 — пиксели над прага стават бели (0),
     * пиксели под или равни на прага стават черни (1).
     *
     * @param img изображението за преобразуване
     * @return ново PBM изображение или непроменено ако вече е PBM
     */
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