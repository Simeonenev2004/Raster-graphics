package rastereditor.Application;

import rastereditor.commands.*;
import rastereditor.model.Session;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Map<Integer, Session> sessions = new HashMap<>();
        Session session = null;

        System.out.println("Welcome to Simple Raster Editor!");

        Map<String, Command> commands = new HashMap<>();
        commands.put("load", new LoadCommand(sessions));
        commands.put("add", new AddCommand());
        commands.put("exit", new ExitCommand());
        commands.put("grayscale", new GrayscaleCommand());
        commands.put("negative", new NegativeCommand());
        commands.put("monochrome", new MonochromeCommand());
        commands.put("rotate", new RotateCommand());
        commands.put("undo", new UndoCommand());
        commands.put("session", new SessionInfoCommand());
        commands.put("save", new SaveCommand());
        commands.put("save as", new SaveAsCommand());
        commands.put("close", new CloseCommand());
        commands.put("collage", new CollageCommand());
        commands.put("switch", new SwitchCommand(sessions));
        commands.put("help", new HelpCommand());

        while (true) {

            System.out.print("> ");
            String input = sc.nextLine().trim();

            if (input.isEmpty())
                continue;

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

            Command command = commands.get(commandName);

            if (command != null) {
                try {
                    CommandResult result = command.execute(parts, session);
                    if (result.getMessage() != null) {
                        System.out.println(result.getMessage());
                    }
                    session = result.getSession();
                } catch (RuntimeException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else {
                System.out.println("Unknown command: " + commandName);
            }
        }
    }
}