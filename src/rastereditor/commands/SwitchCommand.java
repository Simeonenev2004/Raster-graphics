package rastereditor.commands;

import rastereditor.model.Session;

import java.util.Map;

public class SwitchCommand implements Command {

    private Map<Integer, Session> sessions;

    public SwitchCommand(Map<Integer, Session> sessions) {
        this.sessions = sessions;
    }

    @Override
    public CommandResult execute(String[] args, Session session) {

        if (args.length < 2) {
            return new CommandResult("Usage: switch <id>", session);
        }

        int id = Integer.parseInt(args[1]);

        if (!sessions.containsKey(id)) {
            return new CommandResult("No such session.", session);
        }

        Session newSession = sessions.get(id);

        return new CommandResult(
                "You switched to session with ID: " + id + "!",
                newSession
        );
    }
}
