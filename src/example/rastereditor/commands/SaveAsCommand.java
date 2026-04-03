package example.rastereditor.commands;

import example.rastereditor.model.Session;
import example.rastereditor.model.ImageFile;
import example.rastereditor.file.FileHandler;

public class SaveAsCommand implements Command {

    @Override
    public CommandResult execute(String[] args, Session session) {

        if (session == null) {
            return new CommandResult("No active session.", session);
        }

        if (args.length < 3) {
            return new CommandResult("Usage: save as <file>", session);
        }

        ImageFile firstImage = session.getImages().get(0);

        String filename = args[2];

        FileHandler.saveAs(firstImage, filename);

        return new CommandResult("Saved as " + filename, session);
    }
}
