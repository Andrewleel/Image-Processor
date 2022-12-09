package strategy;

import java.util.Objects;

import model.GUIProcessingModel;

/**
 * class for Abstract Function Object that is extended by all function objects.
 */
public abstract class AbstractStrategy implements IStrategy {
  protected String original;
  protected String newName;

  /**
   * Constructor for AbstractStrategy class that is the abstract class for function objects.
   * @param strList String list that serves as what users want to do
   */
  public AbstractStrategy(String[] strList) {
    if (strList == null) {
      throw new IllegalArgumentException("String List is null!");
    }
    this.original = Objects.requireNonNull(strList[1]);
    this.newName = Objects.requireNonNull(strList[2]);
  }

  /**
   * the method that serves as the main method to run the function objects.
   * @param model the ImageProcessingModel that represents as the model for our ImageImpl Processor.
   */
  public abstract void run(GUIProcessingModel model);
}
