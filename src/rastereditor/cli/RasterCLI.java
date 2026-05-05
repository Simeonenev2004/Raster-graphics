package rastereditor.cli;

import rastereditor.commands.*;
import rastereditor.model.Session;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Този клас управлява главното меню (CLI) на проекта.
 * Отговаря за четенето на потребителски команди, тяхното разпознаване
 * и изпращането им към съответния команден клас за изпълнение.
 */
public class RasterCLI {

    private final Map<String, Command> commands = new HashMap<>();
    private Session session = null;
    private final Map<Integer, Session> sessions = new HashMap<>();

    /**
     * Конструктор който инициализира всички поддържани команди.
     */
    public RasterCLI() {
        initializeCommands();
    }

    /**
     * Метод чрез който програмата изпълнява цикъла за меню.
     * Командите се въвеждат от потребителя, обработват се и се
     * изпращат към съответния клас на всяка команда.
     * При невалидни команди се извеждат съобщения за грешка.
     */
    public void run() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Simple Raster Editor!");

        while (true) {

            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;

            String commandName;
            if (input.startsWith("save as")) {
                commandName = "save as";
            } else if (input.startsWith("session info")) {
                commandName = "session";
            } else {
                commandName = input.split(" ")[0].toLowerCase();
            }

            String[] parts;
            if (commandName.equals("save as")) {
                String filename = input.substring("save as".length()).trim();
                filename = filename.replace("\"", "");
                parts = new String[]{"save as", filename};
            } else {
                parts = input.split(" ");
            }

            if (!commands.containsKey(commandName)) {
                System.out.println("Unknown command. Type 'help' for a list of commands.");
                continue;
            }

            try {
                CommandResult result = commands.get(commandName).execute(parts, session);
                if (result.getMessage() != null) {
                    System.out.println(result.getMessage());
                }
                session = result.getSession();
            } catch (RuntimeException e) {

                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    /**
     * Метод който регистрира поддържаните команди в колекцията {@code commands}.
     */
    private void initializeCommands() {
        commands.put("load", new LoadCommand(sessions));
        commands.put("add", new AddCommand());
        commands.put("save", new SaveCommand());
        commands.put("save as", new SaveAsCommand());
        commands.put("close", new CloseCommand());
        commands.put("help", new HelpCommand());
        commands.put("exit", new ExitCommand());
        commands.put("grayscale", new GrayscaleCommand());
        commands.put("monochrome", new MonochromeCommand());
        commands.put("negative", new NegativeCommand());
        commands.put("rotate", new RotateCommand());
        commands.put("undo", new UndoCommand());
        commands.put("session", new SessionInfoCommand());
        commands.put("switch", new SwitchCommand(sessions));
        commands.put("collage", new CollageCommand());
    }
}