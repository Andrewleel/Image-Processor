package controller;

import java.io.IOException;

/**
 * Controller interface for our ImageImpl Processing that also deals with loading and saving files.
 */
public interface ImageProcessingController {
  /**
   * load method that loads the image file into the program.
   * @param filename file path of the image file
   * @param imageName image name the image file will be saved as in the program.
   * @throws IOException when the message is not rendered to the user.
   */

  void loadImage(String filename, String imageName) throws IOException;

  /**
   * the save method that save the image as image file on the machine.
   * @param imageName image name that the image is saved in the program.
   * @param saveName the image file name that will be saved on the machine.
   */
  void saveImage(String imageName, String saveName);

  /**
   * the method that first renders the direction formulas to the user, then interprets the inputs
   *            from the users, and executes the commands. Try and catch the possible exceptions
   *            from the program so that the program doesn't stop, instead render warning messages
   *            to the users.
   * @throws IllegalStateException if the view is unable to render the messages properly.
   */
  void process();
}
