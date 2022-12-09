package controller;

import java.io.IOException;

import view.IGUIView;

/**
 * the main controller interface that deals with gui implementation on the program that
 *              interacts with View.
 */
public interface ImageProcessingGUIController {
  /**
   * the method that initializes the view and brings all the functionalities.
   * @param view the GUI view
   */
  void display(IGUIView view);

  /**
   * the method that allows the user to load an image file to the program.
   * @throws IOException when the message is not rendered to the user.
   */
  void load() throws IOException;

  /**
   * the method that modifies the image with image modifying functionalities.
   * @param command the type of modification to the image.
   * @param imageName the name the image is saved as.
   * @throws IOException when the message is not rendered to the user.
   */
  void modify(String command, String imageName) throws IOException;

  /**
   * the main method that deals with saving the image onto user's machine through GUI interaction.
   * @param imageName the name the image is saved as.
   * @throws IOException when the message is not rendered to the user.
   */
  void save(String imageName) throws IOException;
}
