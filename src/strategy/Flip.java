package strategy;

import model.GUIProcessingModel;

/**
 * class for Function Object that does horizontal and vertical flips.
 */
public class Flip extends AbstractStrategy {
  private boolean horizontal;

  /**
   * the constructor for Flip class for function object that takes in a string array.
   * @param strList String object from the readable for client interaction
   */
  public Flip(String[] strList) {
    super(strList);
    if (strList[0].equals("horizontal-flip")) {
      this.horizontal = true;
    } else {
      this.horizontal = false;
    }
  }

  /**
   * the method that serves as the main method to run the function objects.
   * @param model the ImageProcessingModel that represents as the model for our ImageImpl Processor.
   */
  @Override
  public void run(GUIProcessingModel model) {
    if (this.horizontal) {
      model.flip(true, this.original, this.newName);
    } else {
      model.flip(false, this.original, this.newName);
    }
  }
}