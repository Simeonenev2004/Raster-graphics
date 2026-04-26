package rastereditor.file;

import rastereditor.model.ImageFile;
import java.io.*;
import java.util.Scanner;

public class FileHandler {

    public static ImageFile load(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            file = new File("Images/" + new File(filename).getName());
        }
        if (!file.exists()) {
            throw new RuntimeException("File not found: " + filename);
        }

        try (Scanner sc = new Scanner(file)) {
            String magicNumber = readNextToken(sc);
            if (!magicNumber.equals("P1") && !magicNumber.equals("P2") && !magicNumber.equals("P3"))
                throw new RuntimeException("Unsupported format: " + magicNumber + ". Only P1, P2, P3 are supported.");

            int width = Integer.parseInt(readNextToken(sc));
            int height = Integer.parseInt(readNextToken(sc));
            int maxVal = magicNumber.equals("P1") ? 1 : Integer.parseInt(readNextToken(sc));

            int channels = magicNumber.equals("P3") ? 3 : 1;
            int[][][] pixels = new int[height][width][channels];

            for (int row = 0; row < height; row++)
                for (int col = 0; col < width; col++)
                    for (int ch = 0; ch < channels; ch++)
                        pixels[row][col][ch] = Integer.parseInt(readNextToken(sc));

            return new ImageFile(filename, magicNumber, width, height, maxVal, pixels);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + filename);
        }
    }

    public static void save(ImageFile img) {
        writeToFile(img, img.getFilename());
    }

    public static void saveAs(ImageFile img, String newFilename) {
        writeToFile(img, newFilename);
    }

    private static void writeToFile(ImageFile img, String path) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            pw.println(img.getMagicNumber());
            pw.println(img.getWidth() + " " + img.getHeight());
            if (!img.getMagicNumber().equals("P1")) pw.println(img.getMaxVal());

            int channels = img.getMagicNumber().equals("P3") ? 3 : 1;
            for (int row = 0; row < img.getHeight(); row++) {
                StringBuilder sb = new StringBuilder();
                for (int col = 0; col < img.getWidth(); col++)
                    for (int ch = 0; ch < channels; ch++) {
                        if (sb.length() > 0) sb.append(" ");
                        sb.append(img.getPixels()[row][col][ch]);
                    }
                pw.println(sb);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing file: " + path + " — " + e.getMessage());
        }
    }

    private static String readNextToken(Scanner sc) {
        while (sc.hasNext()) {
            String token = sc.next();
            if (token.startsWith("#")) {
                sc.nextLine();
                continue;
            }
            return token;
        }
        throw new RuntimeException("Unexpected end of file.");
    }
}