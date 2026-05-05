package rastereditor.commands;

import rastereditor.model.Session;

/**
 * Представлява резултата от изпълнението на команда.
 * Съдържа съобщение за потребителя и текущата активна сесия след изпълнението.
 */
public class CommandResult {

    private String message;
    private Session session;

    /**
     * Създава нов резултат с дадено съобщение и сесия.
     *
     * @param message съобщението което ще се покаже на потребителя
     * @param session текущата активна сесия след изпълнението на командата
     */
    public CommandResult(String message, Session session) {
        this.message = message;
        this.session = session;
    }

    /**
     * Връща съобщението за потребителя.
     *
     * @return съобщението
     */
    public String getMessage() {
        return message;
    }

    /**
     * Връща текущата активна сесия след изпълнението на командата.
     *
     * @return активната сесия
     */
    public Session getSession() {
        return session;
    }
}