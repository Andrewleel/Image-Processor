import org.junit.Test;
import org.junit.Before;

import java.io.StringReader;

import controller.ImageProcessingControllerImpl;
import controller.ImageProcessingController;
import model.GUIProcessingModel;
import model.GUIProcessingModelImpl;
import model.Image;
import model.ImageImpl;
import model.Pixel;
import view.ImageProcessingView;
import view.ImageProcessingViewImpl;
import view.ImageView;
import view.ImageViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the controller of the program that mainly tests how the process method
 *              impacts the program.
 */
public class ImageProcessingControllerImplTest {
  private GUIProcessingModel model;
  private ImageProcessingView view;
  private ImageProcessingController controller;
  private Image image;
  private Readable rd;
  private Appendable ap;

  @Before
  public void initialize() {
    this.ap = new StringBuilder();
    this.model = new GUIProcessingModelImpl();
    this.view = new ImageProcessingViewImpl(ap);
    Pixel p1 = new Pixel(100, 0, 60);
    Pixel p2 = new Pixel(20, 40, 60);
    Pixel p3 = new Pixel(200, 130, 160);
    Pixel p4 = new Pixel(140, 64, 83);
    Pixel[][] pixels = new Pixel[2][2];
    pixels[0][0] = p1;
    pixels[0][1] = p2;
    pixels[1][0] = p3;
    pixels[1][1] = p4;
    this.image = new ImageImpl(2, 2, pixels);
    this.model.uploadImage("first", this.image);
  }

  @Test
  public void testInitialize() {
    assertEquals(new Pixel(100, 0, 60), this.image.getPixelAt(0,0));
    assertEquals(new Pixel(20, 40, 60), this.image.getPixelAt(0,1));
    assertEquals(new Pixel(200, 130, 160), this.image.getPixelAt(1,0));
    assertEquals(new Pixel(140, 64, 83), this.image.getPixelAt(1,1));
    assertEquals(2, this.image.getHeight());
    assertEquals(2, this.image.getLength());
    assertEquals(this.image, this.model.getImage("first"));
  }

  @Test(expected = IllegalStateException.class)
  public void testThrowIllegalStateException() {
    this.ap = new CorruptAppendable();
    this.view = new ImageProcessingViewImpl(this.ap);
    this.rd = new StringReader("load res/test/two.ppm test");
    this.controller = new ImageProcessingControllerImpl(this.model, this.view, this.rd);
    this.controller.process();
  }

  @Test
  public void testInitialDirectionMessage() {
    this.rd = new StringReader(" ");
    this.controller = new ImageProcessingControllerImpl(this.model, this.view, this.rd);
    this.controller.process();
    assertEquals("DIRECTIONS for the image processor:\n" +
            "    - Quit the program:                  q or quit. Anywhere in your input\n" +
            "    - Load an image:                     load imagePath imageName\n" +
            "    - Flip the image vertically:         vertical-flip imageName newImageName\n" +
            "    - Flip the image horizontally:       horizontal-flip imageName newImageName\n" +
            "    - Brighten the image:                brighten positive-number imageName "
            + "newImageName\n" +
            "    - Darken the image:                  brighten negative-number imageName "
            + "newImageName\n" +
            "    - Red-Component Greyscale:           red-component imageName newImageName\n" +
            "    - Green-Component Greyscale:         green-component imageName newImageName\n" +
            "    - Blue-Component Greyscale:          blue-component imageName newImageName\n" +
            "    - Value-Component Greyscale:         value-component imageName newImageName\n" +
            "    - Intensity-Component Greyscale:     intensity-component imageName "
            + "newImageName\n" +
            "    - Luma-Component Greyscale:          luma-component imageName newImageName\n" +
            "    - Blur the image:                    blur imageName newImageName\n" +
            "    - Sharpen the image:                 sharpen imageName newImageName\n" +
            "    - ColorTransformation(GreyScale):    greyscale imageName newImageName\n" +
            "    - ColorTransformation(sepia):        sepia imageName newImageName\n" +
            "Enter:\n", this.ap.toString());
  }

  @Test
  public void testUploadPNG() {
    this.rd = new StringReader("load res/test/square.png square");
    this.controller = new ImageProcessingControllerImpl(this.model, this.view, this.rd);
    this.controller.process();
    Pixel p1 = new Pixel(100, 50, 250);
    Pixel p2 = new Pixel(100, 50, 250);
    Pixel p3 = new Pixel(100, 50, 250);
    Pixel p4 = new Pixel(100, 50, 250);
    Pixel[][] pixelsTemp = new Pixel[2][2];
    pixelsTemp[0][0] = p1;
    pixelsTemp[0][1] = p2;
    pixelsTemp[1][0] = p3;
    pixelsTemp[1][1] = p4;
    Image temp = this.model.getImage("square");
    assertEquals(temp, new ImageImpl(2,2, pixelsTemp));
    assertEquals("DIRECTIONS for the image processor:\n" +
            "    - Quit the program:                  q or quit. Anywhere in your input\n" +
            "    - Load an image:                     load imagePath imageName\n" +
            "    - Flip the image vertically:         vertical-flip imageName newImageName\n" +
            "    - Flip the image horizontally:       horizontal-flip imageName newImageName\n" +
            "    - Brighten the image:                brighten positive-number imageName "
            + "newImageName\n" +
            "    - Darken the image:                  brighten negative-number imageName "
            + "newImageName\n" +
            "    - Red-Component Greyscale:           red-component imageName newImageName\n" +
            "    - Green-Component Greyscale:         green-component imageName newImageName\n" +
            "    - Blue-Component Greyscale:          blue-component imageName newImageName\n" +
            "    - Value-Component Greyscale:         value-component imageName newImageName\n" +
            "    - Intensity-Component Greyscale:     intensity-component imageName "
            + "newImageName\n" +
            "    - Luma-Component Greyscale:          luma-component imageName newImageName\n" +
            "    - Blur the image:                    blur imageName newImageName\n" +
            "    - Sharpen the image:                 sharpen imageName newImageName\n" +
            "    - ColorTransformation(GreyScale):    greyscale imageName newImageName\n" +
            "    - ColorTransformation(sepia):        sepia imageName newImageName\n" +
            "Enter:\n" +
            "ImageImpl loading... \n" +
            "ImageImpl loaded!\n", this.ap.toString());
  }

  @Test
  public void testPNGSave() {
    this.rd = new StringReader("load res/test/square.png square\nsave"
            + " res/test/square2.png square\nload res/test/square2.png square2");
    this.controller = new ImageProcessingControllerImpl(this.model, this.view, this.rd);
    this.controller.process();
    assertEquals("DIRECTIONS for the image processor:\n" +
            "    - Quit the program:                  q or quit. Anywhere in your input\n" +
            "    - Load an image:                     load imagePath imageName\n" +
            "    - Flip the image vertically:         vertical-flip imageName newImageName\n" +
            "    - Flip the image horizontally:       horizontal-flip imageName newImageName\n" +
            "    - Brighten the image:                brighten positive-number imageName "
            + "newImageName\n" +
            "    - Darken the image:                  brighten negative-number imageName "
            + "newImageName\n" +
            "    - Red-Component Greyscale:           red-component imageName newImageName\n" +
            "    - Green-Component Greyscale:         green-component imageName newImageName\n" +
            "    - Blue-Component Greyscale:          blue-component imageName newImageName\n" +
            "    - Value-Component Greyscale:         value-component imageName newImageName\n" +
            "    - Intensity-Component Greyscale:     intensity-component imageName "
            + "newImageName\n" +
            "    - Luma-Component Greyscale:          luma-component imageName newImageName\n" +
            "    - Blur the image:                    blur imageName newImageName\n" +
            "    - Sharpen the image:                 sharpen imageName newImageName\n" +
            "    - ColorTransformation(GreyScale):    greyscale imageName newImageName\n" +
            "    - ColorTransformation(sepia):        sepia imageName newImageName\n" +
            "Enter:\n" +
            "ImageImpl loading... \n" +
            "ImageImpl loaded!\n" +
            "ImageImpl saving... \n" +
            "ImageImpl saved\n" +
            "ImageImpl loading... \n" +
            "ImageImpl loaded!\n", this.ap.toString());
    Image image1 = this.model.getImage("square2");
    Image image2 = this.model.getImage("square");
    ImageView view1 = new ImageViewImpl(image1);
    ImageView view2 = new ImageViewImpl(image2);
    assertEquals(image1, image2);
    assertEquals(view1.toString(), view2.toString());
  }

  @Test
  public void testUploadJPG() {
    this.rd = new StringReader("load res/test/square.jpg square");
    this.controller = new ImageProcessingControllerImpl(this.model, this.view, this.rd);
    this.controller.process();
    Pixel p1 = new Pixel(101, 50, 251);
    Pixel p2 = new Pixel(101, 50, 251);
    Pixel p3 = new Pixel(101, 50, 251);
    Pixel p4 = new Pixel(101, 50, 251);
    Pixel[][] pixelsTemp = new Pixel[2][2];
    pixelsTemp[0][0] = p1;
    pixelsTemp[0][1] = p2;
    pixelsTemp[1][0] = p3;
    pixelsTemp[1][1] = p4;
    Image temp = this.model.getImage("square");
    assertEquals(temp, new ImageImpl(2,2, pixelsTemp));
    assertEquals("DIRECTIONS for the image processor:\n" +
            "    - Quit the program:                  q or quit. Anywhere in your input\n" +
            "    - Load an image:                     load imagePath imageName\n" +
            "    - Flip the image vertically:         vertical-flip imageName newImageName\n" +
            "    - Flip the image horizontally:       horizontal-flip imageName newImageName\n" +
            "    - Brighten the image:                brighten positive-number imageName "
            + "newImageName\n" +
            "    - Darken the image:                  brighten negative-number imageName "
            + "newImageName\n" +
            "    - Red-Component Greyscale:           red-component imageName newImageName\n" +
            "    - Green-Component Greyscale:         green-component imageName newImageName\n" +
            "    - Blue-Component Greyscale:          blue-component imageName newImageName\n" +
            "    - Value-Component Greyscale:         value-component imageName newImageName\n" +
            "    - Intensity-Component Greyscale:     intensity-component imageName "
            + "newImageName\n" +
            "    - Luma-Component Greyscale:          luma-component imageName newImageName\n" +
            "    - Blur the image:                    blur imageName newImageName\n" +
            "    - Sharpen the image:                 sharpen imageName newImageName\n" +
            "    - ColorTransformation(GreyScale):    greyscale imageName newImageName\n" +
            "    - ColorTransformation(sepia):        sepia imageName newImageName\n" +
            "Enter:\n" +
            "ImageImpl loading... \n" +
            "ImageImpl loaded!\n", this.ap.toString());
  }

  @Test
  public void testJPGSave() {
    this.rd = new StringReader("load res/test/square.jpg square\nsave"
            + " res/test/square2.jpg square\nload res/test/square2.jpg square2");
    this.controller = new ImageProcessingControllerImpl(this.model, this.view, this.rd);
    this.controller.process();
    assertEquals("DIRECTIONS for the image processor:\n" +
            "    - Quit the program:                  q or quit. Anywhere in your input\n" +
            "    - Load an image:                     load imagePath imageName\n" +
            "    - Flip the image vertically:         vertical-flip imageName newImageName\n" +
            "    - Flip the image horizontally:       horizontal-flip imageName newImageName\n" +
            "    - Brighten the image:                brighten positive-number imageName "
            + "newImageName\n" +
            "    - Darken the image:                  brighten negative-number imageName "
            + "newImageName\n" +
            "    - Red-Component Greyscale:           red-component imageName newImageName\n" +
            "    - Green-Component Greyscale:         green-component imageName newImageName\n" +
            "    - Blue-Component Greyscale:          blue-component imageName newImageName\n" +
            "    - Value-Component Greyscale:         value-component imageName newImageName\n" +
            "    - Intensity-Component Greyscale:     intensity-component imageName "
            + "newImageName\n" +
            "    - Luma-Component Greyscale:          luma-component imageName newImageName\n" +
            "    - Blur the image:                    blur imageName newImageName\n" +
            "    - Sharpen the image:                 sharpen imageName newImageName\n" +
            "    - ColorTransformation(GreyScale):    greyscale imageName newImageName\n" +
            "    - ColorTransformation(sepia):        sepia imageName newImageName\n" +
            "Enter:\n" +
            "ImageImpl loading... \n" +
            "ImageImpl loaded!\n" +
            "ImageImpl saving... \n" +
            "ImageImpl saved\n" +
            "ImageImpl loading... \n" +
            "ImageImpl loaded!\n", this.ap.toString());
    Image image1 = this.model.getImage("square2");
    Image image2 = this.model.getImage("square");
    ImageView view1 = new ImageViewImpl(image1);
    ImageView view2 = new ImageViewImpl(image2);
    assertEquals(image1, image2);
    assertEquals(view1.toString(), view2.toString());
  }

  @Test
  public void testProcessLoad() {
    this.rd = new StringReader("load res/test/two.ppm test");
    this.controller = new ImageProcessingControllerImpl(this.model, this.view, this.rd);
    this.controller.process();
    Pixel p1 = new Pixel(255, 0, 0);
    Pixel p2 = new Pixel(0, 255, 0);
    Pixel p3 = new Pixel(0, 0, 0);
    Pixel p4 = new Pixel(255, 0, 0);
    Pixel[][] pixelsTemp = new Pixel[2][2];
    pixelsTemp[0][0] = p1;
    pixelsTemp[0][1] = p2;
    pixelsTemp[1][0] = p3;
    pixelsTemp[1][1] = p4;
    Image temp = this.model.getImage("test");
    assertEquals(temp, new ImageImpl(2,2, pixelsTemp));
    assertEquals("DIRECTIONS for the image processor:\n" +
            "    - Quit the program:                  q or quit. Anywhere in your input\n" +
            "    - Load an image:                     load imagePath imageName\n" +
            "    - Flip the image vertically:         vertical-flip imageName newImageName\n" +
            "    - Flip the image horizontally:       horizontal-flip imageName newImageName\n" +
            "    - Brighten the image:                brighten positive-number imageName "
            + "newImageName\n" +
            "    - Darken the image:                  brighten negative-number imageName "
            + "newImageName\n" +
            "    - Red-Component Greyscale:           red-component imageName newImageName\n" +
            "    - Green-Component Greyscale:         green-component imageName newImageName\n" +
            "    - Blue-Component Greyscale:          blue-component imageName newImageName\n" +
            "    - Value-Component Greyscale:         value-component imageName newImageName\n" +
            "    - Intensity-Component Greyscale:     intensity-component imageName "
            + "newImageName\n" +
            "    - Luma-Component Greyscale:          luma-component imageName newImageName\n" +
            "    - Blur the image:                    blur imageName newImageName\n" +
            "    - Sharpen the image:                 sharpen imageName newImageName\n" +
            "    - ColorTransformation(GreyScale):    greyscale imageName newImageName\n" +
            "    - ColorTransformation(sepia):        sepia imageName newImageName\n" +
            "Enter:\n" +
            "ImageImpl loading... \n" +
            "ImageImpl loaded!\n", this.ap.toString());
  }

  @Test
  public void testProcessVerticalFlip() {
    this.rd = new StringReader("load res/test/two.ppm test\nvertical-flip test test-vertical");
    this.controller = new ImageProcessingControllerImpl(this.model, this.view, this.rd);
    this.controller.process();
    Pixel p1 = new Pixel(255, 0, 0);
    Pixel p2 = new Pixel(0, 255, 0);
    Pixel p3 = new Pixel(0, 0, 0);
    Pixel p4 = new Pixel(255, 0, 0);
    Pixel[][] pixelsTemp = new Pixel[2][2];
    pixelsTemp[0][0] = p3;
    pixelsTemp[0][1] = p4;
    pixelsTemp[1][0] = p1;
    pixelsTemp[1][1] = p2;
    Image temp = this.model.getImage("test-vertical");
    assertEquals(temp, new ImageImpl(2,2, pixelsTemp));
    assertEquals("DIRECTIONS for the image processor:\n" +
            "    - Quit the program:                  q or quit. Anywhere in your input\n" +
            "    - Load an image:                     load imagePath imageName\n" +
            "    - Flip the image vertically:         vertical-flip imageName newImageName\n" +
            "    - Flip the image horizontally:       horizontal-flip imageName newImageName\n" +
            "    - Brighten the image:                brighten positive-number imageName "
            + "newImageName\n" +
            "    - Darken the image:                  brighten negative-number imageName "
            + "newImageName\n" +
            "    - Red-Component Greyscale:           red-component imageName newImageName\n" +
            "    - Green-Component Greyscale:         green-component imageName newImageName\n" +
            "    - Blue-Component Greyscale:          blue-component imageName newImageName\n" +
            "    - Value-Component Greyscale:         value-component imageName newImageName\n" +
            "    - Intensity-Component Greyscale:     intensity-component imageName "
            + "newImageName\n" +
            "    - Luma-Component Greyscale:          luma-component imageName newImageName\n" +
            "    - Blur the image:                    blur imageName newImageName\n" +
            "    - Sharpen the image:                 sharpen imageName newImageName\n" +
            "    - ColorTransformation(GreyScale):    greyscale imageName newImageName\n" +
            "    - ColorTransformation(sepia):        sepia imageName newImageName\n" +
            "Enter:\n" +
            "ImageImpl loading... \n" +
            "ImageImpl loaded!\n" +
            "vertical-flip processed!\n", this.ap.toString());
  }

  @Test
  public void testProcessHorizontalFlip() {
    this.rd = new StringReader("load res/test/two.ppm test\nhorizontal-flip "
            + "test test-horizontal");
    this.controller = new ImageProcessingControllerImpl(this.model, this.view, this.rd);
    this.controller.process();
    Pixel p1 = new Pixel(255, 0, 0);
    Pixel p2 = new Pixel(0, 255, 0);
    Pixel p3 = new Pixel(0, 0, 0);
    Pixel p4 = new Pixel(255, 0, 0);
    Pixel[][] pixelsTemp = new Pixel[2][2];
    pixelsTemp[0][0] = p2;
    pixelsTemp[0][1] = p1;
    pixelsTemp[1][0] = p4;
    pixelsTemp[1][1] = p3;
    Image temp = this.model.getImage("test-horizontal");
    assertEquals(temp, new ImageImpl(2,2, pixelsTemp));
    assertEquals("DIRECTIONS for the image processor:\n" +
            "    - Quit the program:                  q or quit. Anywhere in your input\n" +
            "    - Load an image:                     load imagePath imageName\n" +
            "    - Flip the image vertically:         vertical-flip imageName newImageName\n" +
            "    - Flip the image horizontally:       horizontal-flip imageName newImageName\n" +
            "    - Brighten the image:                brighten positive-number imageName "
            + "newImageName\n" +
            "    - Darken the image:                  brighten negative-number imageName "
            + "newImageName\n" +
            "    - Red-Component Greyscale:           red-component imageName newImageName\n" +
            "    - Green-Component Greyscale:         green-component imageName newImageName\n" +
            "    - Blue-Component Greyscale:          blue-component imageName newImageName\n" +
            "    - Value-Component Greyscale:         value-component imageName newImageName\n" +
            "    - Intensity-Component Greyscale:     intensity-component imageName "
            + "newImageName\n" +
            "    - Luma-Component Greyscale:          luma-component imageName newImageName\n" +
            "    - Blur the image:                    blur imageName newImageName\n" +
            "    - Sharpen the image:                 sharpen imageName newImageName\n" +
            "    - ColorTransformation(GreyScale):    greyscale imageName newImageName\n" +
            "    - ColorTransformation(sepia):        sepia imageName newImageName\n" +
            "Enter:\n" +
            "ImageImpl loading... \n" +
            "ImageImpl loaded!\n" +
            "horizontal-flip processed!\n", this.ap.toString());
  }

  @Test
  public void testProcessBrighten() {
    this.rd = new StringReader("load res/test/two.ppm test\nbrighten 50 test test-brighter");
    this.controller = new ImageProcessingControllerImpl(this.model, this.view, this.rd);
    this.controller.process();
    Pixel p1 = new Pixel(255, 50, 50);
    Pixel p2 = new Pixel(50, 255, 50);
    Pixel p3 = new Pixel(50, 50, 50);
    Pixel p4 = new Pixel(255, 50, 50);
    Pixel[][] pixelsTemp = new Pixel[2][2];
    pixelsTemp[0][0] = p1;
    pixelsTemp[0][1] = p2;
    pixelsTemp[1][0] = p3;
    pixelsTemp[1][1] = p4;
    Image temp = this.model.getImage("test-brighter");
    assertEquals(temp, new ImageImpl(2,2, pixelsTemp));
    assertEquals("DIRECTIONS for the image processor:\n" +
            "    - Quit the program:                  q or quit. Anywhere in your input\n" +
            "    - Load an image:                     load imagePath imageName\n" +
            "    - Flip the image vertically:         vertical-flip imageName newImageName\n" +
            "    - Flip the image horizontally:       horizontal-flip imageName newImageName\n" +
            "    - Brighten the image:                brighten positive-number imageName "
            + "newImageName\n" +
            "    - Darken the image:                  brighten negative-number imageName "
            + "newImageName\n" +
            "    - Red-Component Greyscale:           red-component imageName newImageName\n" +
            "    - Green-Component Greyscale:         green-component imageName newImageName\n" +
            "    - Blue-Component Greyscale:          blue-component imageName newImageName\n" +
            "    - Value-Component Greyscale:         value-component imageName newImageName\n" +
            "    - Intensity-Component Greyscale:     intensity-component imageName "
            + "newImageName\n" +
            "    - Luma-Component Greyscale:          luma-component imageName newImageName\n" +
            "    - Blur the image:                    blur imageName newImageName\n" +
            "    - Sharpen the image:                 sharpen imageName newImageName\n" +
            "    - ColorTransformation(GreyScale):    greyscale imageName newImageName\n" +
            "    - ColorTransformation(sepia):        sepia imageName newImageName\n" +
            "Enter:\n" +
            "ImageImpl loading... \n" +
            "ImageImpl loaded!\n" +
            "brighten processed!\n", this.ap.toString());
  }

  @Test
  public void testProcessDarken() {
    this.rd = new StringReader("load res/test/two.ppm test\nbrighten -50 test test-darker");
    this.controller = new ImageProcessingControllerImpl(this.model, this.view, this.rd);
    this.controller.process();
    Pixel p1 = new Pixel(205, 0, 0);
    Pixel p2 = new Pixel(0, 205, 0);
    Pixel p3 = new Pixel(0, 0, 0);
    Pixel p4 = new Pixel(205, 0, 0);
    Pixel[][] pixelsTemp = new Pixel[2][2];
    pixelsTemp[0][0] = p1;
    pixelsTemp[0][1] = p2;
    pixelsTemp[1][0] = p3;
    pixelsTemp[1][1] = p4;
    Image temp = this.model.getImage("test-darker");
    assertEquals(temp, new ImageImpl(2,2, pixelsTemp));
    assertEquals("DIRECTIONS for the image processor:\n" +
            "    - Quit the program:                  q or quit. Anywhere in your input\n" +
            "    - Load an image:                     load imagePath imageName\n" +
            "    - Flip the image vertically:         vertical-flip imageName newImageName\n" +
            "    - Flip the image horizontally:       horizontal-flip imageName newImageName\n" +
            "    - Brighten the image:                brighten positive-number imageName "
            + "newImageName\n" +
            "    - Darken the image:                  brighten negative-number imageName "
            + "newImageName\n" +
            "    - Red-Component Greyscale:           red-component imageName newImageName\n" +
            "    - Green-Component Greyscale:         green-component imageName newImageName\n" +
            "    - Blue-Component Greyscale:          blue-component imageName newImageName\n" +
            "    - Value-Component Greyscale:         value-component imageName newImageName\n" +
            "    - Intensity-Component Greyscale:     intensity-component imageName "
            + "newImageName\n" +
            "    - Luma-Component Greyscale:          luma-component imageName newImageName\n" +
            "    - Blur the image:                    blur imageName newImageName\n" +
            "    - Sharpen the image:                 sharpen imageName newImageName\n" +
            "    - ColorTransformation(GreyScale):    greyscale imageName newImageName\n" +
            "    - ColorTransformation(sepia):        sepia imageName newImageName\n" +
            "Enter:\n" +
            "ImageImpl loading... \n" +
            "ImageImpl loaded!\n" +
            "darken processed!\n", this.ap.toString());
  }

  @Test
  public void testProcessRed() {
    this.rd = new StringReader("load res/test/two.ppm test\nred-component test test-red");
    this.controller = new ImageProcessingControllerImpl(this.model, this.view, this.rd);
    this.controller.process();
    Pixel p1 = new Pixel(255, 255, 255);
    Pixel p2 = new Pixel(0, 0, 0);
    Pixel p3 = new Pixel(0, 0, 0);
    Pixel p4 = new Pixel(255, 255, 255);
    Pixel[][] pixelsTemp = new Pixel[2][2];
    pixelsTemp[0][0] = p1;
    pixelsTemp[0][1] = p2;
    pixelsTemp[1][0] = p3;
    pixelsTemp[1][1] = p4;
    Image temp = this.model.getImage("test-red");
    assertEquals(temp, new ImageImpl(2,2, pixelsTemp));
    assertEquals("DIRECTIONS for the image processor:\n" +
            "    - Quit the program:                  q or quit. Anywhere in your input\n" +
            "    - Load an image:                     load imagePath imageName\n" +
            "    - Flip the image vertically:         vertical-flip imageName newImageName\n" +
            "    - Flip the image horizontally:       horizontal-flip imageName newImageName\n" +
            "    - Brighten the image:                brighten positive-number imageName "
            + "newImageName\n" +
            "    - Darken the image:                  brighten negative-number imageName "
            + "newImageName\n" +
            "    - Red-Component Greyscale:           red-component imageName newImageName\n" +
            "    - Green-Component Greyscale:         green-component imageName newImageName\n" +
            "    - Blue-Component Greyscale:          blue-component imageName newImageName\n" +
            "    - Value-Component Greyscale:         value-component imageName newImageName\n" +
            "    - Intensity-Component Greyscale:     intensity-component imageName "
            + "newImageName\n" +
            "    - Luma-Component Greyscale:          luma-component imageName newImageName\n" +
            "    - Blur the image:                    blur imageName newImageName\n" +
            "    - Sharpen the image:                 sharpen imageName newImageName\n" +
            "    - ColorTransformation(GreyScale):    greyscale imageName newImageName\n" +
            "    - ColorTransformation(sepia):        sepia imageName newImageName\n" +
            "Enter:\n" +
            "ImageImpl loading... \n" +
            "ImageImpl loaded!\n" +
            "red-component processed!\n", this.ap.toString());
  }

  @Test
  public void testProcessGreen() {
    this.rd = new StringReader("load res/test/two.ppm test\ngreen-component test test-green");
    this.controller = new ImageProcessingControllerImpl(this.model, this.view, this.rd);
    this.controller.process();
    Pixel p1 = new Pixel(0, 0, 0);
    Pixel p2 = new Pixel(255, 255, 255);
    Pixel p3 = new Pixel(0, 0, 0);
    Pixel p4 = new Pixel(0, 0, 0);
    Pixel[][] pixelsTemp = new Pixel[2][2];
    pixelsTemp[0][0] = p1;
    pixelsTemp[0][1] = p2;
    pixelsTemp[1][0] = p3;
    pixelsTemp[1][1] = p4;
    Image temp = this.model.getImage("test-green");
    assertEquals(temp, new ImageImpl(2,2, pixelsTemp));
    assertEquals("DIRECTIONS for the image processor:\n" +
            "    - Quit the program:                  q or quit. Anywhere in your input\n" +
            "    - Load an image:                     load imagePath imageName\n" +
            "    - Flip the image vertically:         vertical-flip imageName newImageName\n" +
            "    - Flip the image horizontally:       horizontal-flip imageName newImageName\n" +
            "    - Brighten the image:                brighten positive-number imageName "
            + "newImageName\n" +
            "    - Darken the image:                  brighten negative-number imageName "
            + "newImageName\n" +
            "    - Red-Component Greyscale:           red-component imageName newImageName\n" +
            "    - Green-Component Greyscale:         green-component imageName newImageName\n" +
            "    - Blue-Component Greyscale:          blue-component imageName newImageName\n" +
            "    - Value-Component Greyscale:         value-component imageName newImageName\n" +
            "    - Intensity-Component Greyscale:     intensity-component imageName "
            + "newImageName\n" +
            "    - Luma-Component Greyscale:          luma-component imageName newImageName\n" +
            "    - Blur the image:                    blur imageName newImageName\n" +
            "    - Sharpen the image:                 sharpen imageName newImageName\n" +
            "    - ColorTransformation(GreyScale):    greyscale imageName newImageName\n" +
            "    - ColorTransformation(sepia):        sepia imageName newImageName\n" +
            "Enter:\n" +
            "ImageImpl loading... \n" +
            "ImageImpl loaded!\n" +
            "green-component processed!\n", this.ap.toString());
  }

  @Test
  public void testProcessBlue() {
    this.rd = new StringReader("load res/test/two.ppm test\nblue-component test test-blue");
    this.controller = new ImageProcessingControllerImpl(this.model, this.view, this.rd);
    this.controller.process();
    Pixel p1 = new Pixel(0, 0, 0);
    Pixel p2 = new Pixel(0, 0, 0);
    Pixel p3 = new Pixel(0, 0, 0);
    Pixel p4 = new Pixel(0, 0, 0);
    Pixel[][] pixelsTemp = new Pixel[2][2];
    pixelsTemp[0][0] = p1;
    pixelsTemp[0][1] = p2;
    pixelsTemp[1][0] = p3;
    pixelsTemp[1][1] = p4;
    Image temp = this.model.getImage("test-blue");
    assertEquals(temp, new ImageImpl(2,2, pixelsTemp));
    assertEquals("DIRECTIONS for the image processor:\n" +
            "    - Quit the program:                  q or quit. Anywhere in your input\n" +
            "    - Load an image:                     load imagePath imageName\n" +
            "    - Flip the image vertically:         vertical-flip imageName newImageName\n" +
            "    - Flip the image horizontally:       horizontal-flip imageName newImageName\n" +
            "    - Brighten the image:                brighten positive-number imageName "
            + "newImageName\n" +
            "    - Darken the image:                  brighten negative-number imageName "
            + "newImageName\n" +
            "    - Red-Component Greyscale:           red-component imageName newImageName\n" +
            "    - Green-Component Greyscale:         green-component imageName newImageName\n" +
            "    - Blue-Component Greyscale:          blue-component imageName newImageName\n" +
            "    - Value-Component Greyscale:         value-component imageName newImageName\n" +
            "    - Intensity-Component Greyscale:     intensity-component imageName "
            + "newImageName\n" +
            "    - Luma-Component Greyscale:          luma-component imageName newImageName\n" +
            "    - Blur the image:                    blur imageName newImageName\n" +
            "    - Sharpen the image:                 sharpen imageName newImageName\n" +
            "    - ColorTransformation(GreyScale):    greyscale imageName newImageName\n" +
            "    - ColorTransformation(sepia):        sepia imageName newImageName\n" +
            "Enter:\n" +
            "ImageImpl loading... \n" +
            "ImageImpl loaded!\n" +
            "blue-component processed!\n", this.ap.toString());
  }

  @Test
  public void testProcessValue() {
    this.rd = new StringReader("load res/test/two.ppm test\nvalue-component test test-value");
    this.controller = new ImageProcessingControllerImpl(this.model, this.view, this.rd);
    this.controller.process();
    Pixel p1 = new Pixel(255, 255, 255);
    Pixel p2 = new Pixel(255, 255, 255);
    Pixel p3 = new Pixel(0, 0, 0);
    Pixel p4 = new Pixel(255, 255, 255);
    Pixel[][] pixelsTemp = new Pixel[2][2];
    pixelsTemp[0][0] = p1;
    pixelsTemp[0][1] = p2;
    pixelsTemp[1][0] = p3;
    pixelsTemp[1][1] = p4;
    Image temp = this.model.getImage("test-value");
    assertEquals(temp, new ImageImpl(2,2, pixelsTemp));
    assertEquals("DIRECTIONS for the image processor:\n" +
            "    - Quit the program:                  q or quit. Anywhere in your input\n" +
            "    - Load an image:                     load imagePath imageName\n" +
            "    - Flip the image vertically:         vertical-flip imageName newImageName\n" +
            "    - Flip the image horizontally:       horizontal-flip imageName newImageName\n" +
            "    - Brighten the image:                brighten positive-number imageName "
            + "newImageName\n" +
            "    - Darken the image:                  brighten negative-number imageName "
            + "newImageName\n" +
            "    - Red-Component Greyscale:           red-component imageName newImageName\n" +
            "    - Green-Component Greyscale:         green-component imageName newImageName\n" +
            "    - Blue-Component Greyscale:          blue-component imageName newImageName\n" +
            "    - Value-Component Greyscale:         value-component imageName newImageName\n" +
            "    - Intensity-Component Greyscale:     intensity-component imageName "
            + "newImageName\n" +
            "    - Luma-Component Greyscale:          luma-component imageName newImageName\n" +
            "    - Blur the image:                    blur imageName newImageName\n" +
            "    - Sharpen the image:                 sharpen imageName newImageName\n" +
            "    - ColorTransformation(GreyScale):    greyscale imageName newImageName\n" +
            "    - ColorTransformation(sepia):        sepia imageName newImageName\n" +
            "Enter:\n" +
            "ImageImpl loading... \n" +
            "ImageImpl loaded!\n" +
            "value-component processed!\n", this.ap.toString());
  }

  @Test
  public void testProcessIntensity() {
    this.rd = new StringReader("load res/test/two.ppm test\nintensity-component "
            + "test test-intensity");
    this.controller = new ImageProcessingControllerImpl(this.model, this.view, this.rd);
    this.controller.process();
    Pixel p1 = new Pixel(85, 85, 85);
    Pixel p2 = new Pixel(85, 85, 85);
    Pixel p3 = new Pixel(0, 0, 0);
    Pixel p4 = new Pixel(85, 85, 85);
    Pixel[][] pixelsTemp = new Pixel[2][2];
    pixelsTemp[0][0] = p1;
    pixelsTemp[0][1] = p2;
    pixelsTemp[1][0] = p3;
    pixelsTemp[1][1] = p4;
    Image temp = this.model.getImage("test-intensity");
    assertEquals(temp, new ImageImpl(2,2, pixelsTemp));
    assertEquals("DIRECTIONS for the image processor:\n" +
            "    - Quit the program:                  q or quit. Anywhere in your input\n" +
            "    - Load an image:                     load imagePath imageName\n" +
            "    - Flip the image vertically:         vertical-flip imageName newImageName\n" +
            "    - Flip the image horizontally:       horizontal-flip imageName newImageName\n" +
            "    - Brighten the image:                brighten positive-number imageName "
            + "newImageName\n" +
            "    - Darken the image:                  brighten negative-number imageName "
            + "newImageName\n" +
            "    - Red-Component Greyscale:           red-component imageName newImageName\n" +
            "    - Green-Component Greyscale:         green-component imageName newImageName\n" +
            "    - Blue-Component Greyscale:          blue-component imageName newImageName\n" +
            "    - Value-Component Greyscale:         value-component imageName newImageName\n" +
            "    - Intensity-Component Greyscale:     intensity-component imageName "
            + "newImageName\n" +
            "    - Luma-Component Greyscale:          luma-component imageName newImageName\n" +
            "    - Blur the image:                    blur imageName newImageName\n" +
            "    - Sharpen the image:                 sharpen imageName newImageName\n" +
            "    - ColorTransformation(GreyScale):    greyscale imageName newImageName\n" +
            "    - ColorTransformation(sepia):        sepia imageName newImageName\n" +
            "Enter:\n" +
            "ImageImpl loading... \n" +
            "ImageImpl loaded!\n" +
            "intensity-component processed!\n", this.ap.toString());
  }

  @Test
  public void testProcessLuma() {
    this.rd = new StringReader("load res/test/two.ppm test\nluma-component test test-luma");
    this.controller = new ImageProcessingControllerImpl(this.model, this.view, this.rd);
    this.controller.process();
    Pixel p1 = new Pixel(54, 54, 54);
    Pixel p2 = new Pixel(182, 182, 182);
    Pixel p3 = new Pixel(0, 0, 0);
    Pixel p4 = new Pixel(54, 54, 54);
    Pixel[][] pixelsTemp = new Pixel[2][2];
    pixelsTemp[0][0] = p1;
    pixelsTemp[0][1] = p2;
    pixelsTemp[1][0] = p3;
    pixelsTemp[1][1] = p4;
    Image temp = this.model.getImage("test-luma");
    assertEquals(temp, new ImageImpl(2,2, pixelsTemp));
    assertEquals("DIRECTIONS for the image processor:\n" +
            "    - Quit the program:                  q or quit. Anywhere in your input\n" +
            "    - Load an image:                     load imagePath imageName\n" +
            "    - Flip the image vertically:         vertical-flip imageName newImageName\n" +
            "    - Flip the image horizontally:       horizontal-flip imageName newImageName\n" +
            "    - Brighten the image:                brighten positive-number imageName "
            + "newImageName\n" +
            "    - Darken the image:                  brighten negative-number imageName "
            + "newImageName\n" +
            "    - Red-Component Greyscale:           red-component imageName newImageName\n" +
            "    - Green-Component Greyscale:         green-component imageName newImageName\n" +
            "    - Blue-Component Greyscale:          blue-component imageName newImageName\n" +
            "    - Value-Component Greyscale:         value-component imageName newImageName\n" +
            "    - Intensity-Component Greyscale:     intensity-component imageName "
            + "newImageName\n" +
            "    - Luma-Component Greyscale:          luma-component imageName newImageName\n" +
            "    - Blur the image:                    blur imageName newImageName\n" +
            "    - Sharpen the image:                 sharpen imageName newImageName\n" +
            "    - ColorTransformation(GreyScale):    greyscale imageName newImageName\n" +
            "    - ColorTransformation(sepia):        sepia imageName newImageName\n" +
            "Enter:\n" +
            "ImageImpl loading... \n" +
            "ImageImpl loaded!\n" +
            "luma-component processed!\n", this.ap.toString());
  }

  @Test
  public void testProcessBrightenRed() {
    this.rd = new StringReader("load res/test/two.ppm test\nbrighten 50 test"
            + " test-brighter\nred-component test-brighter test-brighter-red");
    this.controller = new ImageProcessingControllerImpl(this.model, this.view, this.rd);
    this.controller.process();
    Pixel p1 = new Pixel(255, 255, 255);
    Pixel p2 = new Pixel(50, 50, 50);
    Pixel p3 = new Pixel(50, 50, 50);
    Pixel p4 = new Pixel(255, 255, 255);
    Pixel[][] pixelsTemp = new Pixel[2][2];
    pixelsTemp[0][0] = p1;
    pixelsTemp[0][1] = p2;
    pixelsTemp[1][0] = p3;
    pixelsTemp[1][1] = p4;
    Image temp = this.model.getImage("test-brighter-red");
    assertEquals(temp, new ImageImpl(2,2, pixelsTemp));
    assertEquals("DIRECTIONS for the image processor:\n" +
            "    - Quit the program:                  q or quit. Anywhere in your input\n" +
            "    - Load an image:                     load imagePath imageName\n" +
            "    - Flip the image vertically:         vertical-flip imageName newImageName\n" +
            "    - Flip the image horizontally:       horizontal-flip imageName newImageName\n" +
            "    - Brighten the image:                brighten positive-number imageName "
            + "newImageName\n" +
            "    - Darken the image:                  brighten negative-number imageName "
            + "newImageName\n" +
            "    - Red-Component Greyscale:           red-component imageName newImageName\n" +
            "    - Green-Component Greyscale:         green-component imageName newImageName\n" +
            "    - Blue-Component Greyscale:          blue-component imageName newImageName\n" +
            "    - Value-Component Greyscale:         value-component imageName newImageName\n" +
            "    - Intensity-Component Greyscale:     intensity-component imageName "
            + "newImageName\n" +
            "    - Luma-Component Greyscale:          luma-component imageName newImageName\n" +
            "    - Blur the image:                    blur imageName newImageName\n" +
            "    - Sharpen the image:                 sharpen imageName newImageName\n" +
            "    - ColorTransformation(GreyScale):    greyscale imageName newImageName\n" +
            "    - ColorTransformation(sepia):        sepia imageName newImageName\n" +
            "Enter:\n" +
            "ImageImpl loading... \n" +
            "ImageImpl loaded!\n" +
            "brighten processed!\n" +
            "red-component processed!\n", this.ap.toString());
  }

  @Test
  public void testProcessSave() {
    this.rd = new StringReader("load res/test/two.ppm test\nsave"
            + " res/test/two2.ppm test\nload res/test/two2.ppm test2");
    this.controller = new ImageProcessingControllerImpl(this.model, this.view, this.rd);
    this.controller.process();
    assertEquals("DIRECTIONS for the image processor:\n" +
            "    - Quit the program:                  q or quit. Anywhere in your input\n" +
            "    - Load an image:                     load imagePath imageName\n" +
            "    - Flip the image vertically:         vertical-flip imageName newImageName\n" +
            "    - Flip the image horizontally:       horizontal-flip imageName newImageName\n" +
            "    - Brighten the image:                brighten positive-number imageName "
            + "newImageName\n" +
            "    - Darken the image:                  brighten negative-number imageName "
            + "newImageName\n" +
            "    - Red-Component Greyscale:           red-component imageName newImageName\n" +
            "    - Green-Component Greyscale:         green-component imageName newImageName\n" +
            "    - Blue-Component Greyscale:          blue-component imageName newImageName\n" +
            "    - Value-Component Greyscale:         value-component imageName newImageName\n" +
            "    - Intensity-Component Greyscale:     intensity-component imageName "
            + "newImageName\n" +
            "    - Luma-Component Greyscale:          luma-component imageName newImageName\n" +
            "    - Blur the image:                    blur imageName newImageName\n" +
            "    - Sharpen the image:                 sharpen imageName newImageName\n" +
            "    - ColorTransformation(GreyScale):    greyscale imageName newImageName\n" +
            "    - ColorTransformation(sepia):        sepia imageName newImageName\n" +
            "Enter:\n" +
            "ImageImpl loading... \n" +
            "ImageImpl loaded!\n" +
            "ImageImpl saving... \n" +
            "ImageImpl saved\n" +
            "ImageImpl loading... \n" +
            "ImageImpl loaded!\n", this.ap.toString());
    Image image1 = this.model.getImage("test2");
    Image image2 = this.model.getImage("test");
    ImageView view1 = new ImageViewImpl(image1);
    ImageView view2 = new ImageViewImpl(image2);
    assertEquals(image1, image2);
    assertEquals(view1.toString(), view2.toString());
  }

  @Test
  public void testProcessSaveWithOneMoreOperation() {
    this.rd = new StringReader("load res/test/two.ppm test\nsave"
            + " res/test/two2.ppm test\nload res/test/two2.ppm test2\nblur test2 test2-blur\n"
            + "blur test test-blur");
    this.controller = new ImageProcessingControllerImpl(this.model, this.view, this.rd);
    this.controller.process();
    assertEquals("DIRECTIONS for the image processor:\n" +
            "    - Quit the program:                  q or quit. Anywhere in your input\n" +
            "    - Load an image:                     load imagePath imageName\n" +
            "    - Flip the image vertically:         vertical-flip imageName newImageName\n" +
            "    - Flip the image horizontally:       horizontal-flip imageName newImageName\n" +
            "    - Brighten the image:                brighten positive-number imageName "
            + "newImageName\n" +
            "    - Darken the image:                  brighten negative-number imageName "
            + "newImageName\n" +
            "    - Red-Component Greyscale:           red-component imageName newImageName\n" +
            "    - Green-Component Greyscale:         green-component imageName newImageName\n" +
            "    - Blue-Component Greyscale:          blue-component imageName newImageName\n" +
            "    - Value-Component Greyscale:         value-component imageName newImageName\n" +
            "    - Intensity-Component Greyscale:     intensity-component imageName "
            + "newImageName\n" +
            "    - Luma-Component Greyscale:          luma-component imageName newImageName\n" +
            "    - Blur the image:                    blur imageName newImageName\n" +
            "    - Sharpen the image:                 sharpen imageName newImageName\n" +
            "    - ColorTransformation(GreyScale):    greyscale imageName newImageName\n" +
            "    - ColorTransformation(sepia):        sepia imageName newImageName\n" +
            "Enter:\n" +
            "ImageImpl loading... \n" +
            "ImageImpl loaded!\n" +
            "ImageImpl saving... \n" +
            "ImageImpl saved\n" +
            "ImageImpl loading... \n" +
            "ImageImpl loaded!\n" +
            "blur processed!\n" +
            "blur processed!\n", this.ap.toString());
    Image image1 = this.model.getImage("test2-blur");
    Image image2 = this.model.getImage("test-blur");
    ImageView view1 = new ImageViewImpl(image1);
    ImageView view2 = new ImageViewImpl(image2);
    assertEquals(image1, image2);
    assertEquals(view1.toString(), view2.toString());
  }

  @Test
  public void testMakeErrorCommand() {
    this.rd = new StringReader("load res/test/two.ppm test\nreeed-component test test-red");
    this.controller = new ImageProcessingControllerImpl(this.model, this.view, this.rd);
    this.controller.process();
    assertEquals("DIRECTIONS for the image processor:\n" +
            "    - Quit the program:                  q or quit. Anywhere in your input\n" +
            "    - Load an image:                     load imagePath imageName\n" +
            "    - Flip the image vertically:         vertical-flip imageName newImageName\n" +
            "    - Flip the image horizontally:       horizontal-flip imageName newImageName\n" +
            "    - Brighten the image:                brighten positive-number imageName "
            + "newImageName\n" +
            "    - Darken the image:                  brighten negative-number imageName "
            + "newImageName\n" +
            "    - Red-Component Greyscale:           red-component imageName newImageName\n" +
            "    - Green-Component Greyscale:         green-component imageName newImageName\n" +
            "    - Blue-Component Greyscale:          blue-component imageName newImageName\n" +
            "    - Value-Component Greyscale:         value-component imageName newImageName\n" +
            "    - Intensity-Component Greyscale:     intensity-component imageName "
            + "newImageName\n" +
            "    - Luma-Component Greyscale:          luma-component imageName newImageName\n" +
            "    - Blur the image:                    blur imageName newImageName\n" +
            "    - Sharpen the image:                 sharpen imageName newImageName\n" +
            "    - ColorTransformation(GreyScale):    greyscale imageName newImageName\n" +
            "    - ColorTransformation(sepia):        sepia imageName newImageName\n" +
            "Enter:\n" +
            "ImageImpl loading... \n" +
            "ImageImpl loaded!\n" +
            "the command does not exist. Try again.\n", this.ap.toString());
  }

  @Test
  public void testProcessBlur() {
    this.rd = new StringReader("load res/test/two.ppm test\nblur test test-blur");
    this.controller = new ImageProcessingControllerImpl(this.model, this.view, this.rd);
    this.controller.process();
    Pixel p1 = new Pixel(79, 31, 0);
    Pixel p2 = new Pixel(41, 67, 0);
    Pixel p3 = new Pixel(44, 8, 0);
    Pixel p4 = new Pixel(79, 11, 0);
    Pixel[][] pixelsTemp = new Pixel[2][2];
    pixelsTemp[0][0] = p2;
    pixelsTemp[0][1] = p1;
    pixelsTemp[1][0] = p4;
    pixelsTemp[1][1] = p3;
    Image temp = this.model.getImage("test-blur");
    assertEquals(temp, new ImageImpl(2,2, pixelsTemp));
    assertEquals("DIRECTIONS for the image processor:\n" +
            "    - Quit the program:                  q or quit. Anywhere in your input\n" +
            "    - Load an image:                     load imagePath imageName\n" +
            "    - Flip the image vertically:         vertical-flip imageName newImageName\n" +
            "    - Flip the image horizontally:       horizontal-flip imageName newImageName\n" +
            "    - Brighten the image:                brighten positive-number imageName "
            + "newImageName\n" +
            "    - Darken the image:                  brighten negative-number imageName "
            + "newImageName\n" +
            "    - Red-Component Greyscale:           red-component imageName newImageName\n" +
            "    - Green-Component Greyscale:         green-component imageName newImageName\n" +
            "    - Blue-Component Greyscale:          blue-component imageName newImageName\n" +
            "    - Value-Component Greyscale:         value-component imageName newImageName\n" +
            "    - Intensity-Component Greyscale:     intensity-component imageName "
            + "newImageName\n" +
            "    - Luma-Component Greyscale:          luma-component imageName newImageName\n" +
            "    - Blur the image:                    blur imageName newImageName\n" +
            "    - Sharpen the image:                 sharpen imageName newImageName\n" +
            "    - ColorTransformation(GreyScale):    greyscale imageName newImageName\n" +
            "    - ColorTransformation(sepia):        sepia imageName newImageName\n" +
            "Enter:\n" +
            "ImageImpl loading... \n" +
            "ImageImpl loaded!\n" +
            "blur processed!\n", this.ap.toString());
  }

  @Test
  public void testProcessSharpen() {
    this.rd = new StringReader("load res/test/two.ppm test\nsharpen test test-sharpen");
    this.controller = new ImageProcessingControllerImpl(this.model, this.view, this.rd);
    this.controller.process();
    Pixel p1 = new Pixel(255, 63, 0);
    Pixel p2 = new Pixel(127, 255, 0);
    Pixel p3 = new Pixel(159, 79, 0);
    Pixel p4 = new Pixel(255, 99, 0);
    Pixel[][] pixelsTemp = new Pixel[2][2];
    pixelsTemp[0][0] = p2;
    pixelsTemp[0][1] = p1;
    pixelsTemp[1][0] = p4;
    pixelsTemp[1][1] = p3;
    Image temp = this.model.getImage("test-sharpen");
    assertEquals(temp, new ImageImpl(2,2, pixelsTemp));
    assertEquals("DIRECTIONS for the image processor:\n" +
            "    - Quit the program:                  q or quit. Anywhere in your input\n" +
            "    - Load an image:                     load imagePath imageName\n" +
            "    - Flip the image vertically:         vertical-flip imageName newImageName\n" +
            "    - Flip the image horizontally:       horizontal-flip imageName newImageName\n" +
            "    - Brighten the image:                brighten positive-number imageName "
            + "newImageName\n" +
            "    - Darken the image:                  brighten negative-number imageName "
            + "newImageName\n" +
            "    - Red-Component Greyscale:           red-component imageName newImageName\n" +
            "    - Green-Component Greyscale:         green-component imageName newImageName\n" +
            "    - Blue-Component Greyscale:          blue-component imageName newImageName\n" +
            "    - Value-Component Greyscale:         value-component imageName newImageName\n" +
            "    - Intensity-Component Greyscale:     intensity-component imageName "
            + "newImageName\n" +
            "    - Luma-Component Greyscale:          luma-component imageName newImageName\n" +
            "    - Blur the image:                    blur imageName newImageName\n" +
            "    - Sharpen the image:                 sharpen imageName newImageName\n" +
            "    - ColorTransformation(GreyScale):    greyscale imageName newImageName\n" +
            "    - ColorTransformation(sepia):        sepia imageName newImageName\n" +
            "Enter:\n" +
            "ImageImpl loading... \n" +
            "ImageImpl loaded!\n" +
            "sharpen processed!\n", this.ap.toString());
  }

  @Test
  public void testProcessGreyScaleColorTransformation() {
    this.rd = new StringReader("load res/test/two.ppm test\ngreyscale test test-greyscale");
    this.controller = new ImageProcessingControllerImpl(this.model, this.view, this.rd);
    this.controller.process();
    Pixel p1 = new Pixel(54, 54, 54);
    Pixel p2 = new Pixel(182, 182, 182);
    Pixel p3 = new Pixel(0, 0, 0);
    Pixel p4 = new Pixel(54, 54, 54);
    Pixel[][] pixelsTemp = new Pixel[2][2];
    pixelsTemp[0][0] = p2;
    pixelsTemp[0][1] = p1;
    pixelsTemp[1][0] = p4;
    pixelsTemp[1][1] = p3;
    Image temp = this.model.getImage("test-greyscale");
    assertEquals(temp, new ImageImpl(2,2, pixelsTemp));
    assertEquals("DIRECTIONS for the image processor:\n" +
            "    - Quit the program:                  q or quit. Anywhere in your input\n" +
            "    - Load an image:                     load imagePath imageName\n" +
            "    - Flip the image vertically:         vertical-flip imageName newImageName\n" +
            "    - Flip the image horizontally:       horizontal-flip imageName newImageName\n" +
            "    - Brighten the image:                brighten positive-number imageName "
            + "newImageName\n" +
            "    - Darken the image:                  brighten negative-number imageName "
            + "newImageName\n" +
            "    - Red-Component Greyscale:           red-component imageName newImageName\n" +
            "    - Green-Component Greyscale:         green-component imageName newImageName\n" +
            "    - Blue-Component Greyscale:          blue-component imageName newImageName\n" +
            "    - Value-Component Greyscale:         value-component imageName newImageName\n" +
            "    - Intensity-Component Greyscale:     intensity-component imageName "
            + "newImageName\n" +
            "    - Luma-Component Greyscale:          luma-component imageName newImageName\n" +
            "    - Blur the image:                    blur imageName newImageName\n" +
            "    - Sharpen the image:                 sharpen imageName newImageName\n" +
            "    - ColorTransformation(GreyScale):    greyscale imageName newImageName\n" +
            "    - ColorTransformation(sepia):        sepia imageName newImageName\n" +
            "Enter:\n" +
            "ImageImpl loading... \n" +
            "ImageImpl loaded!\n" +
            "greyscale processed!\n", this.ap.toString());
  }

  @Test
  public void testProcessSepiaColorTransformation() {
    this.rd = new StringReader("load res/test/two.ppm test\nsepia test test-sepia");
    this.controller = new ImageProcessingControllerImpl(this.model, this.view, this.rd);
    this.controller.process();
    Pixel p1 = new Pixel(100, 88, 69);
    Pixel p2 = new Pixel(196, 174, 136);
    Pixel p3 = new Pixel(0, 0, 0);
    Pixel p4 = new Pixel(100, 88, 69);
    Pixel[][] pixelsTemp = new Pixel[2][2];
    pixelsTemp[0][0] = p2;
    pixelsTemp[0][1] = p1;
    pixelsTemp[1][0] = p4;
    pixelsTemp[1][1] = p3;
    Image temp = this.model.getImage("test-sepia");
    assertEquals(temp, new ImageImpl(2,2, pixelsTemp));
    assertEquals("DIRECTIONS for the image processor:\n" +
            "    - Quit the program:                  q or quit. Anywhere in your input\n" +
            "    - Load an image:                     load imagePath imageName\n" +
            "    - Flip the image vertically:         vertical-flip imageName newImageName\n" +
            "    - Flip the image horizontally:       horizontal-flip imageName newImageName\n" +
            "    - Brighten the image:                brighten positive-number imageName "
            + "newImageName\n" +
            "    - Darken the image:                  brighten negative-number imageName "
            + "newImageName\n" +
            "    - Red-Component Greyscale:           red-component imageName newImageName\n" +
            "    - Green-Component Greyscale:         green-component imageName newImageName\n" +
            "    - Blue-Component Greyscale:          blue-component imageName newImageName\n" +
            "    - Value-Component Greyscale:         value-component imageName newImageName\n" +
            "    - Intensity-Component Greyscale:     intensity-component imageName "
            + "newImageName\n" +
            "    - Luma-Component Greyscale:          luma-component imageName newImageName\n" +
            "    - Blur the image:                    blur imageName newImageName\n" +
            "    - Sharpen the image:                 sharpen imageName newImageName\n" +
            "    - ColorTransformation(GreyScale):    greyscale imageName newImageName\n" +
            "    - ColorTransformation(sepia):        sepia imageName newImageName\n" +
            "Enter:\n" +
            "ImageImpl loading... \n" +
            "ImageImpl loaded!\n" +
            "sepia processed!\n", this.ap.toString());
  }
}