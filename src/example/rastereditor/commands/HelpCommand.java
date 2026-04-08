package example.rastereditor.commands;

import example.rastereditor.model.Session;

public class HelpCommand implements Command {

    @Override
    public CommandResult execute(String[] args, Session session) {

        StringBuilder sb = new StringBuilder();

        sb.append("The following commands are supported:\n");
        sb.append("load <file> - opens file\n");
        sb.append("add <file> - adds image to session\n");
        sb.append("save - saves all images\n");
        sb.append("save as <file> - saves first image as new file\n");
        sb.append("close - closes current session\n");
        sb.append("exit - exits the program\n");
        sb.append("grayscale - adds grayscale transformation\n");
        sb.append("monochrome - adds monochrome transformation\n");
        sb.append("negative - adds negative transformation\n");
        sb.append("rotate <left/right> - rotates images\n");
        sb.append("undo - removes last transformation\n");
        sb.append("session info - prints session info\n");
        sb.append("switch <id> - switches session\n");
        sb.append("collage <horizontal/vertical> <img1> <img2> <out> - creates collage");

        return new CommandResult(sb.toString(), session);
    }
}
