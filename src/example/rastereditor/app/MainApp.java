package example.rastereditor.app;


import example.rastereditor.model.Session;
import example.rastereditor.model.ImageFile;
import example.rastereditor.file.FileHandler;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Session session = null;
        int sessionId = 1;

        System.out.println("Welcome to Simple Raster Editor!");

        while (true) {
            System.out.print("> ");
            String input = sc.nextLine().trim();
            String[] parts = input.split(" ");
            String command = parts[0].toLowerCase();

            switch (command) {
                case "load":
                    if (parts.length < 2) {
                        System.out.println("Usage: load <filename>");
                        break;
                    }
                    session = new Session(sessionId++);
                    ImageFile img = FileHandler.load(parts[1]);
                    session.addImage(img);
                    System.out.println("Session with ID: " + session.getSessionId() + " started");
                    session.listImages();
                    break;

                case "add":
                    if (session == null) {
                        System.out.println("No active session. Use load first.");
                        break;
                    }
                    if (parts.length < 2) {
                        System.out.println("Usage: add <filename>");
                        break;
                    }
                    ImageFile newImg = FileHandler.load(parts[1]);
                    session.addImage(newImg);
                    System.out.println("Image \"" + newImg.getFilename() + "\" added");
                    break;

                case "save":
                    if (session == null) {
                        System.out.println("No active session.");
                        break;
                    }
                    for (ImageFile i : session.getImages()) {
                        FileHandler.save(i);
                    }
                    break;

                case "save as":
                    if (session == null) {
                        System.out.println("No active session.");
                        break;
                    }
                    if (parts.length < 2) {
                        System.out.println("Usage: saveas <filename>");
                        break;
                    }
                    FileHandler.saveAs(session.getImages().get(0), parts[1]);
                    break;

                case "close":
                    if (session == null) {
                        System.out.println("No active session.");
                        break;
                    }
                    System.out.println("Session " + session.getSessionId() + " closed");
                    session = null;
                    break;

                case "exit":
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Unknown command. Supported: load, add, save, save as, close, exit");
            }
        }
    }
}