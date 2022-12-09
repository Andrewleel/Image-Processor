import org.junit.Before;
import org.junit.Test;

import model.AdvancedProcessingModelImpl;
import model.Image;
import model.ImageImpl;
import model.Pixel;
import view.ImageView;
import view.ImageViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * test class for AdvancedImageProcessingModelImpl that serves as the Advanced model
 *              for our image processing program.
 */
public class AdvancedProcessingModelImplTest {
  private Image image1;
  private AdvancedProcessingModelImpl model;
  private ImageView view;

  @Before
  public void initialize() {
    Pixel p1 = new Pixel(100, 0, 60);
    Pixel p2 = new Pixel(20, 40, 60);
    Pixel p3 = new Pixel(200, 130, 160);
    Pixel p4 = new Pixel(140, 64, 83);
    Pixel[][] pixels1 = new Pixel[2][2];
    pixels1[0][0] = p1;
    pixels1[0][1] = p2;
    pixels1[1][0] = p3;
    pixels1[1][1] = p4;
    this.image1 = new ImageImpl(2, 2, pixels1);
    this.model = new AdvancedProcessingModelImpl();
    // initialize view in individual testing methods if view is needed.
  }

  @Test
  public void testBlur() {
    this.model.uploadImage("first", this.image1);
    this.model.blur( "first", "first-blur");
    Pixel p1 = new Pixel(61, 25, 47);
    Pixel p2 = new Pixel(42, 29, 41);
    Pixel p3 = new Pixel(77, 45, 58);
    Pixel p4 = new Pixel(53, 26, 36);
    Pixel[][] temp = new Pixel[2][2];
    temp[0][0] = p1;
    temp[0][1] = p2;
    temp[1][0] = p3;
    temp[1][1] = p4;
    Image image = new ImageImpl(2,2, temp);
    this.view = new ImageViewImpl(image);
    assertEquals(this.view.toString(), new ImageViewImpl(
            this.model.getImage("first-blur")).toString());
  }

  @Test
  public void testSharpen() {
    this.model.uploadImage("first", this.image1);
    this.model.sharpen( "first", "first-sharpen");
    Pixel p1 = new Pixel(190, 58, 135);
    Pixel p2 = new Pixel(152, 103, 154);
    Pixel p3 = new Pixel(255, 186, 253);
    Pixel p4 = new Pixel(255, 150, 218);
    Pixel[][] temp = new Pixel[2][2];
    temp[0][0] = p1;
    temp[0][1] = p2;
    temp[1][0] = p3;
    temp[1][1] = p4;
    Image image = new ImageImpl(2,2, temp);
    this.view = new ImageViewImpl(image);
    assertEquals(this.view.toString(), new ImageViewImpl(
            this.model.getImage("first-sharpen")).toString());
  }

  @Test
  public void testColorTransformationGreyScale() {
    this.model.uploadImage("first", this.image1);
    this.model.colorGreyScale( "first", "first-greyscale");
    Pixel p1 = new Pixel(25, 25, 25);
    Pixel p2 = new Pixel(37, 37, 37);
    Pixel p3 = new Pixel(147, 147, 147);
    Pixel p4 = new Pixel(81, 81, 81);
    Pixel[][] temp = new Pixel[2][2];
    temp[0][0] = p1;
    temp[0][1] = p2;
    temp[1][0] = p3;
    temp[1][1] = p4;
    Image image = new ImageImpl(2,2, temp);
    this.view = new ImageViewImpl(image);
    assertEquals(this.view.toString(), new ImageViewImpl(
            this.model.getImage("first-greyscale")).toString());
  }

  @Test
  public void testColorTransformationSepia() {
    this.model.uploadImage("first", this.image1);
    this.model.sepia( "first", "first-sepia");
    Pixel p1 = new Pixel(50, 44, 35);
    Pixel p2 = new Pixel(49, 44, 34);
    Pixel p3 = new Pixel(208, 185, 144);
    Pixel p4 = new Pixel(119, 106, 83);
    Pixel[][] temp = new Pixel[2][2];
    temp[0][0] = p1;
    temp[0][1] = p2;
    temp[1][0] = p3;
    temp[1][1] = p4;
    Image image = new ImageImpl(2,2, temp);
    this.view = new ImageViewImpl(image);
    assertEquals(this.view.toString(), new ImageViewImpl(
            this.model.getImage("first-sepia")).toString());
  }
}