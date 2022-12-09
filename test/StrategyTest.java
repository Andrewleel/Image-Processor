import org.junit.Before;
import org.junit.Test;

import model.GUIProcessingModel;
import model.GUIProcessingModelImpl;
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
import view.ImageView;
import view.ImageViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test class for all the strategies we have in our strategy file (Function objects that act as
 *            commands for the program.
 */
public class StrategyTest {
  private String[] strList;
  private IStrategy strategy;
  private GUIProcessingModel model;
  private Image image;
  private ImageView view;

  @Before
  public void initialize() {
    this.strList = new String[3];
    this.strList[0] = "horizontal-flip";
    this.strList[1] = "first";
    this.strList[2] = "first-horizontal";
    this.strategy = new Flip(strList);
    Pixel p1 = new Pixel(100, 0, 60);
    Pixel p2 = new Pixel(20, 40, 60);
    Pixel p3 = new Pixel(200, 130, 160);
    Pixel p4 = new Pixel(140, 64, 83);
    Pixel[][] pixels1 = new Pixel[2][2];
    pixels1[0][0] = p1;
    pixels1[0][1] = p2;
    pixels1[1][0] = p3;
    pixels1[1][1] = p4;
    this.image = new ImageImpl(2, 2, pixels1);
    this.model = new GUIProcessingModelImpl();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullBrighten() {
    IStrategy brighten = new Brighten(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullFlip() {
    IStrategy flip = new Flip(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullGreyScale() {
    IStrategy greyScale = new GreyScale(null);
  }

  @Test
  public void testRunHorizontal() {
    this.model.uploadImage("first", this.image);
    this.strategy.run(this.model);
    this.view = new ImageViewImpl(this.model.getImage("first-horizontal"));
    Pixel p1 = new Pixel(100, 0, 60);
    Pixel p2 = new Pixel(20, 40, 60);
    Pixel p3 = new Pixel(200, 130, 160);
    Pixel p4 = new Pixel(140, 64, 83);
    Pixel[][] pixels1 = new Pixel[2][2];
    pixels1[0][0] = p2;
    pixels1[0][1] = p1;
    pixels1[1][0] = p4;
    pixels1[1][1] = p3;
    Image temp = new ImageImpl(2,2, pixels1);
    assertEquals(new ImageViewImpl(temp).toString(), this.view.toString());
  }

  @Test
  public void testRunVertical() {

    // update the strList
    this.strList = new String[3];
    this.strList[0] = "vertical-flip";
    this.strList[1] = "first";
    this.strList[2] = "first-vertical";
    this.strategy = new Flip(strList);


    this.model.uploadImage("first", this.image);
    this.strategy.run(this.model);
    this.view = new ImageViewImpl(this.model.getImage("first-vertical"));
    Pixel p1 = new Pixel(100, 0, 60);
    Pixel p2 = new Pixel(20, 40, 60);
    Pixel p3 = new Pixel(200, 130, 160);
    Pixel p4 = new Pixel(140, 64, 83);
    Pixel[][] pixels1 = new Pixel[2][2];
    pixels1[0][0] = p3;
    pixels1[0][1] = p4;
    pixels1[1][0] = p1;
    pixels1[1][1] = p2;
    Image temp = new ImageImpl(2,2, pixels1);
    assertEquals(new ImageViewImpl(temp).toString(), this.view.toString());
  }

  @Test
  public void testRunBrighten() {
    this.strList = new String[4];
    this.strList[0] = "brighten";
    this.strList[1] = "50";
    this.strList[2] = "first";
    this.strList[3] = "first-brighter";
    this.strategy = new Brighten(strList);
    this.model.uploadImage("first", this.image);
    this.strategy.run(this.model);
    this.view = new ImageViewImpl(this.model.getImage("first-brighter"));
    Pixel p1 = new Pixel(150, 50, 110);
    Pixel p2 = new Pixel(70, 90, 110);
    Pixel p3 = new Pixel(250, 180, 210);
    Pixel p4 = new Pixel(190, 114, 133);
    Pixel[][] pixels1 = new Pixel[2][2];
    pixels1[0][0] = p1;
    pixels1[0][1] = p2;
    pixels1[1][0] = p3;
    pixels1[1][1] = p4;
    Image temp = new ImageImpl(2,2, pixels1);
    assertEquals(new ImageViewImpl(temp).toString(), this.view.toString());
  }

  @Test
  public void testRunDarken() {

    // update the strList for darken
    this.strList = new String[4];
    this.strList[0] = "brighten";
    this.strList[1] = "-50";
    this.strList[2] = "first";
    this.strList[3] = "first-darker";
    this.strategy = new Brighten(strList);

    this.model.uploadImage("first", this.image);
    this.strategy.run(this.model);
    this.view = new ImageViewImpl(this.model.getImage("first-darker"));
    Pixel p1 = new Pixel(50, 0, 10);
    Pixel p2 = new Pixel(0, 0, 10);
    Pixel p3 = new Pixel(150, 80, 110);
    Pixel p4 = new Pixel(90, 14, 33);
    Pixel[][] pixels1 = new Pixel[2][2];
    pixels1[0][0] = p1;
    pixels1[0][1] = p2;
    pixels1[1][0] = p3;
    pixels1[1][1] = p4;
    Image temp = new ImageImpl(2,2, pixels1);
    assertEquals(new ImageViewImpl(temp).toString(), this.view.toString());
  }

  @Test
  public void testRed() {

    // update strList
    this.strList = new String[3];
    this.strList[0] = "red-component";
    this.strList[1] = "first";
    this.strList[2] = "first-red";
    this.strategy = new GreyScale(strList);


    this.model.uploadImage("first", this.image);
    this.strategy.run(this.model);
    this.view = new ImageViewImpl(this.model.getImage("first-red"));
    Pixel p1 = new Pixel(100, 100, 100);
    Pixel p2 = new Pixel(20, 20, 20);
    Pixel p3 = new Pixel(200, 200, 200);
    Pixel p4 = new Pixel(140, 140, 140);
    Pixel[][] pixels1 = new Pixel[2][2];
    pixels1[0][0] = p1;
    pixels1[0][1] = p2;
    pixels1[1][0] = p3;
    pixels1[1][1] = p4;
    Image temp = new ImageImpl(2,2, pixels1);
    assertEquals(new ImageViewImpl(temp).toString(), this.view.toString());
  }

  @Test
  public void testGreen() {

    // update strList
    this.strList = new String[3];
    this.strList[0] = "green-component";
    this.strList[1] = "first";
    this.strList[2] = "first-green";
    this.strategy = new GreyScale(strList);


    this.model.uploadImage("first", this.image);
    this.strategy.run(this.model);
    this.view = new ImageViewImpl(this.model.getImage("first-green"));
    Pixel p1 = new Pixel(0, 0, 0);
    Pixel p2 = new Pixel(40, 40, 40);
    Pixel p3 = new Pixel(130, 130, 130);
    Pixel p4 = new Pixel(64, 64, 64);
    Pixel[][] pixels1 = new Pixel[2][2];
    pixels1[0][0] = p1;
    pixels1[0][1] = p2;
    pixels1[1][0] = p3;
    pixels1[1][1] = p4;
    Image temp = new ImageImpl(2,2, pixels1);
    assertEquals(new ImageViewImpl(temp).toString(), this.view.toString());
  }

  @Test
  public void testBlue() {

    // update strList
    this.strList = new String[3];
    this.strList[0] = "blue-component";
    this.strList[1] = "first";
    this.strList[2] = "first-blue";
    this.strategy = new GreyScale(strList);


    this.model.uploadImage("first", this.image);
    this.strategy.run(this.model);
    this.view = new ImageViewImpl(this.model.getImage("first-blue"));
    Pixel p1 = new Pixel(60, 60, 60);
    Pixel p2 = new Pixel(60, 60, 60);
    Pixel p3 = new Pixel(160, 160, 160);
    Pixel p4 = new Pixel(83, 83, 83);
    Pixel[][] pixels1 = new Pixel[2][2];
    pixels1[0][0] = p1;
    pixels1[0][1] = p2;
    pixels1[1][0] = p3;
    pixels1[1][1] = p4;
    Image temp = new ImageImpl(2,2, pixels1);
    assertEquals(new ImageViewImpl(temp).toString(), this.view.toString());
  }

  @Test
  public void testValue() {

    // update strList
    this.strList = new String[3];
    this.strList[0] = "value-component";
    this.strList[1] = "first";
    this.strList[2] = "first-value";
    this.strategy = new GreyScale(strList);


    this.model.uploadImage("first", this.image);
    this.strategy.run(this.model);
    this.view = new ImageViewImpl(this.model.getImage("first-value"));
    Pixel p1 = new Pixel(100, 100, 100);
    Pixel p2 = new Pixel(60, 60, 60);
    Pixel p3 = new Pixel(200, 200, 200);
    Pixel p4 = new Pixel(140, 140, 140);
    Pixel[][] pixels1 = new Pixel[2][2];
    pixels1[0][0] = p1;
    pixels1[0][1] = p2;
    pixels1[1][0] = p3;
    pixels1[1][1] = p4;
    Image temp = new ImageImpl(2,2, pixels1);
    assertEquals(new ImageViewImpl(temp).toString(), this.view.toString());
  }

  @Test
  public void testIntensity() {

    // update strList
    this.strList = new String[3];
    this.strList[0] = "intensity-component";
    this.strList[1] = "first";
    this.strList[2] = "first-intensity";
    this.strategy = new GreyScale(strList);


    this.model.uploadImage("first", this.image);
    this.strategy.run(this.model);
    this.view = new ImageViewImpl(this.model.getImage("first-intensity"));
    Pixel p1 = new Pixel(53, 53, 53);
    Pixel p2 = new Pixel(40, 40, 40);
    Pixel p3 = new Pixel(163, 163, 163);
    Pixel p4 = new Pixel(95, 95, 95);
    Pixel[][] pixels1 = new Pixel[2][2];
    pixels1[0][0] = p1;
    pixels1[0][1] = p2;
    pixels1[1][0] = p3;
    pixels1[1][1] = p4;
    Image temp = new ImageImpl(2,2, pixels1);
    assertEquals(new ImageViewImpl(temp).toString(), this.view.toString());
  }

  @Test
  public void testLuma() {

    // update strList
    this.strList = new String[3];
    this.strList[0] = "luma-component";
    this.strList[1] = "first";
    this.strList[2] = "first-luma";
    this.strategy = new GreyScale(strList);


    this.model.uploadImage("first", this.image);
    this.strategy.run(this.model);
    this.view = new ImageViewImpl(this.model.getImage("first-luma"));
    Pixel p1 = new Pixel(25, 25, 25);
    Pixel p2 = new Pixel(36, 36, 36);
    Pixel p3 = new Pixel(145, 145, 145);
    Pixel p4 = new Pixel(79, 79, 79);
    Pixel[][] pixels1 = new Pixel[2][2];
    pixels1[0][0] = p1;
    pixels1[0][1] = p2;
    pixels1[1][0] = p3;
    pixels1[1][1] = p4;
    Image temp = new ImageImpl(2,2, pixels1);
    assertEquals(new ImageViewImpl(temp).toString(), this.view.toString());
  }

  @Test
  public void testDefault() {

    // update strList
    this.strList = new String[3];
    this.strList[0] = " ";
    this.strList[1] = "first";
    this.strList[2] = "first-default";
    this.strategy = new GreyScale(strList);


    this.model.uploadImage("first", this.image);
    this.strategy.run(this.model);
    this.view = new ImageViewImpl(this.model.getImage("first-default"));
    Pixel p1 = new Pixel(100, 0, 60);
    Pixel p2 = new Pixel(20, 40, 60);
    Pixel p3 = new Pixel(200, 130, 160);
    Pixel p4 = new Pixel(140, 64, 83);
    Pixel[][] pixels1 = new Pixel[2][2];
    pixels1[0][0] = p1;
    pixels1[0][1] = p2;
    pixels1[1][0] = p3;
    pixels1[1][1] = p4;
    Image temp = new ImageImpl(2,2, pixels1);
    assertEquals(new ImageViewImpl(temp).toString(), this.view.toString());
  }

  @Test
  public void testBlur() {

    // update strList
    this.strList = new String[3];
    this.strList[0] = "blur";
    this.strList[1] = "first";
    this.strList[2] = "first-blur";
    this.strategy = new Blur(strList);


    this.model.uploadImage("first", this.image);
    this.strategy.run(this.model);
    this.view = new ImageViewImpl(this.model.getImage("first-blur"));
    Pixel p1 = new Pixel(61, 25, 47);
    Pixel p2 = new Pixel(42, 29, 41);
    Pixel p3 = new Pixel(77, 45, 58);
    Pixel p4 = new Pixel(53, 26, 36);
    Pixel[][] pixels1 = new Pixel[2][2];
    pixels1[0][0] = p1;
    pixels1[0][1] = p2;
    pixels1[1][0] = p3;
    pixels1[1][1] = p4;
    Image temp = new ImageImpl(2,2, pixels1);
    assertEquals(new ImageViewImpl(temp).toString(), this.view.toString());
  }

  @Test
  public void testSharpen() {

    // update strList
    this.strList = new String[3];
    this.strList[0] = "sharpen";
    this.strList[1] = "first";
    this.strList[2] = "first-sharpen";
    this.strategy = new Sharpen(strList);


    this.model.uploadImage("first", this.image);
    this.strategy.run(this.model);
    this.view = new ImageViewImpl(this.model.getImage("first-sharpen"));
    Pixel p1 = new Pixel(190, 58, 135);
    Pixel p2 = new Pixel(152, 103, 154);
    Pixel p3 = new Pixel(255, 186, 253);
    Pixel p4 = new Pixel(255, 150, 218);
    Pixel[][] pixels1 = new Pixel[2][2];
    pixels1[0][0] = p1;
    pixels1[0][1] = p2;
    pixels1[1][0] = p3;
    pixels1[1][1] = p4;
    Image temp = new ImageImpl(2,2, pixels1);
    assertEquals(new ImageViewImpl(temp).toString(), this.view.toString());
  }

  @Test
  public void testColorTransformationGreyScale() {

    // update strList
    this.strList = new String[3];
    this.strList[0] = "greyscale";
    this.strList[1] = "first";
    this.strList[2] = "first-greyscale";
    this.strategy = new ColorTransformation(strList);


    this.model.uploadImage("first", this.image);
    this.strategy.run(this.model);
    this.view = new ImageViewImpl(this.model.getImage("first-greyscale"));
    Pixel p1 = new Pixel(25, 25, 25);
    Pixel p2 = new Pixel(37, 37, 37);
    Pixel p3 = new Pixel(147, 147, 147);
    Pixel p4 = new Pixel(81, 81, 81);
    Pixel[][] pixels1 = new Pixel[2][2];
    pixels1[0][0] = p1;
    pixels1[0][1] = p2;
    pixels1[1][0] = p3;
    pixels1[1][1] = p4;
    Image temp = new ImageImpl(2,2, pixels1);
    assertEquals(new ImageViewImpl(temp).toString(), this.view.toString());
  }

  @Test
  public void testColorTransformationSepia() {

    // update strList
    this.strList = new String[3];
    this.strList[0] = "sepia";
    this.strList[1] = "first";
    this.strList[2] = "first-sepia";
    this.strategy = new ColorTransformation(strList);


    this.model.uploadImage("first", this.image);
    this.strategy.run(this.model);
    this.view = new ImageViewImpl(this.model.getImage("first-sepia"));
    Pixel p1 = new Pixel(50, 44, 35);
    Pixel p2 = new Pixel(49, 44, 34);
    Pixel p3 = new Pixel(208, 185, 144);
    Pixel p4 = new Pixel(119, 106, 83);
    Pixel[][] pixels1 = new Pixel[2][2];
    pixels1[0][0] = p1;
    pixels1[0][1] = p2;
    pixels1[1][0] = p3;
    pixels1[1][1] = p4;
    Image temp = new ImageImpl(2,2, pixels1);
    assertEquals(new ImageViewImpl(temp).toString(), this.view.toString());
  }
}
