package rastereditor.commands;

import rastereditor.model.Session;

/**
 * Команда за показване на информация за текущата сесия.
 * Извежда имената на заредените изображения и чакащите трансформации.
 */
public class SessionInfoCommand implements Command {

    /**
     * Изпълнява командата session info — показва информация за текущата сесия.
     *
     * @param args    args[0] е "session"
     * @param session текущата активна сесия
     * @return резултат с информация за сесията
     */
    @Override
    public CommandResult execute(String[] args, Session session) {

        if (session == null) {
            return new CommandResult("No active session.", session);
        }

        return new CommandResult(session.getSessionInfo(), session);
    }
}