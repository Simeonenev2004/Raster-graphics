package example.rastereditor.commands;

import example.rastereditor.model.Session;

public class CommandResult {

    private String message;
    private Session session;

    public CommandResult(String message, Session session) {
        this.message = message;
        this.session = session;
    }

    public String getMessage() {
        return message;
    }

    public Session getSession() {
        return session;
    }
}
