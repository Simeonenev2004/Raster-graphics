package example.rastereditor.app;

import example.rastereditor.model.Session;
import example.rastereditor.commands.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Session session = null;

        StringBuilder sb = new StringBuilder();
        sb.append("Welcome to Simple Raster Editor!");
        System.out.println(sb);

        Map<String, Command> commands = new HashMap<>();
        commands.put("load", new LoadCommand());
        commands.put("add", new AddCommand());
        commands.put("exit", new ExitCommand());

        commands.put("grayscale", new GrayscaleCommand());
        commands.put("negative", new NegativeCommand());
        commands.put("monochrome", new MonochromeCommand());
        commands.put("rotate", new RotateCommand());
        commands.put("undo", new UndoCommand());

        while (true) {

            System.out.print("> ");

            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }

            String[] parts = input.split(" ");
            String commandName = parts[0].toLowerCase();

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