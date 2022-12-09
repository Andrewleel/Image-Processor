package view;

import java.util.Objects;

import model.Image;

/**
 * ImageViewImpl class that implements ImageView interface,
 *                and we created this for testing purposes.
 */
public class ImageViewImpl implements ImageView {
  private Image image;

  /**
   * Constructor for ImageViewImpl class that takes in an image.
   * @param image an image to convert the image to String.
   */
  public ImageViewImpl(Image image) {
    this.image = Objects.requireNonNull(image);
  }

  /**
   * Converts the ImageImpl into string format for each pixel: "Red-value Green-value Blue-value\n".
   * @return the string format of ImageImpl.
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < this.image.getHeight(); i++) {
      for (int j = 0; j < this.image.getLength(); j++) {
        builder.append(image.getPixelAt(i, j).getRed()
                + " " + image.getPixelAt(i, j).getGreen()
                + " " + image.getPixelAt(i, j).getBlue());
        builder.append("\n");
      }
    }
    return builder.toString();
  }
}
