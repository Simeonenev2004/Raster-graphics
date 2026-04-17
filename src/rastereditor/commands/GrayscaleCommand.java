package rastereditor.commands;

import rastereditor.model.Session;
import rastereditor.transformations.Grayscale;

public class GrayscaleCommand implements Command {

    @Override
    public CommandResult execute(String[] args, Session session) {

        if (session == null) {
            return new CommandResult("No active session.", session);
        }

        session.addTransformation(new Grayscale());

        return new CommandResult("Transformation grayscale added.", session);
    }
}
