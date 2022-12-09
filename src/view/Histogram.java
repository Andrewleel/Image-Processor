package view;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;

/**
 * class that draws histogram as Panel.
 */
public class Histogram extends JPanel {

  private List<int[]> list;
  private Dimension dimension;

  /**
   * the constructor for the histogram panel.
   * @param list list of frequencies
   * @param dimension dimension of image
   */
  public Histogram(List<int[]> list, Dimension dimension) {
    this.list = list;
    this.dimension = dimension;
  }

  @Override
  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    int height = this.dimension.height;
    int width = this.dimension.width;
    Graphics g = graphics.create();
    Color[] colorList = new Color[]{new Color(255,0,0,80),
        new Color(0,255,0,80), new Color(0,0,255,80),
        new Color(0,0,0,80)};// horizontal
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 256; j++) {
        g.setColor(colorList[i]);
        if ((j + 1) < 256) {
          g.drawLine(j * (500 / 255), list.get(i)[j],
                  (j + 1) * (500 / 255), list.get(i)[j + 1]);
        }
      }
    }
  }
}
