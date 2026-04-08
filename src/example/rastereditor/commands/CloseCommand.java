package example.rastereditor.commands;

import example.rastereditor.model.Session;

public class CloseCommand implements Command {

    @Override
    public CommandResult execute(String[] args, Session session) {

        if (session == null) {
            return new CommandResult("No active session.", session);
        }

        return new CommandResult("Successfully closed session " + session.getSessionId(), null);
    }
}
