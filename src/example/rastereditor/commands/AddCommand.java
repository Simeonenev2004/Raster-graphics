package example.rastereditor.commands;

import example.rastereditor.model.Session;
import example.rastereditor.model.ImageFile;
import example.rastereditor.file.FileHandler;

public class AddCommand implements Command {

    @Override
    public CommandResult execute(String[] args, Session session) {

        if (session == null) {
            return new CommandResult("No active session.", session);
        }

        if (args.length < 2) {
            return new CommandResult("Usage: add <file>", session);
        }

        ImageFile img = FileHandler.load(args[1]);
        session.addImage(img);

        StringBuilder sb = new StringBuilder();
        sb.append("Image \"").append(img.getFilename()).append("\" added");

        return new CommandResult(sb.toString(), session);
    }
}
