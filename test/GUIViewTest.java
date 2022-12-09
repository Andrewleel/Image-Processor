import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import java.awt.Dimension;

import controller.ImageProcessingGUIController;
import controller.ImageProcessingGUIControllerImpl;
import model.GUIProcessingModel;
import model.GUIProcessingModelImpl;
import model.Image;
import model.ImageImpl;
import model.Pixel;
import view.IGUIView;

import static org.junit.Assert.assertEquals;

/**
 * test class for GUI view mock that tests the testable GUI view.
 */
public class GUIViewTest {
  private StringBuilder sb;
  private IGUIView view;
  private ImageProcessingGUIController controller;

  @Before
  public void initialize() {
    GUIProcessingModel model;
    this.sb = new StringBuilder();
    model = new GUIProcessingModelImpl();
    this.view = new GUIMock(sb);
    this.controller = new ImageProcessingGUIControllerImpl(model);
  }

  @Test
  public void testAction() {
    this.view.actions(controller);
    assertEquals("Action processed \n", sb.toString());
  }

  @Test
  public void testAddImage() {
    Pixel p1 = new Pixel(100, 0, 60);
    Pixel p2 = new Pixel(20, 40, 60);
    Pixel p3 = new Pixel(200, 130, 160);
    Pixel p4 = new Pixel(140, 64, 83);
    Pixel[][] pixels1 = new Pixel[2][2];
    pixels1[0][0] = p1;
    pixels1[0][1] = p2;
    pixels1[1][0] = p3;
    pixels1[1][1] = p4;
    int[] array1 = new int[1];
    int[] array2 = new int[1];
    List<int[]> list = new ArrayList<>();
    list.add(array1);
    list.add(array2);
    Image image = new ImageImpl(2, 2, pixels1);
    this.view.addImage("image", image, list, new Dimension(200, 200));
    assertEquals("Image added \n", sb.toString());
  }

  @Test
  public void testUpload() {
    Pixel p1 = new Pixel(100, 0, 60);
    Pixel p2 = new Pixel(20, 40, 60);
    Pixel p3 = new Pixel(200, 130, 160);
    Pixel p4 = new Pixel(140, 64, 83);
    Pixel[][] pixels1 = new Pixel[2][2];
    pixels1[0][0] = p1;
    pixels1[0][1] = p2;
    pixels1[1][0] = p3;
    pixels1[1][1] = p4;
    int[] array1 = new int[1];
    int[] array2 = new int[1];
    List<int[]> list = new ArrayList<>();
    list.add(array1);
    list.add(array2);
    Image image = new ImageImpl(2, 2, pixels1);
    this.view.updateImage(image,"image", list, new Dimension(200, 200));
    assertEquals("Image updated \n", sb.toString());
  }

  @Test
  public void testRunMultiple() {
    Pixel p1 = new Pixel(100, 0, 60);
    Pixel p2 = new Pixel(20, 40, 60);
    Pixel p3 = new Pixel(200, 130, 160);
    Pixel p4 = new Pixel(140, 64, 83);
    Pixel[][] pixels1 = new Pixel[2][2];
    pixels1[0][0] = p1;
    pixels1[0][1] = p2;
    pixels1[1][0] = p3;
    pixels1[1][1] = p4;
    int[] array1 = new int[1];
    int[] array2 = new int[1];
    List<int[]> list = new ArrayList<>();
    list.add(array1);
    list.add(array2);
    Image image = new ImageImpl(2, 2, pixels1);
    this.view.addImage("image", image, list, new Dimension(200, 200));
    this.view.updateImage(image,"image", list, new Dimension(200, 200));
    assertEquals("Image added \nImage updated \n", sb.toString());
  }
}