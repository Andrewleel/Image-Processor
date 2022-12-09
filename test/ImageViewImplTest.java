import org.junit.Before;
import org.junit.Test;

import model.Image;
import model.ImageImpl;
import model.Pixel;
import view.ImageView;
import view.ImageViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test class for ImageView test in view package that I created for testing image purposes.
 */
public class ImageViewImplTest {
  private ImageView view1;
  private ImageView view2;

  @Before
  public void setUp() {
    Pixel p1 = new Pixel(100, 0, 60);
    Pixel p2 = new Pixel(20, 40, 60);
    Pixel p3 = new Pixel(200, 130, 160);
    Pixel p4 = new Pixel(140, 64, 83);
    Pixel[][] pixels1 = new Pixel[2][2];
    pixels1[0][0] = p1;
    pixels1[0][1] = p2;
    pixels1[1][0] = p3;
    pixels1[1][1] = p4;
    Image image1 = new ImageImpl(2, 2, pixels1);
    this.view1 = new ImageViewImpl(image1);

    Pixel p5 = new Pixel(180, 49, 75);
    Pixel p6 = new Pixel(40, 93, 231);
    Pixel p7 = new Pixel(210, 134, 84);
    Pixel p8 = new Pixel(40,156, 209);
    Pixel p9 = new Pixel(198, 43, 2);

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

    Image image2 = new ImageImpl(3, 3, pixels2);
    this.view2 = new ImageViewImpl(image2);
  }

  @Test
  public void testToString() {
    assertEquals("100 0 60\n"
            + "20 40 60\n"
            + "200 130 160\n"
            + "140 64 83\n", this.view1.toString());
    assertEquals("100 0 60\n"
            + "20 40 60\n"
            + "200 130 160\n"
            + "140 64 83\n"
            + "180 49 75\n"
            + "40 93 231\n"
            + "210 134 84\n"
            + "40 156 209\n"
            + "198 43 2\n", this.view2.toString());
  }
}