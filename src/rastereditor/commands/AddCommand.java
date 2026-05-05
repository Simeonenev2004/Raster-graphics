package rastereditor.commands;

import rastereditor.model.Session;
import rastereditor.model.ImageFile;
import rastereditor.file.FileHandler;

/**
 * Команда за добавяне на изображение към текущата сесия.
 * Новото изображение се добавя без да се прилагат вече съществуващите трансформации.
 */
public class AddCommand implements Command {

    /**
     * Изпълнява командата add — зарежда файл и го добавя към текущата сесия.
     *
     * @param args    args[0] е "add", args[1] е името на файла
     * @param session текущата активна сесия
     * @return резултат със съобщение за успех или грешка
     */
    @Override
    public CommandResult execute(String[] args, Session session) {

        if (session == null) {
            return new CommandResult("No active session.", session);
        }

        if (args.length < 2) {
            return new CommandResult("Usage: add <file>", session);
        }

        ImageFile img = FileHandler.load(args[1]);
        session.addImage(img);

        return new CommandResult("Image \"" + img.getShortName() + "\" added", session);
    }
}