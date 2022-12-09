package view;


import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;

import controller.ImageProcessingGUIController;
import model.Image;
import model.Pixel;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * the main GUI view class that builds GUI for the image Processing program.
 */
public class GUIView extends JFrame implements IGUIView {
  JPanel main;
  JPanel histogramPanel;
  DefaultListModel<String> imagesList;
  JLabel imagePanel;
  JLabel imageSelectingPanel;
  JScrollPane image;
  JScrollPane histogram;
  JPanel selectionPanel;
  JPanel commandPanel;
  JPanel filePanel;
  JButton loadButton;
  JButton saveButton;
  JButton submitButton;
  JList<String> commandListForPanel;
  JList<String> imageListForPanel;

  /**
   *  the constructor for our GUIView class for our Graphical Interface.
   */
  public GUIView() {
    super();
    this.setTitle("Image Processor");
    this.setPreferredSize(new Dimension(900, 900));
    this.setResizable(false);
    this.setLayout(new BorderLayout());
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.placeAllPanels();
    this.getContentPane().add(this.main, BorderLayout.CENTER);
    this.pack();
    this.setVisible(true);
  }

  private void placeAllPanels() {

    // main Panel
    this.main = new JPanel();
    this.main.setLayout(new BorderLayout());

    JPanel top = new JPanel();
    top.setLayout(new BorderLayout());

    JLabel importExport = new JLabel("Import and export images", SwingConstants.CENTER);
    importExport.setFont(new Font("Serif", Font.BOLD, 15));
    top.add(importExport, BorderLayout.NORTH);

    this.loadSavePanels();
    top.add(this.filePanel, BorderLayout.CENTER);

    this.main.add(top, BorderLayout.NORTH);

    JPanel imageSection = new JPanel();
    imageSection.setLayout(new BorderLayout());

    this.imagePanels();
    imageSection.add(this.image, BorderLayout.CENTER);
    // add imagePanel to main
    this.main.add(imageSection, BorderLayout.CENTER);

    JPanel bottom = new JPanel();
    bottom.setLayout(new BorderLayout());

    JPanel interactive = new JPanel();
    interactive.setLayout(new BorderLayout());

    JLabel label = new JLabel("Choose an image and modify it"
            , SwingConstants.CENTER);
    label.setFont(new Font("Serif", Font.BOLD, 15));
    interactive.add(label, BorderLayout.NORTH);

    this.selectionPanel = new JPanel();
    this.commandSelectingPanel();
    this.imageSelectingPanel();
    interactive.add(this.selectionPanel, BorderLayout.CENTER);

    this.submitButton();
    interactive.add(this.submitButton, BorderLayout.EAST);
    bottom.add(interactive, BorderLayout.CENTER);

    this.manageHistogram();
    bottom.add(this.histogram, BorderLayout.EAST);

    this.main.add(bottom, BorderLayout.PAGE_END);
  }

  private void manageHistogram() {
    this.histogramPanel = new JPanel();
    this.histogram = new JScrollPane(this.histogramPanel);
    this.histogramPanel.setPreferredSize(new Dimension(400, 250));
    this.histogramPanel.setLayout(new BorderLayout());
    JLabel title = new JLabel("Histogram",
            SwingConstants.CENTER);
    this.histogramPanel.add(title, BorderLayout.NORTH);
  }

  /**
   * the method that set actions on buttons so that the functionalities can be operated through the
   *                  parameter controller.
   * @param controller GUI controller for processing functionalities.
   */
  public void actions(ImageProcessingGUIController controller) {
    loadButton.addActionListener(evt -> {
      try {
        controller.load();
      } catch (IOException e) {
        try {
          this.render("Image not loaded");
        } catch (IOException ex) {
          throw new IllegalArgumentException("Message not rendering");
        }
      }
    });
    saveButton.addActionListener(evt -> {
      try {
        controller.save(imageListForPanel.getSelectedValue());
      } catch (IOException e) {
        throw new IllegalArgumentException("Message not rendering");
      }
    });
    submitButton.addActionListener(evt -> {
      try {
        controller.modify(commandListForPanel.getSelectedValue(),
                imageListForPanel.getSelectedValue());
      } catch (IOException e) {
        throw new IllegalArgumentException("Message not rendering");
      }
    });
  }

  private void loadSavePanels() {
    this.filePanel = new JPanel();
    this.loadButton = new JButton("Load Image");
    this.saveButton = new JButton("Save Image");
    this.loadButton.setActionCommand("Load Image");
    this.saveButton.setActionCommand("Save Image");
    this.filePanel.add(this.loadButton);
    this.filePanel.add(this.saveButton);
  }

  private void submitButton() {
    this.submitButton = new JButton("Submit");
    this.submitButton.setPreferredSize(new Dimension(100, 100));
    this.submitButton.setActionCommand("Submit");
  }

  private void imagePanels() {
    this.imagePanel = new JLabel();
    this.image = new JScrollPane(this.imagePanel);
    this.imagePanel.setPreferredSize(new Dimension(900, 450));
    this.imagePanel.setHorizontalAlignment(JLabel.CENTER);
  }


  @Override
  public void render(String message) throws IOException {
    JOptionPane.showMessageDialog(null, message);
  }

  private void commandSelectingPanel() {
    List<String> commands = new ArrayList<>();
    commands.add("red-component");
    commands.add("green-component");
    commands.add("blue-component");
    commands.add("value-component");
    commands.add("intensity-component");
    commands.add("luma-component");
    commands.add("vertical-flip");
    commands.add("horizontal-flip");
    commands.add("brighten");
    commands.add("sepia");
    commands.add("greyscale");
    commands.add("sharpen");
    commands.add("blur");

    commandPanel = new JPanel();
    commandPanel.setPreferredSize(new Dimension(150, 200));
    DefaultListModel<String> commandsList = new DefaultListModel<>();
    for (String command: commands) {
      commandsList.addElement(command);
    }
    this.commandListForPanel = new JList<>(commandsList);
    this.commandListForPanel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    this.commandPanel.setLayout(new BorderLayout());
    this.commandPanel.add(new JScrollPane(this.commandListForPanel), BorderLayout.CENTER);
    this.selectionPanel.add(commandPanel);
  }

  private void updateHistogram(List<int[]> list, Dimension dimension) {
    JPanel histogram = new Histogram(list, dimension);
    this.histogramPanel.removeAll();
    JLabel title = new JLabel("Histogram"
            , SwingConstants.CENTER);
    this.histogramPanel.add(title, BorderLayout.NORTH);
    this.histogramPanel.add(histogram, BorderLayout.CENTER);
  }

  /**
   * the method that adds image to the view Image list and updating histogram.
   * @param imageName the image name the image is saved as in the model.
   * @param image the image
   * @param list the frequency representations of the pixels of the image.
   */
  @Override
  public void addImage(String imageName, Image image, List<int[]> list, Dimension dimension) {
    this.imagesList.addElement(imageName);
    this.updateImage(image, imageName, list, dimension);
    this.updateHistogram(list, dimension);
    SwingUtilities.updateComponentTreeUI(this);
  }

  /**
   * the method that updates the image to the image view panel and updating
   *                    the histogram correspondingly.
   * @param image the image
   * @param imageName the image name the image is saved as in the model.
   * @param list the frequency representations of the pixels of the image.
   */
  @Override
  public void updateImage(Image image, String imageName, List<int[]> list, Dimension dimension) {
    BufferedImage imageToSave = new BufferedImage(image.getLength(),
            image.getHeight(), TYPE_INT_RGB);
    for (int i = 0; i < imageToSave.getHeight(); i++) {
      for (int j = 0 ; j < imageToSave.getWidth(); j++) {
        Pixel current = image.getPixelAt(i, j);
        int red = current.getRed();
        int green = current.getGreen();
        int blue = current.getBlue();
        int alpha = current.getAlpha();
        Color color = new Color(red, green, blue, alpha);
        imageToSave.setRGB(j, i, color.getRGB());
      }
    }

    ImageIcon icon = new ImageIcon(imageToSave);
    this.imagePanel.setIcon(icon);

    this.updateHistogram(list, dimension);
    SwingUtilities.updateComponentTreeUI(this);
  }

  private void imageSelectingPanel() {
    this.imageSelectingPanel = new JLabel();
    this.imageSelectingPanel.setPreferredSize(new Dimension(150, 200));
    this.imagesList = new DefaultListModel<>();
    this.imageListForPanel = new JList<>(imagesList);
    this.imageListForPanel.setModel(this.imagesList);
    this.imageListForPanel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    this.imageSelectingPanel.setLayout(new BorderLayout());
    this.imageSelectingPanel.add(new JScrollPane(this.imageListForPanel), BorderLayout.CENTER);
    this.selectionPanel.add(this.imageSelectingPanel);
  }
}
