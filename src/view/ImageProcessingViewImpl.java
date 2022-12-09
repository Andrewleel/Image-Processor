package view;

import java.io.IOException;
import java.util.Objects;

/**
 * ImageProcessingView class that renders the messages. We could have done everything the view does
 *                 in Controller but decided to keep it for future assignments.
 */
public class ImageProcessingViewImpl implements ImageProcessingView {
  private Appendable appendable;

  /**
   * the constructor that takes in nothing and initialize the Appendable to System.out.
   */
  public ImageProcessingViewImpl() {
    this.appendable = System.out;
  }

  /**
   * the constructor that takes in Appendable and initialize the Appendable to this.Appendable.
   * @param appendable appendable that will be initialized to this.appendable field
   * @throws IllegalArgumentException if the appendable is null.
   */
  public ImageProcessingViewImpl(Appendable appendable) throws IllegalArgumentException {
    this.appendable = Objects.requireNonNull(appendable);
  }

  /**
   * The method that takes in a String and appends to the appendable.
   * @param message String that will be appended to the appendable.
   * @throws IOException if Appendable is unable to append the String message.
   */
  @Override
  public void render(String message) throws IOException {
    this.appendable.append(message);
  }
}