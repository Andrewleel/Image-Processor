import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.ImageProcessingControllerImpl;
import controller.ImageProcessingController;
import controller.ImageProcessingGUIController;
import controller.ImageProcessingGUIControllerImpl;
import model.GUIProcessingModel;
import model.GUIProcessingModelImpl;
import view.GUIView;
import view.IGUIView;
import view.ImageProcessingView;
import view.ImageProcessingViewImpl;

/**
 * Main class to operate the overall program.
 */
public class Main {

  /**
   * main method that operates the program while taking in user inputs.
   * @param args String array that we aren't using for this assignment.
   */
  public static void main(String[] args) throws IOException {

    // model
    GUIProcessingModel model = new GUIProcessingModelImpl();
    Readable readable;

    try {
      if (args[0].equalsIgnoreCase("-file")) {
        try {
          readable = new FileReader(args[1]);
          // view
          ImageProcessingView view = new ImageProcessingViewImpl();
          ImageProcessingController controller
                  = new ImageProcessingControllerImpl(model, view, readable);
          controller.process();
        } catch (FileNotFoundException e) {
          ImageProcessingView view = new ImageProcessingViewImpl();
          view.render("File is not found \n");
          readable = new InputStreamReader(System.in);
          ImageProcessingController controller
                  = new ImageProcessingControllerImpl(model, view, readable);
          controller.process();
        } catch (ArrayIndexOutOfBoundsException a) {
          ImageProcessingView view = new ImageProcessingViewImpl();
          view.render("the file command is incorrect \n");
          readable = new InputStreamReader(System.in);
          ImageProcessingController controller
                  = new ImageProcessingControllerImpl(model, view, readable);
          controller.process();
        }
      }
      else if (args[0].equalsIgnoreCase("-text")) {
        readable = new InputStreamReader(System.in);
        ImageProcessingView view = new ImageProcessingViewImpl();
        ImageProcessingController controller
                = new ImageProcessingControllerImpl(model, view, readable);
        controller.process();
      }
      else {
        IGUIView view = new GUIView();
        ImageProcessingGUIController controller = new ImageProcessingGUIControllerImpl(model);
        controller.display(view);
      }
    } catch (IOException e) {
      ImageProcessingView view = new ImageProcessingViewImpl();
      view.render("The image processor failed.");
    } catch (ArrayIndexOutOfBoundsException a) {
      IGUIView view = new GUIView();
      ImageProcessingGUIController controller = new ImageProcessingGUIControllerImpl(model);
      controller.display(view);
    }
  }
}