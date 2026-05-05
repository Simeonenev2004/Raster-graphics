package rastereditor.commands;

import rastereditor.model.ImageFile;
import rastereditor.model.Session;
import rastereditor.transformations.Transformation;
import rastereditor.file.FileHandler;

/**
 * Команда за записване на първото изображение от сесията под ново име.
 * Прилага всички чакащи трансформации преди записването.
 * За разлика от save, записва само първото изображение.
 * След записването изчиства списъка с трансформации.
 */
public class SaveAsCommand implements Command {

    /**
     * Изпълнява командата save as — прилага трансформациите и записва под ново име.
     *
     * @param args    args[0] е "save as", args[1] е новото име на файла
     * @param session текущата активна сесия
     * @return резултат със съобщение за успех или грешка
     */
    @Override
    public CommandResult execute(String[] args, Session session) {

        if (session == null) {
            return new CommandResult("No active session.", session);
        }

        if (args.length < 2) {
            return new CommandResult("Usage: save as <file>", session);
        }

        ImageFile firstImage = session.getImages().get(0);
        String newFilename = args[1];

        ImageFile result = firstImage;
        for (Transformation t : session.getTransformations()) {
            result = t.apply(result);
        }

        FileHandler.saveAs(result, newFilename);

        session.getTransformations().clear();

        return new CommandResult("Successfully saved " + newFilename, session);
    }
}