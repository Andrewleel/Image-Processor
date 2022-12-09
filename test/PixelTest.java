import org.junit.Test;
import org.junit.Before;

import model.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

/**
 * test for Pixel class that test all the methods and also test initializing.
 */
public class PixelTest {
  private Pixel p1;
  private Pixel p2;
  private Pixel p3;
  private Pixel p4;
  private Pixel p5;
  private Pixel p6;
  private Pixel p7;

  @Before
  public void initialize() {
    this.p1 = new Pixel(50, 200, 13);
    this.p2 = new Pixel(21, 21, 21);
    this.p3 = new Pixel(200, 231, 1);
    this.p4 = new Pixel(100, 14, 180);
    this.p5 = new Pixel(1, 21, 210);
    this.p6 = new Pixel(1, 210, 12);
    this.p7 = new Pixel(142, 121, 111);
  }

  @Test
  public void testInitialize() {
    assertEquals(50, this.p1.getRed());
    assertEquals(21, this.p2.getGreen());
    assertEquals(1, this.p3.getBlue());
  }

  @Test
  public void testConstructorException() {
    assertThrows(IllegalArgumentException.class,
        () -> new Pixel(-1, 40, 30));
    assertThrows(IllegalArgumentException.class,
        () -> new Pixel(120, -12, 16));
    assertThrows(IllegalArgumentException.class,
        () -> new Pixel(111, 222, -233));
  }

  @Test
  public void testGetRed() {
    assertEquals(50, p1.getRed());
    assertEquals(21, p2.getRed());
    assertEquals(200, p3.getRed());
    assertEquals(100, p4.getRed());
  }

  @Test
  public void testGetGreen() {
    assertEquals(200, p1.getGreen());
    assertEquals(21, p2.getGreen());
    assertEquals(231, p3.getGreen());
    assertEquals(14, p4.getGreen());
  }

  @Test
  public void testGetBlue() {
    assertEquals(13, p1.getBlue());
    assertEquals(21, p2.getBlue());
    assertEquals(1, p3.getBlue());
    assertEquals(180, p4.getBlue());
  }

  @Test
  public void testGreyScalePixel() {
    assertEquals(new Pixel(50, 50, 50), p1.greyScalePixel("red"));
    assertEquals(p2, p2.greyScalePixel("green"));
    assertEquals(new Pixel(1, 1, 1), p3.greyScalePixel("blue"));
    assertEquals(new Pixel(180, 180, 180), p4.greyScalePixel("value"));
    assertEquals(new Pixel(77, 77, 77), p5.greyScalePixel("intensity"));
    assertEquals(new Pixel(150, 150, 150), p6.greyScalePixel("luma"));
    assertEquals(new Pixel(142, 121, 111), p7.greyScalePixel(""));
  }

  @Test
  public void testEquals() {
    assertTrue(p1.equals(new Pixel(50, 200, 13)));
    assertTrue(p3.equals(this.p3 = new Pixel(200, 231, 1)));
    assertTrue(p4.equals(new Pixel(100, 14, 180)));
    assertFalse(p5.equals(this.p3 = new Pixel(200, 231, 1)));
    assertFalse(p6.equals(new Pixel(100, 14, 180)));
    assertFalse(p6 == null);
  }

  @Test
  public void testHashCode() {
    assertEquals(new Pixel(50, 200, 13).hashCode(), p1.hashCode());
    assertEquals(new Pixel(1, 21, 210).hashCode(), p5.hashCode());
    assertEquals(new Pixel(1, 210, 12).hashCode(), p6.hashCode());
    assertEquals(new Pixel(142, 121, 111).hashCode(), p7.hashCode());
  }
}