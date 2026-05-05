package rastereditor.commands;

import rastereditor.model.Session;
import rastereditor.transformations.Rotate;

/**
 * Команда която добавя трансформацията rotate към текущата сесия.
 * Приема посока "left" или "right" като аргумент.
 * Трансформацията се прилага при следващото save или save as.
 */
public class RotateCommand implements Command {

    /**
     * Изпълнява командата rotate — добавя трансформацията с посока към опашката.
     *
     * @param args    args[0] е "rotate", args[1] е посоката ("left" или "right")
     * @param session текущата активна сесия
     * @return резултат със съобщение за успех или грешка
     */
    @Override
    public CommandResult execute(String[] args, Session session) {

        if (session == null) {
            return new CommandResult("No active session.", session);
        }

        if (args.length < 2) {
            return new CommandResult("Usage: rotate <left/right>", session);
        }

        String direction = args[1];

        session.addTransformation(new Rotate(direction));

        return new CommandResult("Transformation rotate " + direction + " added.", session);
    }
}