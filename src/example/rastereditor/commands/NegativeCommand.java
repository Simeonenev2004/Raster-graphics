package example.rastereditor.commands;

import example.rastereditor.model.Session;
import example.rastereditor.transformations.Negative;

public class NegativeCommand implements Command {

    @Override
    public CommandResult execute(String[] args, Session session) {

        if (session == null) {
            return new CommandResult("No active session.", session);
        }

        session.addTransformation(new Negative());

        return new CommandResult("Transformation negative added.", session);
    }
}
