package example.rastereditor.commands;

import example.rastereditor.model.Session;

public interface Command {
    CommandResult execute(String[] args, Session session);
}
