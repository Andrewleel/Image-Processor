package strategy;

import model.GUIProcessingModel;

/**
 * class for Function Object that sharpens the image in the model.
 */
public class Sharpen extends AbstractStrategy {

  /**
   * the constructor for Sharpen class for function object that takes in a string array.
   * @param strList String object from the readable for client interaction
   */
  public Sharpen(String[] strList) {
    super(strList);
  }

  @Override
  public void run(GUIProcessingModel model) {
    model.sharpen(this.original, this.newName);
  }
}
