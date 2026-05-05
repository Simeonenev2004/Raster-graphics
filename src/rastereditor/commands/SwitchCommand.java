package rastereditor.commands;

import rastereditor.model.Session;

import java.util.Map;

/**
 * Команда за превключване към друга активна сесия.
 * Ако сесия с дадения идентификационен номер не съществува, извежда грешка.
 */
public class SwitchCommand implements Command {

    private Map<Integer, Session> sessions;

    /**
     * Създава нова SwitchCommand с достъп до всички сесии.
     *
     * @param sessions картата с всички активни сесии
     */
    public SwitchCommand(Map<Integer, Session> sessions) {
        this.sessions = sessions;
    }

    /**
     * Изпълнява командата switch — превключва към сесия с даден номер.
     *
     * @param args    args[0] е "switch", args[1] е идентификационният номер на сесията
     * @param session текущата активна сесия
     * @return резултат с новата активна сесия или съобщение за грешка
     */
    @Override
    public CommandResult execute(String[] args, Session session) {

        if (args.length < 2) {
            return new CommandResult("Usage: switch <id>", session);
        }

        int id = Integer.parseInt(args[1]);

        if (!sessions.containsKey(id)) {
            return new CommandResult("No such session.", session);
        }

        Session newSession = sessions.get(id);

        return new CommandResult(
                "You switched to session with ID: " + id + "!",
                newSession
        );
    }
}