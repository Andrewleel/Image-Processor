package model;

import java.util.Objects;

/**
 * ImageImpl class that represents the image with Pixel array,
 *                  length value, height value, maxValue value.
 */
public class ImageImpl implements Image {
  private Pixel [][] image;
  private int length;
  private int height;
  private int maxValue;

  /**
   * the constructor that takes in length, height, and Pixel array.
   * @param length the length of image
   * @param height the height of image
   * @param image the pixel array that represents the image
   * @throws IllegalArgumentException if the length or height is less than 0,
   *                  or the length and height doesn't match the Pixel's dimensions.
   */
  public ImageImpl(int length, int height, Pixel [][] image) throws IllegalArgumentException {
    this.setConstructorImage(length, height, image);
  }

  /**
   * the constructor that takes in length, height, Pixel array, and maxValue.
   * @param length the length of image
   * @param height the height of image
   * @param image the Pixel array that represents the image.
   * @param maxValue the maxValue the pixel rgb values can't exceed
   * @throws IllegalArgumentException if the length, height, or maxValue is below 0,
   *                      or the length and height doesn't match the Pixel's dimensions,
   *                      or the pixel rgb values exceed the maxValue.
   */
  public ImageImpl(int length, int height, Pixel [][] image, int maxValue)
          throws IllegalArgumentException {
    if (maxValue < 0) {
      throw new IllegalArgumentException("maxValue can't be negative!");
    }
    this.setConstructorImage(length, height, image);
    this.maxValue = maxValue;
  }

  private void setConstructorImage(int length, int height, Pixel[][] image) {
    if (length < 0 || height < 0) {
      throw new IllegalArgumentException(
              "Length, height, or maxValue can't be negative!");
    }
    this.maxValue = 255;
    this.length = Objects.requireNonNull(length);
    this.height = Objects.requireNonNull(height);
    this.image = Objects.requireNonNull(image);

    if (image.length != height || image[0].length != length) {
      throw new IllegalArgumentException("invalid height or length!");
    }
    for (Pixel[] pixels: image) {
      for (Pixel p : pixels) {
        if (p.getRed() > maxValue || p.getGreen() > maxValue || p.getBlue() > maxValue) {
          throw new IllegalArgumentException(
                  "Pixel(s) have color values greater than the maxValue");
        }
      }
    }
  }

  /**
   * the method that returns the height value of ImageImpl.
   * @return the height value of ImageImpl.
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * the method that returns the length value of image.
   * @return the length value of ImageImpl.
   */
  @Override
  public int getLength() {
    return this.length;
  }

  /**
   * the method that returns the maxValue of the image for pixels.
   * @return the maxValue for Pixels colors in the image.
   */
  @Override
  public int getMaxValue() {
    return this.maxValue;
  }

  /**
   * the method that return the image that is made of Pixel arrays.
   * @return the image that is made up of Pixel arrays.
   */
  @Override
  public Pixel[][] getImage() {
    return this.image;
  }

  /**
   * the method that returns the pixel corresponding coordinate.
   * @param row y-value of our pixel in our arrays
   * @param col x-value of our pixel in our arrays.
   * @return the corresponding pixel that matches the coordinate.
   */
  @Override
  public Pixel getPixelAt(int row, int col) {
    if (row < 0 || col < 0
            || row > this.height  || col > this.length) {
      throw new IllegalArgumentException("Row or Col value is invalid");
    }
    return this.image[row][col];
  }

  /**
   * the method that checks if two images are equal.
   * @param o the ImageImpl object
   * @return the boolean whether two images are equal or not.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof ImageImpl)) {
      return false;
    }

    ImageImpl image = (ImageImpl) o;

    return this.height == image.height
            && this.length == image.length
            && this.maxValue == image.maxValue
            && this.hashCode() == image.hashCode();
  }

  /**
   * the method that return the hashCode for the image.
   * @return the hashCode int value for the image.
   */
  @Override
  public int hashCode() {
    int sum = 0;
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.length; j++) {
        sum += this.getPixelAt(i, j).getRed() * 6 + this.getPixelAt(i, j).getGreen() * 5
                + this.getPixelAt(i, j).getBlue() * 4;
      }
    }
    return this.height + this.length + sum;
  }
}
