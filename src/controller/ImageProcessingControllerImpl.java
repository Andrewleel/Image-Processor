package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

import javax.imageio.ImageIO;

import model.GUIProcessingModel;
import model.Image;
import model.ImageImpl;
import model.Pixel;
import strategy.Blur;
import strategy.Brighten;
import strategy.ColorTransformation;
import strategy.Flip;
import strategy.GreyScale;
import strategy.IStrategy;
import strategy.Sharpen;
import view.ImageProcessingView;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * The class that serves as the main controller for our program. It executes the command that
 *      are initialized in our constructor.
 */
public class ImageProcessingControllerImpl implements ImageProcessingController {
  private final Map<String, Function<String[], IStrategy>> commands = new HashMap<>();
  private GUIProcessingModel model;
  private ImageProcessingView view;
  private Readable readable;

  /**
   * the constructor for our controller that takes in a model, view, and readable.
   * @param model the model for our program.
   * @param view the view for our program.
   * @param readable readable that serves as main interaction with the users.
   */
  public ImageProcessingControllerImpl(GUIProcessingModel model,
                                       ImageProcessingView view, Readable readable) {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);
    this.readable = Objects.requireNonNull(readable);
    commands.put("horizontal-flip", s -> new Flip(s));
    commands.put("vertical-flip", s -> new Flip(s));
    commands.put("brighten", s -> new Brighten(s));
    commands.put("red-component", s -> new GreyScale(s));
    commands.put("green-component", s -> new GreyScale(s));
    commands.put("blue-component", s -> new GreyScale(s));
    commands.put("value-component", s -> new GreyScale(s));
    commands.put("intensity-component", s -> new GreyScale(s));
    commands.put("luma-component", s -> new GreyScale(s));
    commands.put("sepia", s -> new ColorTransformation(s));
    commands.put("greyscale", s -> new ColorTransformation(s));
    commands.put("blur", s -> new Blur(s));
    commands.put("sharpen", s -> new Sharpen(s));
  }



  /**
   * the method that first renders the direction formulas to the user, then interprets the inputs
   *            from the users, and executes the commands. Try and catch the possible exceptions
   *            from the program so that the program doesn't stop, instead render warning messages
   *            to the users.
   * @throws IllegalStateException if the view is unable to render the messages properly.
   */
  @Override
  public void process() throws IllegalStateException {
    String direction = "DIRECTIONS for the image processor:\n"
            + "    - Quit the program:                  q or quit. Anywhere in your input\n"
            + "    - Load an image:                     load imagePath imageName\n"
            + "    - Flip the image vertically:         vertical-flip imageName newImageName\n"
            + "    - Flip the image horizontally:       horizontal-flip imageName newImageName\n"
            + "    - Brighten the image:                brighten positive-number imageName "
            + "newImageName\n"
            + "    - Darken the image:                  brighten negative-number imageName "
            + "newImageName\n"
            + "    - Red-Component Greyscale:           red-component imageName "
            + "newImageName\n"
            + "    - Green-Component Greyscale:         green-component imageName "
            + "newImageName\n"
            + "    - Blue-Component Greyscale:          blue-component imageName "
            + "newImageName\n"
            + "    - Value-Component Greyscale:         value-component imageName "
            + "newImageName\n"
            + "    - Intensity-Component Greyscale:     intensity-component imageName "
            + "newImageName\n"
            + "    - Luma-Component Greyscale:          luma-component imageName "
            + "newImageName\n"
            + "    - Blur the image:                    blur imageName "
            + "newImageName\n"
            + "    - Sharpen the image:                 sharpen imageName "
            + "newImageName\n"
            + "    - ColorTransformation(GreyScale):    greyscale imageName "
            + "newImageName\n"
            + "    - ColorTransformation(sepia):        sepia imageName "
            + "newImageName\n"
            + "Enter:";
    this.tryRenderingMessage(direction);
    Scanner scan = new Scanner(this.readable);
    while (scan.hasNext()) {
      String strLine = scan.nextLine();
      String[] strList = strLine.split(" ");
      try {
        this.quitProgram(strList);
      } catch (IllegalStateException e) {
        return;
      }
      try {
        this.operateCommands(strList);
      } catch (ArrayIndexOutOfBoundsException ae) {
        this.tryRenderingMessage("Invalid Command. Please refer to the formulas. Try again");
      }
    }
  }

  /**
   * load method that loads the image file into the program.
   * @param filename file path of the image file
   * @param imageName image name the image file will be saved as in the program.
   * @throws IOException when the message is not rendered to the user.
   */
  public void loadImage(String filename, String imageName) throws IOException {
    String saveType = filename.substring(filename.length() - 3);

    if (saveType.equals("ppm")) {
      this.loadPPM(filename, imageName);
      return;
    }

    BufferedImage loadedImage;
    try {
      loadedImage = ImageIO.read(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("File not found");
    } catch (IOException e) {
      throw new IllegalArgumentException("Unable to read the image file");
    }

    int height;
    int width;
    try {
      height = loadedImage.getHeight();
      width = loadedImage.getWidth();
    } catch (NullPointerException n) {
      throw new IllegalArgumentException("Height and Width values are missing");
    }

    Pixel[][] pixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Color color = new Color(loadedImage.getRGB(j, i));
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        int alpha = color.getAlpha();
        pixels[i][j] = new Pixel(red, green, blue, alpha);
      }
    }

    Image newImage = new ImageImpl(width, height, pixels);
    this.model.uploadImage(imageName, newImage);
  }

  // method that loads the PPM file and upload the image to the model.
  private void loadPPM(String filename, String imageName) throws IOException {
    Scanner scan;

    try {
      scan = new Scanner(new FileInputStream(filename));
    }
    catch (FileNotFoundException e) {
      throw new FileNotFoundException("file not found");
    }

    StringBuilder builder = this.evaluatePPM(scan);

    //now set up the scanner to read from the string we just built
    scan = new Scanner(builder.toString());

    String token = scan.next();

    if (!token.equals("P3")) {
      this.tryRenderingMessage("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = scan.nextInt();
    int height = scan.nextInt();
    int maxValue = scan.nextInt();

    Pixel[][] pixels = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0 ; j < width; j++) {
        int r = scan.nextInt();
        int g = scan.nextInt();
        int b = scan.nextInt();
        pixels [i][j] = new Pixel(r, g, b);
      }
    }

    Image image = new ImageImpl(width, height, pixels, maxValue);
    this.model.uploadImage(imageName, image);
  }

  /**
   * the save method that save the image as image file on the machine.
   * @param imageName image name that the image is saved in the program.
   * @param saveName the image file name that will be saved on the machine.
   */
  public void saveImage(String imageName, String saveName) {
    String saveType = saveName.substring(saveName.length() - 3);

    if (saveType.equals("ppm")) {
      this.savePPM(imageName, saveName);
      return;
    }

    Image image;
    try {
      image = this.model.getImage(imageName);
    } catch (IllegalStateException n) {
      throw new IllegalArgumentException("The image doesn't exist in our ImageImpl list");
    }

    BufferedImage imageToSave = new BufferedImage(image.getLength(),
            image.getHeight(), TYPE_INT_RGB);

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getLength(); j++) {
        int red = image.getPixelAt(i, j).getRed();
        int green = image.getPixelAt(i, j).getGreen();
        int blue = image.getPixelAt(i, j).getBlue();
        int alpha = image.getPixelAt(i, j).getAlpha();
        Color color = new Color(red, green, blue, alpha);
        imageToSave.setRGB(j, i, color.getRGB());
      }
    }

    try {
      File output = new File(saveName);
      ImageIO.write(imageToSave, saveType, output);
    } catch (IOException e) {
      throw new IllegalArgumentException("unable to save the image");
    }
  }

  // the method that gets an image from the model and saves it  to the filePath
  private void savePPM(String imageName, String saveName) {
    Image image;
    try {
      image = this.model.getImage(imageName);
    } catch (IllegalStateException n) {
      throw new IllegalArgumentException("The image doesn't exist in our ImageImpl list");
    }

    StringBuilder builder = this.savePPMHelper(image);
    try {
      OutputStream output
              = new FileOutputStream(saveName);
      byte[] array = new String(builder).getBytes();
      output.write(array);
      output.close();
    }
    catch (Exception e) {
      throw new IllegalStateException("saving ppm file failed");
    }
  }

  // converts the image to String PPM format
  private StringBuilder savePPMHelper(Image image) {
    StringBuilder builder = new StringBuilder();
    builder.append("P3\n" + image.getLength() + "\n" + image.getHeight() + "\n255\n");
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getLength(); j++) {
        int red = image.getPixelAt(i, j).getRed();
        int green = image.getPixelAt(i, j).getGreen();
        int blue = image.getPixelAt(i, j).getBlue();
        builder.append(red + " " + green + " " + blue + " " + "\n");
      }
    }
    return builder;
  }

  // the helper for loadPPM method that turns the PPM into StringBuilder
  private StringBuilder evaluatePPM(Scanner scan) {
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (scan.hasNextLine()) {
      String s = scan.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    return builder;
  }

  // the method that operates all the commands
  private void operateCommands(String[] strList) {
    strList[0] = strList[0].toLowerCase();
    if (strList[0].equalsIgnoreCase("load")) {
      this.tryRenderingMessage("ImageImpl loading... ");

      try {
        this.loadImage(strList[1], strList[2]);
        this.tryRenderingMessage("ImageImpl loaded!");
      } catch (FileNotFoundException f) {
        this.tryRenderingMessage("Could not locate the ImageImpl File");
      } catch (IOException e) {
        throw new IllegalStateException("Message failed to print out");
      }
    } else if (strList[0].equalsIgnoreCase("save")) {
      this.tryRenderingMessage("ImageImpl saving... ");
      try {
        this.saveImage(strList[2], strList[1]);
        this.tryRenderingMessage("ImageImpl saved");
      } catch (IllegalArgumentException a) {
        this.tryRenderingMessage("The image doesn't exist in our image list");
      }
    } else {
      Function<String[], IStrategy> cmd =
              commands.getOrDefault(strList[0], null);
      if (cmd == null) {
        this.tryRenderingMessage("the command does not exist. Try again.");
      } else {
        try {
          cmd.apply(strList).run(this.model);

          // for darken
          if (strList[0].equals("brighten")) {
            if (Integer.parseInt(strList[1]) < 0) {
              strList[0] = "darken";
            }
          }
          this.tryRenderingMessage(strList[0] + " processed!");
        } catch (IllegalArgumentException n) {
          this.tryRenderingMessage("ImageImpl not found. Try again");
        }
      }
    }
  }

  // the method that will be executed when the user inputs q quit
  private void quitProgram(String[] strList) {
    for (String strValue: strList) {
      if (strValue.equalsIgnoreCase("quit") || strValue.equalsIgnoreCase("q")) {
        this.tryRenderingMessage("ImageImpl Processor quit :(");
        throw new IllegalStateException("quit check");
      }
    }
  }

  // method that tries to render the message.
  private void tryRenderingMessage(String message) throws IllegalStateException {
    try {
      this.view.render(message + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Message failed to print out");
    }
  }
}