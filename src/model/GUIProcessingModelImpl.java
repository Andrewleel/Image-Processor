package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that serves as the GUI Model for the program that extends original
 *                AdvancedImageProcessingModel and implements the GUIProcessingModel
 *                interface. Uses hashmap to store Images, and the model modifies
 *                with advanced features and uploads the images to the hashmap. This model is
 *                final model that covered GUI functionality.
 */
public class GUIProcessingModelImpl extends AdvancedProcessingModelImpl
        implements GUIProcessingModel {

  /**
   * the constructor for GUIProcessingModelImpl class that initializes ImageMap to empty HashMap.
   */
  public GUIProcessingModelImpl() {
    super();
  }

  /**
   * Takes in a imageName and gets the image, convert it into list of frequencies.
   * @param imageName String imageName of image
   * @return the frequency representations of image pixels.
   */
  @Override
  public List<int[]> convertFrequencies(String imageName) {
    // first integer represents rgb value = 0 - 255
    // second integer represents the frequency or count
    int[] redFrequencies = new int[256];
    int[] greenFrequencies = new int[256];
    int[] blueFrequencies = new int[256];
    int[] intensityFrequencies = new int[256];
    Image image = this.getImage(imageName);
    for (int i = 0; i < 256; i++) {
      redFrequencies[i] = 0;
      greenFrequencies[i] = 0;
      blueFrequencies[i] = 0;
      intensityFrequencies[i] = 0;
    }
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getLength(); j++) {
        Pixel current = image.getPixelAt(i,j);
        int r = current.getRed();
        int g = current.getGreen();
        int b = current.getBlue();
        int intensity = (r + g + b) / 3;
        redFrequencies[r] += 1;
        greenFrequencies[g] += 1;
        blueFrequencies[b] += 1;
        intensityFrequencies[intensity] += 1;
      }
    }

    List<int[]> list = new ArrayList<>();
    list.add(redFrequencies);
    list.add(greenFrequencies);
    list.add(blueFrequencies);
    list.add(intensityFrequencies);
    return list;
  }
}