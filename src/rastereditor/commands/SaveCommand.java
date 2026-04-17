package rastereditor.commands;

import rastereditor.model.Session;
import rastereditor.model.ImageFile;
import rastereditor.file.FileHandler;

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
