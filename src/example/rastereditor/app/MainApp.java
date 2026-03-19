package example.rastereditor.app;

import example.rastereditor.model.Session;
import example.rastereditor.model.ImageFile;
import example.rastereditor.file.FileHandler;
import example.rastereditor.transformations.Grayscale;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Session session = null;
        int sessionId = 1;

        StringBuilder sb = new StringBuilder();
        sb.append("Welcome to Simple Raster Editor!");
        System.out.println(sb);

        while (true) {

            System.out.print("> ");

            String input = sc.nextLine().trim();
            String[] parts = input.split(" ");
            String command = parts[0].toLowerCase();

            switch (command) {

                case "load":

                    if (parts.length < 2) {
                        sb = new StringBuilder();
                        sb.append("Usage: load <filename>");
                        System.out.println(sb);
                        break;
                    }

                    session = new Session(sessionId++);

                    ImageFile img = FileHandler.load(parts[1]);
                    session.addImage(img);

                    sb = new StringBuilder();
                    sb.append("Session with ID: ")
                            .append(session.getSessionId())
                            .append(" started");

                    System.out.println(sb);

                    break;

                case "grayscale":

                    if (session == null) {
                        sb = new StringBuilder();
                        sb.append("No active session.");
                        System.out.println(sb);
                        break;
                    }

                    session.addTransformation(new Grayscale());

                    sb = new StringBuilder();
                    sb.append("Transformation grayscale added.");
                    System.out.println(sb);

                    break;

                case "session":

                    if (session == null) {
                        sb = new StringBuilder();
                        sb.append("No active session.");
                        System.out.println(sb);
                        break;
                    }

                    if (parts.length > 1 && parts[1].equals("info")) {
                        session.printSessionInfo();
                    }

                    break;

                case "exit":

                    sb = new StringBuilder();
                    sb.append("Exiting...");
                    System.out.println(sb);

                    sc.close();
                    return;

                default:

                    sb = new StringBuilder();
                    sb.append("Unknown command.");
                    System.out.println(sb);
            }
        }
    }
}