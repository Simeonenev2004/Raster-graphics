package rastereditor.commands;

import rastereditor.model.ImageFile;
import rastereditor.model.Session;
import rastereditor.transformations.Transformation;
import rastereditor.file.FileHandler;

/**
 * Команда за записване на всички изображения в текущата сесия.
 * Преди записване прилага всички чакащи трансформации върху всяко изображение.
 * След успешно записване изчиства списъка с трансформации.
 */
public class SaveCommand implements Command {

    /**
     * Изпълнява командата save — прилага трансформациите и записва всички файлове.
     *
     * @param args    args[0] е "save"
     * @param session текущата активна сесия
     * @return резултат със съобщение за успех или грешка
     */
    @Override
    public CommandResult execute(String[] args, Session session) {

        if (session == null) {
            return new CommandResult("No active session.", session);
        }

        StringBuilder sb = new StringBuilder();

        for (ImageFile img : session.getImages()) {
            ImageFile result = img;
            for (Transformation t : session.getTransformations()) {
                result = t.apply(result);
            }

            result.setFilename(img.getFilename());
            FileHandler.save(result);

            sb.append("Successfully saved ").append(img.getFilename()).append("\n");
        }

        session.getTransformations().clear();

        return new CommandResult(sb.toString().trim(), session);
    }
}