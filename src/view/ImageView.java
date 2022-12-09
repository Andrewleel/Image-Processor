package view;

/**
 * Interface for ImageView that was created for tests to make sure Images are equal when they are.
 */
public interface ImageView {

  /**
   * Converts the ImageImpl into string format for each pixel: "Red-value Green-value Blue-value\n".
   * @return the string format of ImageImpl.
   */
  String toString();
}
