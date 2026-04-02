package example.rastereditor.commands;

import example.rastereditor.model.Session;
import example.rastereditor.transformations.Rotate;

public class RotateCommand implements Command {

    @Override
    public CommandResult execute(String[] args, Session session) {

        if (session == null) {
            return new CommandResult("No active session.", session);
        }

        if (args.length < 2) {
            return new CommandResult("Usage: rotate <left/right>", session);
        }

        String direction = args[1];

        session.addTransformation(new Rotate(direction));

        return new CommandResult("Transformation rotate " + direction + " added.", session);
    }
}
