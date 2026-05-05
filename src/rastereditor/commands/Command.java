package rastereditor.commands;

import rastereditor.model.Session;

/**
 * Интерфейс който представлява команда в редактора.
 * Всяка команда трябва да имплементира метода {@link #execute(String[], Session)}.
 * Използва се Command design pattern за обработка на потребителски команди.
 */
public interface Command {

    /**
     * Изпълнява командата с дадените аргументи и текуща сесия.
     *
     * @param args    масив от аргументи, където args[0] е името на командата
     * @param session текущо активната сесия (може да е null ако няма активна сесия)
     * @return резултат от изпълнението съдържащ съобщение и евентуално нова сесия
     */
    CommandResult execute(String[] args, Session session);
}