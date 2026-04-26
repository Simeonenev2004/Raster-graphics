package rastereditor.commands;

import rastereditor.model.ImageFile;
import rastereditor.model.Session;
import rastereditor.transformations.Transformation;
import rastereditor.file.FileHandler;

public class SaveCommand implements Command {

    @Override
    public CommandResult execute(String[] args, Session session) {

        if (session == null) {
            return new CommandResult("No active session.", session);
        }

        StringBuilder sb = new StringBuilder();

        for (ImageFile img : session.getImages()) {
            ImageFile result = img;
            for (Transformation t : session.getTransformations()) {
                result = t.apply(result);
            }

            result.setFilename(img.getFilename());
            FileHandler.save(result);

            sb.append("Successfully saved ").append(img.getFilename()).append("\n");
        }

        session.getTransformations().clear();

        return new CommandResult(sb.toString().trim(), session);
    }
}
