package strategy;

import model.GUIProcessingModel;

/**
 * class for Function Object that convert Images to GreyScale.
 */
public class GreyScale extends AbstractStrategy {
  private String type;

  /**
   * the constructor for Brighten class for function object that takes in a string array.
   * @param strList String object from the readable for client interaction
   */
  public GreyScale(String[] strList) {
    super(strList);
    switch (strList[0].toLowerCase()) {
      case "red-component":
        this.type = "red";
        break;
      case "green-component":
        this.type = "green";
        break;
      case "blue-component":
        this.type = "blue";
        break;
      case "value-component":
        this.type = "value";
        break;
      case "luma-component":
        this.type = "luma";
        break;
      case "intensity-component":
        this.type = "intensity";
        break;
      default:
        this.type = "";
        break;
    }
  }

  /**
   * the method that serves as the main method to run the function objects.
   * @param model the ImageProcessingModel that represents as the model for our ImageImpl Processor.
   */
  @Override
  public void run(GUIProcessingModel model) {
    model.greyScaleModel(this.type, this.original, this.newName);
  }
}