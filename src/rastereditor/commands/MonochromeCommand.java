package rastereditor.commands;

import rastereditor.model.Session;
import rastereditor.transformations.Monochrome;

public class MonochromeCommand implements Command {

    @Override
    public CommandResult execute(String[] args, Session session) {

        if (session == null) {
            return new CommandResult("No active session.", session);
        }

        session.addTransformation(new Monochrome());

        return new CommandResult("Transformation monochrome added.", session);
    }
}
