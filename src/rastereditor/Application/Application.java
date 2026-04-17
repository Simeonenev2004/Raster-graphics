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
        int nextId = 1;

        StringBuilder sb = new StringBuilder();
        sb.append("Welcome to Simple Raster Editor!");
        System.out.println(sb);

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

            if (input.isEmpty()) continue;

            String[] parts = input.split(" ");

            String commandName;

            if (input.startsWith("save as")) {
                commandName = "save as";
            } else if (input.startsWith("session info")) {
                commandName = "session";
            } else {
                commandName = parts[0].toLowerCase();
            }

            Command command = commands.get(commandName);

            if (command != null) {

                CommandResult result = command.execute(parts, session);

                if (result.getMessage() != null) {
                    System.out.println(result.getMessage());
                }

                session = result.getSession();

            } else {
                System.out.println("Unknown command");
            }
        }
    }
}