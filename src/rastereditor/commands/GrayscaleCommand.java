package rastereditor.commands;

import rastereditor.model.Session;
import rastereditor.transformations.Grayscale;

/**
 * Команда която добавя трансформацията grayscale към текущата сесия.
 * Трансформацията се прилага при следващото save или save as.
 */
public class GrayscaleCommand implements Command {

    /**
     * Изпълнява командата grayscale — добавя трансформацията към опашката.
     *
     * @param args    args[0] е "grayscale"
     * @param session текущата активна сесия
     * @return резултат със съобщение за успех или грешка
     */
    @Override
    public CommandResult execute(String[] args, Session session) {

        if (session == null) {
            return new CommandResult("No active session.", session);
        }

        session.addTransformation(new Grayscale());

        return new CommandResult("Transformation grayscale added.", session);
    }
}