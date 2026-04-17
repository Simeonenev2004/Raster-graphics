package rastereditor.commands;

import rastereditor.model.Session;

public class SessionInfoCommand implements Command {

    @Override
    public CommandResult execute(String[] args, Session session) {

        if (session == null) {
            return new CommandResult("No active session.", session);
        }

        return new CommandResult(session.getSessionInfo(), session);
    }
}
