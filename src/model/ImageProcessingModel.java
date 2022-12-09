package model;

/**
 * Interface for Model of the program. The model mainly serves as
 *                  place to save and modify the images.
 */
public interface ImageProcessingModel {

  /**
   * Gets the image from hashmap with original String, flips the image, and adds the modified
   *                  image in the hashmap.
   * @param horizontal boolean for if the flip is horizontal or vertical
   * @param original the name the image is saved in our image hashmap.
   * @param newName the new name you want the modified image to be saved as.
   */
  void flip(boolean horizontal, String original, String newName);

  /**
   * the message that uploads the image to the hashmap.
   * @param name name you want the image to save as.
   * @param image image you want to save in the hashmap.
   */
  void uploadImage(String name, Image image);

  /**
   * Getter method to get the image from the hashmap.
   * @param imageName the name the image is saved as in the hashmap
   * @return the image you wanted to get that matches the imageName.
   * @throws IllegalStateException if the image is not found.
   */
  Image getImage(String imageName);

  /**
   * Gets the image from the hashmap and brighten or darken the image based on the value of
   *        addAmount we take in.
   * @param addAmount the amount you want to darken or brighten the image.
   * @param original the name the image is saved in our image hashmap.
   * @param newName the new name you want the modified image to be saved as.
   */
  void brighten(int addAmount, String original, String newName);

  /**
   * Gets the image from hashMap and greyscale the image using whichever method the type indicates.
   * @param type the type of method you want to use to greyscale the image.
   * @param original the name the image is saved in our image hashmap.
   * @param newName the new name you want the modified image to be saved as.
   */
  void greyScaleModel(String type, String original, String newName);
}