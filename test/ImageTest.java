import org.junit.Before;
import org.junit.Test;

import model.Image;
import model.ImageImpl;
import model.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * test class for image that presents the image object with pixels.
 */
public class ImageTest {
  private Image image1;
  private Image image2;

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
    this.image2 = new ImageImpl(3, 3, pixels2, 256);
  }

  @Test
  public void testInitialize() {
    assertEquals(new Pixel(100, 0, 60), this.image1.getPixelAt(0,0));
    assertEquals(new Pixel(20, 40, 60), this.image1.getPixelAt(0,1));
    assertEquals(new Pixel(200, 130, 160), this.image1.getPixelAt(1,0));
    assertEquals(new Pixel(140, 64, 83), this.image1.getPixelAt(1,1));
    assertEquals(2, this.image1.getHeight());
    assertEquals(2, this.image1.getLength());
  }

  // negative length value
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptionFirst() {
    Pixel[][] pixels = new Pixel[1][1];
    Image image = new ImageImpl(-1, 1, pixels);
  }

  // negative height value
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptionSecond() {
    Pixel[][] pixels = new Pixel[1][1];
    Image image = new ImageImpl(1, -1, pixels);
  }

  // wrong length value
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptionThird() {
    Pixel[][] pixels = new Pixel[1][1];
    Image image = new ImageImpl(2, 1, pixels);
  }

  // wrong height value
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptionFourth() {
    Pixel[][] pixels = new Pixel[1][1];
    Image image = new ImageImpl(1, 4, pixels);
  }

  // blue red green value greater than maxValue
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptionFifth() {
    Pixel[][] pixels = new Pixel[1][1];
    pixels[0][0] = new Pixel(400, 200, 393);
    Image image = new ImageImpl(1, 1, pixels);
  }

  @Test
  public void testGetPixelAt() {
    assertEquals(new Pixel(100, 0, 60), this.image1.getPixelAt(0, 0));
    assertEquals(new Pixel(20, 40, 60), this.image1.getPixelAt(0, 1));
    assertEquals(new Pixel(200, 130, 160), this.image1.getPixelAt(1, 0));
    assertEquals(new Pixel(140, 64, 83), this.image1.getPixelAt(1, 1));
  }

  @Test
  public void testGetLength() {
    assertEquals(2, this.image1.getLength());
    assertEquals(3, this.image2.getLength());
  }

  @Test
  public void testGetHeight() {
    assertEquals(2, this.image1.getHeight());
    assertEquals(3, this.image2.getHeight());
  }

  @Test
  public void testGetMaxValue() {
    assertEquals(255, this.image1.getMaxValue());
    assertEquals(256, this.image2.getMaxValue());
  }

  @Test
  public void testHashCode() {
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
    Image tempImage = new ImageImpl(2, 2, pixels1);
    assertTrue(this.image1.hashCode() == tempImage.hashCode());

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
    Image tempImage2 = new ImageImpl(3, 3, pixels2);
    assertTrue(this.image2.hashCode() == tempImage2.hashCode());
  }

  @Test
  public void testEquals() {
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
    Image tempImage = new ImageImpl(2, 2, pixels1);
    assertTrue(this.image1.equals(tempImage));

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
    Image tempImage2 = new ImageImpl(3, 3, pixels2, 256);
    assertTrue(this.image2.equals(tempImage2));
  }
}