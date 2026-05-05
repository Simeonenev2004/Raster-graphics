package rastereditor.commands;

import rastereditor.model.Session;

/**
 * Команда за затваряне на текущата сесия.
 * След затварянето текущата сесия се изчиства и програмата не може да изпълнява
 * други команди докато не се отвори нова сесия с load.
 */
public class CloseCommand implements Command {

    /**
     * Изпълнява командата close — затваря текущата сесия.
     *
     * @param args    args[0] е "close"
     * @param session текущата активна сесия
     * @return резултат с null сесия и съобщение за успех
     */
    @Override
    public CommandResult execute(String[] args, Session session) {

        if (session == null) {
            return new CommandResult("No active session.", session);
        }

        return new CommandResult("Successfully closed session " + session.getSessionId(), null);
    }
}