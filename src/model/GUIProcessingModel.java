package model;

import java.util.List;

/**
 * Interface for GUI model of the program. The model mainly serves as
 *                  place to save and modify the images with advanced features
 *                  and the model for gui functionality.
 */
public interface GUIProcessingModel extends AdvancedProcessingModel {

  /**
   * Takes in a imageName and gets the image, convert it into list of frequencies.
   * @param imageName String imageName of image
   * @return the frequency representations of image pixels.
   */
  List<int[]> convertFrequencies(String imageName);
}