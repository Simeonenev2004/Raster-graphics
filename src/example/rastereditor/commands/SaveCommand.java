package example.rastereditor.commands;

import example.rastereditor.model.Session;
import example.rastereditor.model.ImageFile;
import example.rastereditor.file.FileHandler;

public class SaveCommand implements Command {

    @Override
    public CommandResult execute(String[] args, Session session) {

        if (session == null) {
            return new CommandResult("No active session.", session);
        }

        StringBuilder sb = new StringBuilder();

        for (ImageFile img : session.getImages()) {
            FileHandler.save(img);
            sb.append("Saved ").append(img.getFilename()).append("\n");
        }

        return new CommandResult(sb.toString().trim(), session);
    }
}
