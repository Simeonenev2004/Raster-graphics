package example.rastereditor.app;

import example.rastereditor.model.Session;
import example.rastereditor.model.ImageFile;
import example.rastereditor.file.FileHandler;
import example.rastereditor.transformations.Grayscale;
import example.rastereditor.transformations.Monochrome;
import example.rastereditor.transformations.Negative;
import example.rastereditor.transformations.Rotate;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Map<Integer, Session> sessions = new HashMap<>();
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
                        sessions.put(session.getSessionId(), session);
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

                case "negative":

                    if (session == null) {
                        sb = new StringBuilder();
                        sb.append("No active session.");
                        System.out.println(sb);
                        break;
                    }

                    session.addTransformation(new Negative());

                    sb = new StringBuilder();
                    sb.append("Transformation negative added.");
                    System.out.println(sb);

                    break;

                case "monochrome":

                    if (session == null) {
                        sb = new StringBuilder();
                        sb.append("No active session.");
                        System.out.println(sb);
                        break;
                    }

                    session.addTransformation(new Monochrome());

                    sb = new StringBuilder();
                    sb.append("Transformation monochrome added.");
                    System.out.println(sb);

                    break;

                case "rotate":

                    if (session == null) {
                        sb = new StringBuilder();
                        sb.append("No active session.");
                        System.out.println(sb);
                        break;
                    }

                    if (parts.length < 2) {
                        sb = new StringBuilder();
                        sb.append("Usage: rotate <left/right>");
                        System.out.println(sb);
                        break;
                    }

                    session.addTransformation(new Rotate(parts[1]));

                    sb = new StringBuilder();
                    sb.append("Transformation rotate ").append(parts[1]).append(" added.");
                    System.out.println(sb);

                    break;

                case "undo":
                    if (session == null) {
                        sb = new StringBuilder();
                        sb.append("No active session.");
                        System.out.println(sb);
                        break;
                    }
                    session.undoLastTransformation();

                    break;

                case "save":
                    if (session == null) {
                        sb = new StringBuilder();
                        sb.append("No active session.");
                        System.out.println(sb);
                        break;
                    }
                    for (ImageFile i : session.getImages()) {
                        FileHandler.save(i);
                    }
                    sb = new StringBuilder();
                    sb.append("All images saved with transformations applied.");
                    System.out.println(sb);
                    break;

                case "save as":
                    if (session == null) {
                        sb = new StringBuilder();
                        sb.append("No active session.");
                        System.out.println(sb);
                        break;
                    }
                    if (parts.length < 2) {
                        sb = new StringBuilder();
                        sb.append("Usage: save as <filename>");
                        System.out.println(sb);
                        break;
                    }
                    FileHandler.saveAs(session.getImages().get(0), parts[1]);
                    sb = new StringBuilder();
                    sb.append("First image saved as ").append(parts[1]).append(" with transformations.");
                    System.out.println(sb);
                    break;

                case "switch":
                    if (parts.length < 2) {
                        sb = new StringBuilder();
                        sb.append("Usage: switch <sessionId>");
                        System.out.println(sb);
                        break;
                    }
                    int switchId;
                    try {
                        switchId = Integer.parseInt(parts[1]);
                    } catch (NumberFormatException e) {
                        sb = new StringBuilder();
                        sb.append("Invalid session ID.");
                        System.out.println(sb);
                        break;
                    }
                    if (!sessions.containsKey(switchId)) {
                        sb = new StringBuilder();
                        sb.append("Session with ID ").append(switchId).append(" does not exist.");
                        System.out.println(sb);
                        break;
                    }
                    session = sessions.get(switchId);
                    sb = new StringBuilder();
                    sb.append("Switched to session with ID: ").append(switchId);
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

                case "collage":

                    if (session == null) {
                        sb = new StringBuilder();
                        sb.append("No active session.");
                        System.out.println(sb);
                        break;
                    }

                    if (parts.length < 5) {
                        sb = new StringBuilder();
                        sb.append("Usage: collage <direction> <img1> <img2> <out>");
                        System.out.println(sb);
                        break;
                    }

                    String direction = parts[1];
                    String img1 = parts[2];
                    String img2 = parts[3];
                    String out = parts[4];

                    session.createCollage(direction, img1, img2, out);

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