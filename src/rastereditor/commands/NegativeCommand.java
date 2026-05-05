package rastereditor.commands;

import rastereditor.model.Session;
import rastereditor.transformations.Negative;

/**
 * Команда която добавя трансформацията negative към текущата сесия.
 * Трансформацията се прилага при следващото save или save as.
 */
public class NegativeCommand implements Command {

    /**
     * Изпълнява командата negative — добавя трансформацията към опашката.
     *
     * @param args    args[0] е "negative"
     * @param session текущата активна сесия
     * @return резултат със съобщение за успех или грешка
     */
    @Override
    public CommandResult execute(String[] args, Session session) {

        if (session == null) {
            return new CommandResult("No active session.", session);
        }

        session.addTransformation(new Negative());

        return new CommandResult("Transformation negative added.", session);
    }
}