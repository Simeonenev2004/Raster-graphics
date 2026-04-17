package rastereditor.commands;

import rastereditor.model.Session;

public class ExitCommand implements Command {

    @Override
    public CommandResult execute(String[] args, Session session) {
        System.exit(0);
        return new CommandResult(null, session);
    }
}
