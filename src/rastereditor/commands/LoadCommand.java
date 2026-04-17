package rastereditor.commands;

import rastereditor.model.Session;
import rastereditor.model.ImageFile;
import rastereditor.file.FileHandler;

import java.util.Map;

public class LoadCommand implements Command {

    private Map<Integer, Session> sessions;
    private static int nextId = 1;

    public LoadCommand(Map<Integer, Session> sessions) {
        this.sessions = sessions;
    }

    @Override
    public CommandResult execute(String[] args, Session session) {

        if (args.length < 2) {
            return new CommandResult("Usage: load <file1> <file2> ...", session);
        }

        Session newSession = new Session(nextId++);
        sessions.put(newSession.getSessionId(), newSession);

        StringBuilder sb = new StringBuilder();
        sb.append("Session with ID: ").append(newSession.getSessionId()).append(" started\n");

        for (int i = 1; i < args.length; i++) {
            ImageFile img = FileHandler.load(args[i]);
            newSession.addImage(img);
            sb.append("Image \"").append(args[i]).append("\" added\n");
        }

        return new CommandResult(sb.toString().trim(), newSession);
    }
}
