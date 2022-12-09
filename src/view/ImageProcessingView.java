package view;

import java.io.IOException;

/**
 * ImageProcessingView interface that will be implemented by ImageProcessingView that renders the
 *          messages.
 */
public interface ImageProcessingView {

  /**
   * The method that takes in a String and appends to the appendable.
   * @param message String that will be appended to the appendable.
   * @throws IOException if Appendable is unable to append the String message.
   */
  void render(String message) throws IOException;
}
