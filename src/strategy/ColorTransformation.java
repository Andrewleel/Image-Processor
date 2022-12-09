package strategy;

import model.GUIProcessingModel;

/**
 * class for Function Object that transforms the color of the image in the model.
 */
public class ColorTransformation extends AbstractStrategy {
  private String type;

  /**
   * the constructor for ColorTransformation class for function object that takes in a string array.
   * @param strList String List from readable
   */
  public ColorTransformation(String[] strList) {
    super(strList);
    this.type = strList[0];
  }

  @Override
  public void run(GUIProcessingModel model) {
    if (this.type.equals("sepia")) {
      model.sepia(this.original, this.newName);
    } else {
      model.colorGreyScale(this.original, this.newName);
    }
  }
}