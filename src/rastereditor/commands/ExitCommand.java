package rastereditor.commands;

import rastereditor.model.Session;

/**
 * Команда за излизане от програмата.
 * При изпълнение веднага прекратява работата на приложението.
 */
public class ExitCommand implements Command {

    /**
     * Изпълнява командата exit — прекратява програмата.
     *
     * @param args    args[0] е "exit"
     * @param session текущата активна сесия
     * @return не връща реален резултат, програмата се прекратява
     */
    @Override
    public CommandResult execute(String[] args, Session session) {
        System.exit(0);
        return new CommandResult(null, session);
    }
}