import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import model.GUIProcessingModel;
import model.GUIProcessingModelImpl;
import model.Image;
import model.ImageImpl;
import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * testing class for Gui model class that serves as main model for GUI.
 */
public class GUIProcessingModelImplTest {
  private GUIProcessingModel model;
  private Image image;

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
    this.image = new ImageImpl(2, 2, pixels1);
    this.model = new GUIProcessingModelImpl();
  }

  @Test
  public void testFrequencies() {
    this.model.uploadImage("first", this.image);
    int[] redFrequencies = new int[256];
    int[] greenFrequencies = new int[256];
    int[] blueFrequencies = new int[256];
    int[] intensityFrequencies = new int[256];
    for (int i = 0; i < 256; i++) {
      redFrequencies[i] = 0;
      greenFrequencies[i] = 0;
      blueFrequencies[i] = 0;
      intensityFrequencies[i] = 0;
    }
    redFrequencies[100] += 1;
    redFrequencies[20] += 1;
    redFrequencies[200] += 1;
    redFrequencies[140] += 1;
    greenFrequencies[0] += 1;
    greenFrequencies[40] += 1;
    greenFrequencies[130] += 1;
    greenFrequencies[64] += 1;
    blueFrequencies[60] += 1;
    blueFrequencies[60] += 1;
    blueFrequencies[160] += 1;
    blueFrequencies[83] += 1;
    intensityFrequencies[53] += 1;
    intensityFrequencies[40] += 1;
    intensityFrequencies[163] += 1;
    intensityFrequencies[95] += 1;
    assertEquals(1, redFrequencies[100]);
    List<int[]> list = new ArrayList<>();
    list.add(redFrequencies);
    list.add(greenFrequencies);
    list.add(blueFrequencies);
    list.add(intensityFrequencies);
    for (int i = 0; i < 256; i++) {
      assertEquals(redFrequencies[i],
              this.model.convertFrequencies("first").get(0)[i]);
    }
    for (int i = 0; i < 256; i++) {
      assertEquals(greenFrequencies[i],
              this.model.convertFrequencies("first").get(1)[i]);
    }
    for (int i = 0; i < 256; i++) {
      assertEquals(blueFrequencies[i],
              this.model.convertFrequencies("first").get(2)[i]);
    }
    for (int i = 0; i < 256; i++) {
      assertEquals(intensityFrequencies[i],
              this.model.convertFrequencies("first").get(3)[i]);
    }
  }
}