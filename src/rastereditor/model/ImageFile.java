package rastereditor.model;

import java.io.File;

/**
 * Представлява едно растерно изображение заредено в паметта.
 * Поддържа три формата: PPM (цветно), PGM (сиво) и PBM (черно-бяло).
 * Пикселните данни се пазят като триизмерен масив [ред][колона][канал],
 * където PPM има 3 канала (R, G, B), а PGM и PBM имат 1 канал.
 */
public class ImageFile {

    private String filename;
    private ImageType type;
    private String magicNumber;
    private int width;
    private int height;
    private int maxVal;
    private int[][][] pixels;

    /**
     * Създава ново изображение с дадените параметри.
     *
     * @param filename    пътят до файла
     * @param magicNumber magic number от Netpbm формата (P1, P2 или P3)
     * @param width       ширината на изображението в пиксели
     * @param height      височината на изображението в пиксели
     * @param maxVal      максималната стойност на пиксел (1 за PBM, обикновено 255 за другите)
     * @param pixels      триизмерен масив с пикселните данни [ред][колона][канал]
     */
    public ImageFile(String filename, String magicNumber, int width, int height, int maxVal, int[][][] pixels) {
        this.filename = filename;
        this.type = determineType(filename);
        this.magicNumber = magicNumber;
        this.width = width;
        this.height = height;
        this.maxVal = maxVal;
        this.pixels = pixels;
    }

    /**
     * Определя типа на изображението според разширението на файла.
     *
     * @param filename името на файла
     * @return типът на изображението като {@link ImageType}
     * @throws IllegalArgumentException ако разширението не е .ppm, .pgm или .pbm
     */
    private ImageType determineType(String filename) {
        if (filename.endsWith(".ppm"))
            return ImageType.PPM;
        else if (filename.endsWith(".pgm"))
            return ImageType.PGM;
        else if (filename.endsWith(".pbm"))
            return ImageType.PBM;
        else throw new IllegalArgumentException("Unsupported file type: " + filename);
    }

    /**
     * Връща само името на файла без пълния път.
     * Например "/home/user/img1.ppm" връща "img1.ppm".
     *
     * @return краткото име на файла
     */
    public String getShortName() {
        return new File(filename).getName();
    }

    /**
     * Връща пълния път до файла.
     *
     * @return пълният път до файла
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Връща типа на изображението (PPM, PGM или PBM).
     *
     * @return типът на изображението
     */
    public ImageType getType() {
        return type;
    }

    /**
     * Връща magic number на файла (P1, P2 или P3).
     *
     * @return magic number
     */
    public String getMagicNumber() {
        return magicNumber;
    }

    /**
     * Връща ширината на изображението в пиксели.
     *
     * @return ширината
     */
    public int getWidth() {
        return width;
    }

    /**
     * Връща височината на изображението в пиксели.
     *
     * @return височината
     */
    public int getHeight() {
        return height;
    }

    /**
     * Връща максималната стойност на пиксел.
     *
     * @return максималната стойност
     */
    public int getMaxVal() {
        return maxVal;
    }

    /**
     * Връща пикселните данни като триизмерен масив.
     *
     * @return масивът с пиксели [ред][колона][канал]
     */
    public int[][][] getPixels() {
        return pixels;
    }

    /**
     * Задава нов път до файла.
     *
     * @param filename новият път
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Задава нови пикселни данни.
     *
     * @param pixels новият масив с пиксели
     */
    public void setPixels(int[][][] pixels) {
        this.pixels = pixels;
    }

    /**
     * Задава нова ширина.
     *
     * @param width новата ширина
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Задава нова височина.
     *
     * @param height новата височина
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Задава нов magic number.
     *
     * @param magicNumber новият magic number
     */
    public void setMagicNumber(String magicNumber) {

        this.magicNumber = magicNumber;
    }
}