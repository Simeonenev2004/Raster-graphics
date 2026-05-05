package rastereditor.commands;

import rastereditor.model.Session;
import rastereditor.transformations.Monochrome;

/**
 * Команда която добавя трансформацията monochrome към текущата сесия.
 * Трансформацията се прилага при следващото save или save as.
 */
public class MonochromeCommand implements Command {

    /**
     * Изпълнява командата monochrome — добавя трансформацията към опашката.
     *
     * @param args    args[0] е "monochrome"
     * @param session текущата активна сесия
     * @return резултат със съобщение за успех или грешка
     */
    @Override
    public CommandResult execute(String[] args, Session session) {

        if (session == null) {
            return new CommandResult("No active session.", session);
        }

        session.addTransformation(new Monochrome());

        return new CommandResult("Transformation monochrome added.", session);
    }
}