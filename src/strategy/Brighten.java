package strategy;

import java.util.Objects;

import model.GUIProcessingModel;

/**
 * class for Function Object that brighten or darken the image in the model.
 */
public class Brighten extends AbstractStrategy {
  private int addAmount;

  /**
   * the constructor for Brighten class for function object that takes in a string array.
   * @param strList String List from readable
   */
  public Brighten(String[] strList) {
    super(strList);
    try {
      int temp = Integer.parseInt(strList[1]);
      this.addAmount = temp;
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(
              "Invalid format. The amount to brighten or dark is not found");
    } catch (NullPointerException n) {
      throw new IllegalArgumentException(
              "Enter value for incrementing or decrementing the brightness");
    }
    this.original = Objects.requireNonNull(strList[2]);
    this.newName = Objects.requireNonNull(strList[3]);
  }

  /**
   * the method that serves as the main method to run the function objects.
   * @param model the ImageProcessingModel that represents as the model for our ImageImpl Processor.
   */
  @Override
  public void run(GUIProcessingModel model) {
    model.brighten(this.addAmount, this.original, this.newName);
  }
}
