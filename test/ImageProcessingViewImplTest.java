import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import view.ImageProcessingView;
import view.ImageProcessingViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test class for ImageView that renders message to the appendable.
 */
public class ImageProcessingViewImplTest {
  private ImageProcessingView v1;
  private ImageProcessingView v2;
  private Appendable ap1;

  @Before
  public void initialize() {
    this.ap1 = new StringBuilder();
    this.v1 = new ImageProcessingViewImpl(ap1);
    Appendable ap2 = new CorruptAppendable();
    this.v2 = new ImageProcessingViewImpl(ap2);
  }

  @Test(expected = IOException.class)
  public void testRenderException() throws IOException {
    this.v2.render("Hi");
    this.v2.render("hey");
  }

  @Test
  public void testRender() throws IOException {
    this.v1.render("Hello");
    assertEquals(new StringBuilder("Hello").toString(), this.ap1.toString());
  }
}