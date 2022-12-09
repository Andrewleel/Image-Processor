package view;

import java.awt.Dimension;
import java.util.List;

import controller.ImageProcessingGUIController;
import model.Image;

/**
 * the GUI view interface that is for view class that builds GUI for the image processor program.
 */
public interface IGUIView extends ImageProcessingView {

  /**
   * the method that set actions on buttons so that the functionalities can be operated through the
   *                  parameter controller.
   * @param controller GUI controller for processing functionalities.
   */
  void actions(ImageProcessingGUIController controller);

  /**
   * the method that adds image to the view Image list and updating histogram.
   * @param imageName the image name the image is saved as in the model.
   * @param image the image
   * @param list the frequency representations of the pixels of the image.
   */
  void addImage(String imageName, Image image, List<int[]> list, Dimension dimension);

  /**
   * the method that updates the image to the image view panel and updating
   *                    the histogram correspondingly.
   * @param image the image
   * @param imageName the image name the image is saved as in the model.
   * @param list the frequency representations of the pixels of the image.
   */
  void updateImage(Image image, String imageName, List<int[]> list, Dimension dimension);
}
