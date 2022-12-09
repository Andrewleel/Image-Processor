package model;

/**
 * Interface for AdvancedModel of the program. The model mainly serves as
 *                  place to save and modify the images with advanced features.
 */
public interface AdvancedProcessingModel extends ImageProcessingModel {
  /**
   * the method that blurs the image and saves the modified one to the image hashmap.
   * @param imageName imageName to get image from hashMap.
   * @param newImageName imageName to save the modified image.
   */
  void blur(String imageName, String newImageName);

  /**
   * the method that sharpens the image and saves the modified one to the image hashmap.
   * @param imageName imageName to get image from hashMap.
   * @param newImageName imageName to save the modified image.
   */
  void sharpen(String imageName, String newImageName);

  /**
   * the method that transforms the color of image with greyscale and
   *                  saves the modified image to image hashMap.
   * @param imageName imageName to get image from hashMap.
   * @param newImageName imageName to save the modified image.
   */
  void colorGreyScale(String imageName, String newImageName);

  /**
   * the method that transforms the color of image with sepia and
   *                  saves the modified image to image hashMap.
   * @param imageName imageName to get image from hashMap.
   * @param newImageName imageName to save the modified image.
   */
  void sepia(String imageName, String newImageName);
}
