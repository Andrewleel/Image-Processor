package strategy;

import model.GUIProcessingModel;

/**
 * class for Function Object that blurs the image in the model.
 */
public class Blur extends AbstractStrategy {

  /**
   * the constructor for Blur class for function object that takes in a string array.
   * @param strList String List from readable
   */
  public Blur(String[] strList) {
    super(strList);
  }

  @Override
  public void run(GUIProcessingModel model) {
    model.blur(this.original, this.newName);
  }
}
