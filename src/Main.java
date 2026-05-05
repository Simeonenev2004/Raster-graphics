import rastereditor.cli.RasterCLI;

/**
 * Входна точка на програмата.
 * Създава RasterCLI обект и стартира главния цикъл.
 */

public class Main {

    public static void main(String... args) {
        RasterCLI cli = new RasterCLI();
        cli.run();
    }
}
