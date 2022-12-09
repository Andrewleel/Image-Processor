package model;


/**
 * Class that serves as the Advanced Model for the program that extends original
 *                ImageProcessingModel and implements the AdvancedProcessingModel
 *                interface. Uses hashmap to store Images, and the model modifies
 *                with advanced features and uploads the images to the hashmap.
 */
public class AdvancedProcessingModelImpl extends ImageProcessingModelImpl
        implements AdvancedProcessingModel {

  /**
   * the constructor for our AdvancedImageProcessModelImpl class that takes in nothing and
   *                initialize the image hashmap to an empty hashmap.
   */
  public AdvancedProcessingModelImpl() {
    super();
  }

  /**
   * the method that blurs the image and saves the modified one to the image hashmap.
   * @param imageName imageName to get image from hashMap.
   * @param newImageName imageName to save the modified image.
   */
  @Override
  public void blur(String imageName, String newImageName) {
    Image image = this.getImage(imageName);
    double[][] kernel = new double[][]{
            {0.0625, 0.125, 0.0625},
            {0.125, 0.250, 0.125},
            {0.0625, 0.125, 0.0625}};
    Pixel[][] pixels = this.setPixels(image);
    this.filteringHelper(image, kernel, pixels, newImageName);
  }

  /**
   * the method that sharpens the image and saves the modified one to the image hashmap.
   * @param imageName imageName to get image from hashMap.
   * @param newImageName imageName to save the modified image.
   */
  @Override
  public void sharpen(String imageName, String newImageName) {
    Image image = this.getImage(imageName);

    double[][] kernel = new double[][]{
            {-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.250, 0.250, 0.250, -0.125},
            {-0.125, 0.250, 1.000, 0.250, -0.125},
            {-0.125, 0.250, 0.250, 0.250, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125}};

    Pixel[][] pixels = this.setPixels(image);
    this.filteringHelper(image, kernel, pixels, newImageName);
  }

  private Pixel[][] setPixels(Image image) {
    Pixel[][] pixels = new Pixel[image.getHeight()][image.getLength()];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getLength(); j++) {
        int red = image.getPixelAt(i, j).getRed();
        int green = image.getPixelAt(i, j).getGreen();
        int blue = image.getPixelAt(i, j).getBlue();
        pixels[i][j] = new Pixel(red, green, blue);
      }
    }
    return pixels;
  }

  private void filteringHelper(Image image, double[][] kernel,
                               Pixel[][] pixels, String newImageName) {
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getLength(); j++) {
        this.applyKernel(image, kernel, pixels, i, j);
      }
    }
    Image newImage = new ImageImpl(image.getLength(), image.getHeight(), pixels);
    this.uploadImage(newImageName, newImage);
  }

  private void applyKernel(Image image, double[][] kernel, Pixel[][] pixels, int row, int col) {
    int kernelCenter = kernel.length / 2;
    double redTotal = 0.00;
    double greenTotal = 0.00;
    double blueTotal = 0.00;
    for (int i = -kernelCenter; i < kernelCenter + 1; i++) {
      for (int j = -kernelCenter; j < kernelCenter + 1; j++) {
        try {
          redTotal += (pixels[row + i][col + j].getRed()
                  * kernel[i + kernelCenter][j + kernelCenter]);
          greenTotal += (pixels[row + i][col + j].getGreen()
                  * kernel[i + kernelCenter][j + kernelCenter]);
          blueTotal += (pixels[row + i][col + j].getBlue()
                  * kernel[i + kernelCenter][j + kernelCenter]);
        } catch (ArrayIndexOutOfBoundsException e) {
          // just continue
        }
      }
    }
    int newRed = (int) this.checkColorValue(redTotal, image);
    int newGreen = (int) this.checkColorValue(greenTotal, image);
    int newBlue = (int) this.checkColorValue(blueTotal, image);
    pixels[row][col] = new Pixel(newRed, newGreen, newBlue);
  }

  /**
   * the method that transforms the color of image with greyscale and
   *                  saves the modified image to image hashMap.
   * @param imageName imageName to get image from hashMap.
   * @param newImageName imageName to save the modified image.
   */
  @Override
  public void colorGreyScale(String imageName, String newImageName) {
    Image image = this.getImage(imageName);
    double[][] matrix = new double[][]{
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}};
    this.colorTransformationHelper(matrix, image, newImageName);
  }

  /**
   * the method that transforms the color of image with sepia and
   *                  saves the modified image to image hashMap.
   * @param imageName imageName to get image from hashMap.
   * @param newImageName imageName to save the modified image.
   */
  @Override
  public void sepia(String imageName, String newImageName) {
    Image image = this.getImage(imageName);
    double[][] matrix = new double[][]{
            {0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}};
    this.colorTransformationHelper(matrix, image, newImageName);
  }

  private void colorTransformationHelper(double[][] matrix, Image image, String newImageName) {
    Pixel[][] newPixels = new Pixel[image.getHeight()][image.getLength()];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getLength(); j++) {
        int red = image.getPixelAt(i, j).getRed();
        int green = image.getPixelAt(i, j).getGreen();
        int blue = image.getPixelAt(i, j).getBlue();
        double newRed = (red * matrix[0][0]) + (green * matrix[0][1])
                + (blue * matrix[0][2]);
        double newGreen = (red * matrix[1][0]) + (green * matrix[1][1])
                + (blue * matrix[1][2]);
        double newBlue = (red * matrix[2][0]) + (green * matrix[2][1])
                + (blue * matrix[2][2]);
        int adjustedRed = (int) this.checkColorValue(newRed, image);
        int adjustedGreen = (int) this.checkColorValue(newGreen, image);
        int adjustedBlue = (int) this.checkColorValue(newBlue, image);
        newPixels[i][j] = new Pixel(adjustedRed, adjustedGreen, adjustedBlue);
      }
    }
    Image newImage = new ImageImpl(image.getLength(), image.getHeight(), newPixels);
    this.uploadImage(newImageName, newImage);
  }

  private double checkColorValue(double colorValue, Image image) {
    if (colorValue > image.getMaxValue()) {
      colorValue = image.getMaxValue();
    }
    if (colorValue < 0) {
      colorValue = 0;
    }
    return colorValue;
  }
}
