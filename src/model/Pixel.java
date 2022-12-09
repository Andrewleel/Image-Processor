package model;

import java.util.Objects;

/**
 * class that represents a pixel that
 *            has a value for red, green,and blue.
 */
public class Pixel {
  private int red;
  private int green;
  private int blue;
  private int alpha;

  /**
   * the constructor for pixel class that takes in red, green, blue values.
   * @param red red value
   * @param green green value
   * @param blue blue value
   * @throws IllegalArgumentException if the red, green, or blue value is below 0.
   */
  public Pixel(int red, int green, int blue) throws IllegalArgumentException {
    if (red < 0 || green < 0 || blue < 0) {
      throw new IllegalArgumentException("red, green, or blue values are invalid");
    }
    this.red = Objects.requireNonNull(red);
    this.green = Objects.requireNonNull(green);
    this.blue = Objects.requireNonNull(blue);
    this.alpha = 255;
  }

  /**
   * the constructor for pixel class that takes in red, green, blue, and alpha values.
   * @param red red value
   * @param green green value
   * @param blue blue value
   * @param alpha alpha value
   * @throws IllegalArgumentException if the red, green, blue, or alpha value is below 0 and
   *                  if alpha is greater than 255.
   */
  public Pixel(int red, int green, int blue, int alpha) throws IllegalArgumentException {
    if (red < 0 || green < 0 || blue < 0 || alpha < 0 || alpha > 255) {
      throw new IllegalArgumentException("red, green, blue, or alpha values are invalid");
    }
    this.red = Objects.requireNonNull(red);
    this.green = Objects.requireNonNull(green);
    this.blue = Objects.requireNonNull(blue);
    this.alpha = Objects.requireNonNull(alpha);
  }

  /**
   * the method that return red value of the pixel.
   * @return red value of the pixel.
   */
  public int getRed() {
    return this.red;
  }

  /**
   * the method that return green value of the pixel.
   * @return green value of the pixel.
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * the method that return blue value of the pixel.
   * @return blue value of the pixel.
   */
  public int getBlue() {
    return this.blue;
  }

  /**
   * the method that return alpha value of the pixel.
   * @return alpha value of the pixel.
   */
  public int getAlpha() {
    return this.alpha;
  }

  /**
   * the method that determine which way to greyScale the pixel.
   * @param type the way we would greyScale the pixel.
   * @return the new red, green, blue value after the pixel was greyScaled.
   */
  public Pixel greyScalePixel(String type) {
    int red;
    int green;
    int blue;
    Pixel pixel;
    switch (type) {
      case "red":
        red = this.red;
        green = this.red;
        blue = this.red;
        break;
      case "green":
        red = this.green;
        green = this.green;
        blue = this.green;
        break;
      case "blue":
        red = this.blue;
        green = this.blue;
        blue = this.blue;
        break;
      case "value":
        int max = Math.max(Math.max(this.red, this.green), this.blue);
        red = max;
        green = max;
        blue = max;
        break;
      case "intensity":
        int mean = (this.red + this.green + this.blue) / 3;
        red = mean;
        green = mean;
        blue = mean;
        break;
      case "luma":
        int sum = (int) (this.red * 0.2126) + (int) (this.green * 0.7152)
                + (int) (this.blue * 0.0722);
        red = sum;
        green = sum;
        blue = sum;
        break;
      default:
        red = this.red;
        green = this.green;
        blue = this.blue;
        break;
    }
    pixel = new Pixel(red, green, blue);
    return pixel;
  }

  /**
   * the method that checks if two pixels are equal.
   * @param o the object that is Pixel object (preferably).
   * @return the boolean whether two pixels are equal.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof Pixel)) {
      return false;
    }

    Pixel pixel = (Pixel) o;

    return  this.red == pixel.red
            && this.green == pixel.green
            && this.blue == pixel.blue;
  }

  /**
   * the method that returns the unique code value for the pixel.
   * @return the unique code value for the pixel.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.red, this.green, this.blue);
  }
}
