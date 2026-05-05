package rastereditor.commands;

import rastereditor.model.Session;

/**
 * Команда която извежда информация за всички поддържани команди.
 */
public class HelpCommand implements Command {

    /**
     * Изпълнява командата help — показва списък с всички команди.
     *
     * @param args    args[0] е "help"
     * @param session текущата активна сесия
     * @return резултат с описание на всички команди
     */
    @Override
    public CommandResult execute(String[] args, Session session) {

        StringBuilder sb = new StringBuilder();

        sb.append("Commands:\n");
        sb.append("load <files>\n");
        sb.append("add <file>\n");
        sb.append("save\n");
        sb.append("save as <file>\n");
        sb.append("grayscale\n");
        sb.append("monochrome\n");
        sb.append("negative\n");
        sb.append("rotate <left/right>\n");
        sb.append("undo\n");
        sb.append("session info\n");
        sb.append("switch <id>\n");
        sb.append("collage <direction> <img1> <img2> <out>\n");
        sb.append("exit");

        return new CommandResult(sb.toString(), session);
    }
}