package model;

/**
 * Interface that is implemented by the image class. Created interface just in case we have to deal
 *            with different types of images.
 */
public interface Image {

  /**
   * the method that returns the height value of ImageImpl.
   * @return the height value of ImageImpl.
   */
  int getHeight();

  /**
   * the method that returns the length value of image.
   * @return the length value of ImageImpl.
   */
  int getLength();

  /**
   * the method that returns the maxValue of the image for pixels.
   * @return the maxValue for Pixels colors in the image.
   */
  int getMaxValue();

  /**
   * the method that return the image that is made of Pixel arrays.
   * @return the image that is made up of Pixel arrays.
   */
  Pixel[][] getImage();

  /**
   * the method that returns the pixel corresponding coordinate.
   * @param row y-value of our pixel in our arrays
   * @param col x-value of our pixel in our arrays.
   * @return the corresponding pixel that matches the coordinate.
   */
  Pixel getPixelAt(int row, int col);

  /**
   * the method that checks if two images are equal.
   * @param o the ImageImpl object
   * @return the boolean whether two images are equal or not.
   */
  boolean equals(Object o);

  /**
   * the method that return the hashCode for the image.
   * @return the hashCode int value for the image.
   */
  int hashCode();
}
