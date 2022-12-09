import java.awt.Dimension;
import java.io.IOException;
import java.util.List;

import controller.ImageProcessingGUIController;
import model.Image;
import view.IGUIView;

/**
 * GUIMock class that was implemented for testing purpose since GUI itself can't be tested.
 */
public class GUIMock implements IGUIView  {
  private StringBuilder log;

  /**
   * constructor for GUI Mock for testing.
   * @param log StringBuilder
   */
  public GUIMock(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void actions(ImageProcessingGUIController controller) {
    this.log.append("Action processed \n");
  }

  @Override
  public void addImage(String imageName, Image image, List<int[]> list, Dimension dimension) {
    this.log.append("Image added \n");
  }

  @Override
  public void updateImage(Image image, String imageName, List<int[]> list, Dimension dimension) {
    this.log.append("Image updated \n");
  }

  @Override
  public void render(String message) throws IOException {
    this.log.append("Message rendered: " + message +  "\n");
  }
}
