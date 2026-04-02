package example.rastereditor.commands;

import example.rastereditor.model.Session;

public class UndoCommand implements Command {

    @Override
    public CommandResult execute(String[] args, Session session) {

        if (session == null) {
            return new CommandResult("No active session.", session);
        }

        if (session.getTransformations().isEmpty()) {
            return new CommandResult("Nothing to undo.", session);
        }

        int lastIndex = session.getTransformations().size() - 1;
        session.getTransformations().remove(lastIndex);

        return new CommandResult("Last transformation removed.", session);
    }
}
