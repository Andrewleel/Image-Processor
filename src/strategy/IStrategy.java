package strategy;

import model.GUIProcessingModel;

/**
 * the interface that is implemented by all the strategies.
 */
public interface IStrategy {

  /**
   * the method that serves as the main method to run the function objects.
   * @param model the ImageProcessingModel that represents as the model for our ImageImpl Processor.
   */
  void run(GUIProcessingModel model);
}
