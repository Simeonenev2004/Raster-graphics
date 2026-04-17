package rastereditor.model;

public class ImageFile {

    private String filename;
    private ImageType type;

    public ImageFile(String filename) {
        this.filename = filename;
        this.type = determineType(filename);
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

    public String getFilename() {
        return filename;
    }

    public ImageType getType() {
        return type;
    }
}