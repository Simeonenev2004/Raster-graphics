package rastereditor.commands;

import rastereditor.model.Session;
import rastereditor.model.ImageFile;
import rastereditor.file.FileHandler;
import java.util.Map;

/**
 * Команда за зареждане на един или повече файлове.
 * Създава нова потребителска сесия и зарежда изображенията в нея.
 * Ако се посочат няколко файла, всички се зареждат в същата сесия.
 */
public class LoadCommand implements Command {

    private Map<Integer, Session> sessions;
    private static int nextId = 1;

    /**
     * Създава нова LoadCommand с достъп до всички сесии.
     *
     * @param sessions картата с всички активни сесии
     */
    public LoadCommand(Map<Integer, Session> sessions) {
        this.sessions = sessions;
    }

    /**
     * Изпълнява командата load — създава нова сесия и зарежда файловете.
     *
     * @param args    args[0] е "load", args[1..n] са имената на файловете
     * @param session текущата сесия (не се използва, създава се нова)
     * @return резултат с новата сесия и съобщение за успех
     */
    @Override
    public CommandResult execute(String[] args, Session session) {

        if (args.length < 2) {
            return new CommandResult("Usage: load <file1> <file2> ...", session);
        }

        Session newSession = new Session(nextId++);
        sessions.put(newSession.getSessionId(), newSession);

        StringBuilder sb = new StringBuilder();
        sb.append("Session with ID: ").append(newSession.getSessionId()).append(" started\n");

        for (int i = 1; i < args.length; i++) {
            ImageFile img = FileHandler.load(args[i]);
            newSession.addImage(img);
            sb.append("Image \"").append(img.getShortName()).append("\" added\n");
        }

        return new CommandResult(sb.toString().trim(), newSession);
    }
}