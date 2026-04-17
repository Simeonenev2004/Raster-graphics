package rastereditor.commands;

import rastereditor.model.Session;

public interface Command {
    CommandResult execute(String[] args, Session session);
}
