package example.rastereditor.commands;

import example.rastereditor.model.Session;

import java.util.Map;

public class SwitchCommand implements Command {

    private Map<Integer, Session> sessions;

    public SwitchCommand(Map<Integer, Session> sessions) {
        this.sessions = sessions;
    }

    @Override
    public CommandResult execute(String[] args, Session session) {

        if (args.length < 2) {
            return new CommandResult("Usage: switch <sessionId>", session);
        }

        int id;

        try {
            id = Integer.parseInt(args[1]);
        } catch (Exception e) {
            return new CommandResult("Invalid session ID.", session);
        }

        Session newSession = sessions.get(id);

        if (newSession == null) {
            return new CommandResult("Session not found.", session);
        }

        return new CommandResult(
                "You switched to session with ID: " + id,
                newSession
        );
    }
}
