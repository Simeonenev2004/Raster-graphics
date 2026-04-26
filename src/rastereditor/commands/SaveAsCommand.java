package rastereditor.commands;

import rastereditor.model.ImageFile;
import rastereditor.model.Session;
import rastereditor.transformations.Transformation;
import rastereditor.file.FileHandler;

public class SaveAsCommand implements Command {

    @Override
    public CommandResult execute(String[] args, Session session) {

        if (session == null) {
            return new CommandResult("No active session.", session);
        }

        if (args.length < 2) {
            return new CommandResult("Usage: save as <file>", session);
        }

        ImageFile firstImage = session.getImages().get(0);
        String newFilename = args[1];

        ImageFile result = firstImage;
        for (Transformation t : session.getTransformations()) {
            result = t.apply(result);
        }

        result.setFilename(newFilename);
        FileHandler.saveAs(result, newFilename);

        return new CommandResult("Successfully saved " + newFilename, session);
    }
}