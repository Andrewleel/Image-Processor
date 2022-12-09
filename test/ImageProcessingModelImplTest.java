import org.junit.Before;
import org.junit.Test;

import model.Image;
import model.ImageImpl;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import model.Pixel;
import view.ImageView;
import view.ImageViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * test class for ImageProcessingModelImpl that serves as the model
 *              for our image processing program.
 */
// Test done
public class ImageProcessingModelImplTest {
  private Image image1;
  private Image image2;
  private ImageProcessingModel model;
  private ImageView view;

  @Before
  public void initialize() {
    Pixel p1 = new Pixel(100, 0, 60);
    Pixel p2 = new Pixel(20, 40, 60);
    Pixel p3 = new Pixel(200, 130, 160);
    Pixel p4 = new Pixel(140, 64, 83);
    Pixel p5 = new Pixel(180, 49, 75);
    Pixel p6 = new Pixel(40, 93, 231);
    Pixel p7 = new Pixel(210, 134, 84);
    Pixel p8 = new Pixel(40,156, 209);
    Pixel p9 = new Pixel(198, 43, 2);
    Pixel[][] pixels1 = new Pixel[2][2];
    pixels1[0][0] = p1;
    pixels1[0][1] = p2;
    pixels1[1][0] = p3;
    pixels1[1][1] = p4;
    this.image1 = new ImageImpl(2, 2, pixels1);
    Pixel[][] pixels2 = new Pixel[3][3];
    pixels2[0][0] = p1;
    pixels2[0][1] = p2;
    pixels2[0][2] = p3;
    pixels2[1][0] = p4;
    pixels2[1][1] = p5;
    pixels2[1][2] = p6;
    pixels2[2][0] = p7;
    pixels2[2][1] = p8;
    pixels2[2][2] = p9;
    this.image2 = new ImageImpl(3, 3, pixels2);
    this.model = new ImageProcessingModelImpl();
    // initialize view in individual testing methods if view is needed.
  }

  @Test
  public void testInitialize() {
    assertEquals(2, this.image1.getLength());
    assertEquals(2, this.image1.getHeight());
    assertEquals(3, this.image2.getLength());
    assertEquals(3, this.image2.getHeight());

    Pixel p1 = new Pixel(100, 0, 60);
    Pixel p2 = new Pixel(20, 40, 60);
    Pixel p3 = new Pixel(200, 130, 160);
    Pixel p4 = new Pixel(140, 64, 83);

    Pixel[][] tempPixels = new Pixel[2][2];
    tempPixels[0][0] = p1;
    tempPixels[0][1] = p2;
    tempPixels[1][0] = p3;
    tempPixels[1][1] = p4;

    assertEquals(p1, this.image1.getPixelAt(0,0));
    assertEquals(p2, this.image1.getPixelAt(0,1));
    assertEquals(p3, this.image1.getPixelAt(1,0));
    assertEquals(p4, this.image1.getPixelAt(1,1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUploadImageException() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    this.model.uploadImage(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetImageException() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    this.model.getImage("second");
  }

  @Test
  public void testUploadImageAndGetImage() {
    this.model.uploadImage("first", this.image1);
    this.model.uploadImage("second", this.image2);
    assertEquals(this.image1, this.model.getImage("first"));
    assertEquals(this.image2, this.model.getImage("second"));
  }

  @Test
  public void testFlip() {

    // Horizontal flip for 2 * 2 image
    this.model.uploadImage("first", this.image1);
    this.model.flip(true, "first", "newFirst");
    Pixel p1 = new Pixel(100, 0, 60);
    Pixel p2 = new Pixel(20, 40, 60);
    Pixel p3 = new Pixel(200, 130, 160);
    Pixel p4 = new Pixel(140, 64, 83);
    Pixel[][] temp = new Pixel[2][2];
    temp[0][0] = p2;
    temp[0][1] = p1;
    temp[1][0] = p4;
    temp[1][1] = p3;
    Image image = new ImageImpl(2,2, temp);
    this.view = new ImageViewImpl(image);
    assertEquals(this.view.toString(), new ImageViewImpl(
            this.model.getImage("newFirst")).toString());

    // Vertical flip for 3 * 3 image
    this.model.uploadImage("second", this.image2);
    this.model.flip(false, "second", "newSecond");
    Pixel p5 = new Pixel(180, 49, 75);
    Pixel p6 = new Pixel(40, 93, 231);
    Pixel p7 = new Pixel(210, 134, 84);
    Pixel p8 = new Pixel(40,156, 209);
    Pixel p9 = new Pixel(198, 43, 2);
    Pixel[][] temp2 = new Pixel[3][3];
    temp2[0][0] = p7;
    temp2[0][1] = p8;
    temp2[0][2] = p9;
    temp2[1][0] = p4;
    temp2[1][1] = p5;
    temp2[1][2] = p6;
    temp2[2][0] = p1;
    temp2[2][1] = p2;
    temp2[2][2] = p3;
    Image image2 = new ImageImpl(3,3, temp2);
    this.view = new ImageViewImpl(image2);
    assertEquals(this.view.toString(), new ImageViewImpl(
            this.model.getImage("newSecond")).toString());
  }

  @Test
  public void testBrighten() {
    this.model.uploadImage("first", this.image1);
    this.model.brighten(50, "first", "first-brighter");
    Pixel p1 = new Pixel(150, 50, 110);
    Pixel p2 = new Pixel(70, 90, 110);
    Pixel p3 = new Pixel(250, 180, 210);
    Pixel p4 = new Pixel(190, 114, 133);
    Pixel[][] temp = new Pixel[2][2];
    temp[0][0] = p1;
    temp[0][1] = p2;
    temp[1][0] = p3;
    temp[1][1] = p4;
    Image image = new ImageImpl(2,2, temp);
    this.view = new ImageViewImpl(image);
    assertEquals(this.view.toString(), new ImageViewImpl(
            this.model.getImage("first-brighter")).toString());
  }

  @Test
  public void testDarken() {
    this.model.uploadImage("first", this.image1);
    this.model.brighten(-50, "first", "first-darker");
    Pixel p1 = new Pixel(50, 0, 10);
    Pixel p2 = new Pixel(0, 0, 10);
    Pixel p3 = new Pixel(150, 80, 110);
    Pixel p4 = new Pixel(90, 14, 33);
    Pixel[][] temp = new Pixel[2][2];
    temp[0][0] = p1;
    temp[0][1] = p2;
    temp[1][0] = p3;
    temp[1][1] = p4;
    Image image = new ImageImpl(2,2, temp);
    this.view = new ImageViewImpl(image);
    assertEquals(this.view.toString(), new ImageViewImpl(
            this.model.getImage("first-darker")).toString());
  }

  @Test
  public void testGreyScaleRed() {
    this.model.uploadImage("first", this.image1);
    this.model.greyScaleModel("red", "first", "first-red");
    Pixel p1 = new Pixel(100, 100, 100);
    Pixel p2 = new Pixel(20, 20, 20);
    Pixel p3 = new Pixel(200, 200, 200);
    Pixel p4 = new Pixel(140, 140, 140);
    Pixel[][] temp = new Pixel[2][2];
    temp[0][0] = p1;
    temp[0][1] = p2;
    temp[1][0] = p3;
    temp[1][1] = p4;
    Image image = new ImageImpl(2,2, temp);
    this.view = new ImageViewImpl(image);
    assertEquals(this.view.toString(), new ImageViewImpl(
            this.model.getImage("first-red")).toString());
  }

  @Test
  public void testGreyScaleGreen() {
    this.model.uploadImage("first", this.image1);
    this.model.greyScaleModel("green", "first", "first-green");
    Pixel p1 = new Pixel(0, 0, 0);
    Pixel p2 = new Pixel(40, 40, 40);
    Pixel p3 = new Pixel(130, 130, 130);
    Pixel p4 = new Pixel(64, 64, 64);
    Pixel[][] temp = new Pixel[2][2];
    temp[0][0] = p1;
    temp[0][1] = p2;
    temp[1][0] = p3;
    temp[1][1] = p4;
    Image image = new ImageImpl(2,2, temp);
    this.view = new ImageViewImpl(image);
    assertEquals(this.view.toString(), new ImageViewImpl(
            this.model.getImage("first-green")).toString());
  }

  @Test
  public void testGreyScaleBlue() {
    this.model.uploadImage("first", this.image1);
    this.model.greyScaleModel("blue", "first", "first-blue");
    Pixel p1 = new Pixel(60, 60, 60);
    Pixel p2 = new Pixel(60, 60, 60);
    Pixel p3 = new Pixel(160, 160, 160);
    Pixel p4 = new Pixel(83, 83, 83);
    Pixel[][] temp = new Pixel[2][2];
    temp[0][0] = p1;
    temp[0][1] = p2;
    temp[1][0] = p3;
    temp[1][1] = p4;
    Image image = new ImageImpl(2,2, temp);
    this.view = new ImageViewImpl(image);
    assertEquals(this.view.toString(), new ImageViewImpl(
            this.model.getImage("first-blue")).toString());
  }

  @Test
  public void testGreyScaleValue() {
    this.model.uploadImage("first", this.image1);
    this.model.greyScaleModel("value", "first", "first-value");
    Pixel p1 = new Pixel(100, 100, 100);
    Pixel p2 = new Pixel(60, 60, 60);
    Pixel p3 = new Pixel(200, 200, 200);
    Pixel p4 = new Pixel(140, 140, 140);
    Pixel[][] temp = new Pixel[2][2];
    temp[0][0] = p1;
    temp[0][1] = p2;
    temp[1][0] = p3;
    temp[1][1] = p4;
    Image image = new ImageImpl(2,2, temp);
    this.view = new ImageViewImpl(image);
    assertEquals(this.view.toString(), new ImageViewImpl(
            this.model.getImage("first-value")).toString());
  }

  @Test
  public void testGreyScaleIntensity() {
    this.model.uploadImage("first", this.image1);
    this.model.greyScaleModel("intensity", "first", "first-intensity");
    Pixel p1 = new Pixel(53, 53, 53);
    Pixel p2 = new Pixel(40, 40, 40);
    Pixel p3 = new Pixel(163, 163, 163);
    Pixel p4 = new Pixel(95, 95, 95);
    Pixel[][] temp = new Pixel[2][2];
    temp[0][0] = p1;
    temp[0][1] = p2;
    temp[1][0] = p3;
    temp[1][1] = p4;
    Image image = new ImageImpl(2,2, temp);
    this.view = new ImageViewImpl(image);
    assertEquals(this.view.toString(), new ImageViewImpl(
            this.model.getImage("first-intensity")).toString());
  }

  @Test
  public void testGreyScaleLuma() {
    this.model.uploadImage("first", this.image1);
    this.model.greyScaleModel("luma", "first", "first-luma");
    Pixel p1 = new Pixel(25, 25, 25);
    Pixel p2 = new Pixel(36, 36, 36);
    Pixel p3 = new Pixel(145, 145, 145);
    Pixel p4 = new Pixel(79, 79, 79);
    Pixel[][] temp = new Pixel[2][2];
    temp[0][0] = p1;
    temp[0][1] = p2;
    temp[1][0] = p3;
    temp[1][1] = p4;
    Image image = new ImageImpl(2,2, temp);
    this.view = new ImageViewImpl(image);
    assertEquals(this.view.toString(), new ImageViewImpl(
            this.model.getImage("first-luma")).toString());
  }

  @Test
  public void testGreyScaleDefault() {
    this.model.uploadImage("first", this.image1);
    this.model.greyScaleModel("", "first", "first-default");
    Pixel p1 = new Pixel(100, 0, 60);
    Pixel p2 = new Pixel(20, 40, 60);
    Pixel p3 = new Pixel(200, 130, 160);
    Pixel p4 = new Pixel(140, 64, 83);
    Pixel[][] temp = new Pixel[2][2];
    temp[0][0] = p1;
    temp[0][1] = p2;
    temp[1][0] = p3;
    temp[1][1] = p4;
    Image image = new ImageImpl(2,2, temp);
    this.view = new ImageViewImpl(image);
    assertEquals(this.view.toString(), new ImageViewImpl(
            this.model.getImage("first-default")).toString());
  }
}