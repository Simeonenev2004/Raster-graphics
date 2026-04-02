package example.rastereditor.commands;

import example.rastereditor.model.Session;
import example.rastereditor.model.ImageFile;
import example.rastereditor.file.FileHandler;

public class LoadCommand implements Command {

    private static int sessionId = 1;

    @Override
    public CommandResult execute(String[] args, Session session) {

        if (args.length < 2) {
            return new CommandResult("Usage: load <file>", session);
        }

        Session newSession = new Session(sessionId++);

        ImageFile img = FileHandler.load(args[1]);
        newSession.addImage(img);

        StringBuilder sb = new StringBuilder();
        sb.append("Session with ID: ")
                .append(newSession.getSessionId())
                .append(" started\n");

        sb.append("Image \"").append(img.getFilename()).append("\" added");

        return new CommandResult(sb.toString(), newSession);
    }
}
