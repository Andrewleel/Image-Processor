package controller;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import java.awt.Dimension;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.GUIProcessingModel;
import model.Image;
import view.IGUIView;
import view.ImageProcessingView;
import view.ImageProcessingViewImpl;

/**
 * the main controller that is implemented to deal with interaction with View to make GUi possible.
 */
public class ImageProcessingGUIControllerImpl implements ImageProcessingGUIController {
  private GUIProcessingModel model;
  private ImageProcessingController delegate;
  private IGUIView view;

  /**
   * the constructor for GUI controller that takes in a model and initializes to the model field
   *                    in the class.
   * @param model model for GUI
   */
  public ImageProcessingGUIControllerImpl(GUIProcessingModel model) {
    ImageProcessingView view = new ImageProcessingViewImpl();
    this.model = model;
    this.delegate = new ImageProcessingControllerImpl(this.model, view, new StringReader(""));
  }

  /**
   * the method that initializes the view and brings all the functionalities.
   * @param view the GUI view
   */
  public void display(IGUIView view) {
    this.view = view;
    this.view.actions(this);
  }

  /**
   * the method that allows the user to load an image file to the program.
   * @throws IOException when the message is not rendered to the user.
   */
  @Override
  public void load() throws IOException {
    JFileChooser file = new JFileChooser();
    int response = file.showOpenDialog(null);
    File file1;
    if (response == JFileChooser.APPROVE_OPTION) {
      file1 = file.getSelectedFile();
    } else {
      return;
    }
    String uploadName = JOptionPane.showInputDialog("Image name: ");
    String fPath = file1.getAbsolutePath();
    try {
      this.delegate.loadImage(fPath, uploadName);
    } catch (IllegalArgumentException ie) {
      this.view.render("Not valid image file");
      return;
    }

    Image image = this.model.getImage(uploadName);
    this.view.addImage(uploadName, image, this.model.convertFrequencies(uploadName),
            new Dimension(image.getHeight(), image.getLength()));
  }

  /**
   * the method that modifies the image with image modifying functionalities.
   * @param command the type of modification to the image.
   * @param imageName the name the image is saved as.
   * @throws IOException when the message is not rendered to the user.
   */
  @Override
  public void modify(String command, String imageName) throws IOException {
    if (command == null || imageName == null) {
      this.view.render("Some inputs are missing");
      return;
    }
    String newName = JOptionPane.showInputDialog("New Image Name to save as: ");
    System.out.println(newName);
    System.out.println(imageName);
    switch (command) {
      case "blur":
        this.model.blur(imageName, newName);
        break;
      case "sharpen":
        this.model.sharpen(imageName, newName);
        break;
      case "greyscale":
        this.model.colorGreyScale(imageName, newName);
        break;
      case "sepia":
        this.model.sepia(imageName, newName);
        break;
      case "vertical-flip":
        this.model.flip(false, imageName, newName);
        break;
      case "horizontal-flip":
        this.model.flip(true, imageName, newName);
        break;
      case "brighten":
        String amount = JOptionPane.showInputDialog("Brighten (+) or Darken (-) by: ");
        int intAmount = Integer.parseInt(amount);
        this.model.brighten(intAmount, imageName, newName);
        break;
      case "red-component":
        this.model.greyScaleModel("red", imageName, newName);
        break;
      case "green-component":
        this.model.greyScaleModel("green", imageName, newName);
        break;
      case "blue-component":
        this.model.greyScaleModel("blue", imageName, newName);
        break;
      case "value-component":
        this.model.greyScaleModel("value", imageName, newName);
        break;
      case "intensity-component":
        this.model.greyScaleModel("intensity", imageName, newName);
        break;
      case "luma-component":
        this.model.greyScaleModel("luma", imageName, newName);
        break;
      default:
        return;
    }
    Image image = this.model.getImage(newName);
    this.view.addImage(newName, image,
            this.model.convertFrequencies(newName),
            new Dimension(image.getHeight(), image.getLength()));
  }

  /**
   * the main method that deals with saving the image onto user's machine through GUI interaction.
   * @param imageName the name the image is saved as.
   * @throws IOException when the message is not rendered to the user.
   */
  @Override
  public void save(String imageName) throws IOException {
    if (imageName == null) {
      this.view.render("Image is not found");
      return;
    }
    JFileChooser saveFile = new JFileChooser();
    int response = saveFile.showSaveDialog(null);
    File file1;
    String fPath = null;
    if (response == JFileChooser.APPROVE_OPTION) {
      file1 = saveFile.getSelectedFile();
      fPath = file1.getAbsolutePath();
    }
    try {
      this.delegate.saveImage(imageName, fPath);
    } catch (IllegalArgumentException ie) {
      this.view.render("Failed to save");
      return;
    }
  }
}