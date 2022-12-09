package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that serves as the Model for the program. Uses hashmap
 *                to store Images, and the model modifies and uploads the images to the hashmap.
 */
public class ImageProcessingModelImpl implements ImageProcessingModel {
  private final Map<String, Image> imagesMap;

  /**
   * the constructor for our ImageProcessModelImpl class that takes in nothing and
   *                initialize the image hashmap to an empty hashmap.
   */
  public ImageProcessingModelImpl() {
    this.imagesMap = new HashMap<>();
  }

  /**
   * Gets the image from hashmap with original String, flips the image, and adds the modified
   *                  image in the hashmap.
   * @param horizontal boolean for if the flip is horizontal or vertical
   * @param original the name the image is saved in our image hashmap.
   * @param newName the new name you want the modified image to be saved as.
   */
  @Override
  public void flip(boolean horizontal, String original, String newName) {
    Image image = this.getImage(original);
    Pixel[][] pixels = new Pixel[image.getHeight()][image.getLength()];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getLength(); j++) {
        if (horizontal) {
          pixels[i][j] = image.getPixelAt(i,
                  image.getLength() - j - 1);
        } else {
          pixels[i][j] = image.getPixelAt(
                  image.getHeight() - i - 1, j);
        }
      }
    }
    this.uploadImage(newName,
            new ImageImpl(image.getLength(), image.getHeight(), pixels));
  }

  /**
   * the message that uploads the image to the hashmap.
   * @param name name you want the image to save as.
   * @param image image you want to save in the hashmap.
   * @throws IllegalArgumentException if the name or image is null.
   */
  @Override
  public void uploadImage(String name, Image image) {
    if (name == null || image == null) {
      throw new IllegalArgumentException("ImageImpl not found");
    }
    this.imagesMap.put(name, image);
  }

  /**
   * Getter method to get the image from the hashmap.
   * @param imageName the name the image is saved as in the hashmap
   * @return the image you wanted to get that matches the imageName.
   * @throws IllegalArgumentException if the image is not found.
   */
  @Override
  public Image getImage(String imageName) throws IllegalArgumentException {
    Image image = this.imagesMap.get(imageName);
    if (image == null) {
      throw new IllegalArgumentException("ImageImpl not found");
    }
    return image;
  }

  /**
   * Gets the image from the hashmap and brighten or darken the image based on the value of
   *        addAmount we take in.
   * @param addAmount the amount you want to darken or brighten the image.
   * @param original the name the image is saved in our image hashmap.
   * @param newName the new name you want the modified image to be saved as.
   */
  @Override
  public void brighten(int addAmount, String original, String newName) {
    boolean brighten;
    Image image = this.getImage(original);
    Pixel [][] pixels = new Pixel[image.getHeight()][image.getLength()];

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getLength(); j++) {
        int red = image.getPixelAt(i, j).getRed();
        int green = image.getPixelAt(i, j).getGreen();
        int blue = image.getPixelAt(i, j).getBlue();
        int[] temp = new int[3];
        temp[0] = red;
        temp[1] = green;
        temp[2] = blue;
        int[] check;
        if (addAmount > 0 ) {
          brighten = true;
        } else {
          brighten = false;
        }
        check = this.brighteningHelper(temp, addAmount, image, brighten);
        red = check[0];
        green = check[1];
        blue = check[2];
        pixels[i][j] = new Pixel(red, green, blue);
      }
    }
    Image newImage = new ImageImpl(image.getLength(), image.getHeight(), pixels);
    this.uploadImage(newName, newImage);
  }

  /**
   * Gets the image from hashMap and greyscale the image using whichever method the type indicates.
   * @param type the type of method you want to use to greyscale the image.
   * @param original the name the image is saved in our image hashmap.
   * @param newName the new name you want the modified image to be saved as.
   */
  @Override
  public void greyScaleModel(String type, String original, String newName) {
    Image image = this.getImage(original);
    Pixel [][] pixels = new Pixel[image.getHeight()][image.getLength()];

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getLength(); j++) {
        pixels[i][j] = image.getPixelAt(i, j).greyScalePixel(type);
      }
    }
    Image newImage = new ImageImpl(image.getLength(), image.getHeight(), pixels);
    this.uploadImage(newName, newImage);
  }

  // make sure the colors in pixels don't exceed the image's maxValue if brighten.
  // make sure the colors in pixels don't go below 0 if darken.
  private int[] brighteningHelper(int[] colors, int addAmount, Image image, boolean brighten) {
    int[] temp = new int[colors.length];
    int counter = 0;
    for (int color: colors) {
      if (brighten) {
        if (color + addAmount > image.getMaxValue()) {
          temp[counter] = image.getMaxValue();
        } else {
          temp[counter] = color + addAmount;
        }
      } else {
        if (color + addAmount < 0) {
          temp[counter] = 0;
        } else {
          temp[counter] = color + addAmount;
        }
      }
      counter += 1;
    }
    return temp;
  }


}
