package rastereditor.commands;

import rastereditor.model.Session;

/**
 * Команда за отмяна на последно добавената трансформация в текущата сесия.
 * Ако няма трансформации за отмяна, не прави нищо.
 */
public class UndoCommand implements Command {

    /**
     * Изпълнява командата undo — премахва последната трансформация от опашката.
     *
     * @param args    args[0] е "undo"
     * @param session текущата активна сесия
     * @return резултат със съобщение за резултата
     */
    @Override
    public CommandResult execute(String[] args, Session session) {

        if (session == null) {
            return new CommandResult("No active session.", session);
        }

        if (session.getTransformations().isEmpty()) {
            return new CommandResult("Nothing to undo.", session);
        }

        int lastIndex = session.getTransformations().size() - 1;
        session.getTransformations().remove(lastIndex);

        return new CommandResult("Last transformation removed.", session);
    }
}