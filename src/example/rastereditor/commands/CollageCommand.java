package example.rastereditor.commands;

import example.rastereditor.model.Session;

public class CollageCommand implements Command {

    @Override
    public CommandResult execute(String[] args, Session session) {

        if (session == null) {
            return new CommandResult("No active session.", session);
        }

        if (args.length < 5) {
            return new CommandResult("Usage: collage <horizontal/vertical> <img1> <img2> <outimg>", session);
        }

        String direction = args[1];
        String img1 = args[2];
        String img2 = args[3];
        String out = args[4];
        String result = session.createCollage(direction, img1, img2, out);

        return new CommandResult(result, session);
    }
}
