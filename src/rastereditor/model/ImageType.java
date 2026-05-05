package rastereditor.model;

/**
 * Изброен тип който представлява поддържаните формати на растерни изображения.
 * <ul>
 *   <li>PPM — Portable Pixmap (цветно изображение)</li>
 *   <li>PGM — Portable Graymap (изображение в нюанси на сивото)</li>
 *   <li>PBM — Portable Bitmap (черно-бяло изображение)</li>
 * </ul>
 */
public enum ImageType {
    PPM, PGM, PBM
}
