package rastereditor.commands;

import rastereditor.model.Session;

/**
 * Команда за създаване на колаж от две изображения в текущата сесия.
 * Двете изображения трябва да са от един и същ тип и да имат еднакви размери.
 * Резултантният колаж се добавя като ново изображение в сесията.
 */
public class CollageCommand implements Command {

    /**
     * Изпълнява командата collage — създава колаж от две изображения.
     *
     * @param args    args[0] е "collage", args[1] е посоката ("horizontal" или "vertical"),
     *                args[2] и args[3] са имената на изображенията, args[4] е името на изходния файл
     * @param session текущата активна сесия
     * @return резултат със съобщение за успех или грешка
     */
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