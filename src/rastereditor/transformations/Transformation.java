package rastereditor.transformations;

import rastereditor.model.ImageFile;

/**
 * Абстрактен клас който представлява трансформация върху изображение.
 * Всяка конкретна трансформация трябва да наследи този клас и да имплементира
 * методите {@link #getName()} и {@link #apply(ImageFile)}.
 * Трансформациите не променят оригиналното изображение, а връщат ново.
 */
public abstract class Transformation {

    /**
     * Връща името на трансформацията.
     * Използва се при показване на session info и при undo.
     *
     * @return името на трансформацията
     */
    public abstract String getName();

    /**
     * Прилага трансформацията върху дадено изображение и връща новото изображение.
     * Оригиналното изображение не се променя.
     *
     * @param img изображението върху което се прилага трансформацията
     * @return новото трансформирано изображение
     */
    public abstract ImageFile apply(ImageFile img);
}