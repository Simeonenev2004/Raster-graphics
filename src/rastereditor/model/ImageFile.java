package rastereditor.model;

import java.io.File;

public class ImageFile {

    private String filename;
    private ImageType type;
    private String magicNumber;
    private int width;
    private int height;
    private int maxVal;
    private int[][][] pixels;

    public ImageFile(String filename, String magicNumber, int width, int height, int maxVal, int[][][] pixels) {
        this.filename = filename;
        this.type = determineType(filename);
        this.magicNumber = magicNumber;
        this.width = width;
        this.height = height;
        this.maxVal = maxVal;
        this.pixels = pixels;
    }

    private ImageType determineType(String filename) {
        if (filename.endsWith(".ppm"))
            return ImageType.PPM;
        else if (filename.endsWith(".pgm"))
            return ImageType.PGM;
        else if (filename.endsWith(".pbm"))
            return ImageType.PBM;
        else throw new IllegalArgumentException("Unsupported file type: " + filename);
    }

    public String getShortName() {
        return new File(filename).getName();
    }

    public String getFilename() {
        return filename;
    }

    public ImageType getType() {
        return type;
    }

    public String getMagicNumber() {
        return magicNumber;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMaxVal() {
        return maxVal;
    }

    public int[][][] getPixels() {
        return pixels;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setPixels(int[][][] pixels) {
        this.pixels = pixels;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setMagicNumber(String magicNumber) {
        this.magicNumber = magicNumber;
    }
}