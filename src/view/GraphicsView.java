package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


import controller.Features;
import img.Img;
import model.Command;
import model.ImgModel;


public class GraphicsView extends JFrame implements IGraphicsView {
  protected ImgModel model;
  Histogram hist;
  ArrayList<JButton> commandButtons;
  private JTextField input;
  private String mostRecentInput;
  private JLabel imgGraphic;
  private Img currentImg;

  /**
   * Creates a ImgView object.
   *
   * @param model a model for the class
   * @throws IllegalArgumentException if object is null
   * @model a ImgModel object
   */
  public GraphicsView(ImgModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Gave null object");
    } else {
      this.model = model;
    }

    setSize(1000, 1000);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new FlowLayout());

    commandButtons = new ArrayList<JButton>();

    for (Command c: Command.values()) {
      JButton b = new JButton(c.toString());
      commandButtons.add(b);
      this.add(b);
    }

    currentImg = new Img("small", 4, 2);
    currentImg.setPixel(0, 0, 110, 115, 119);
    currentImg.setPixel(0, 1, 120, 125, 129);
    currentImg.setPixel(1, 0, 130, 135, 139);
    currentImg.setPixel(1, 1, 140, 145, 149);

    currentImg.setPixel(2, 0, 150, 155, 159);
    currentImg.setPixel(2, 1, 160, 165, 169);
    currentImg.setPixel(3, 0, 170, 175, 179);
    currentImg.setPixel(3, 1, 180, 185, 189);
    if (currentImg != null) {
      BufferedImage bImg = getBuffImg(currentImg);
      ImageIcon displayedImage = new ImageIcon(bImg);
      JLabel label = new JLabel(displayedImage);
      this.add(label);
    }

      hist = new Histogram(currentImg);
      this.add(hist);
      setVisible(true);
    }


  /**
   * Generates a BufferedImage from the given Img object.
   * @param image An Img object
   * @return A bufferedImg representing the given Img.
   */
  public BufferedImage getBuffImg(Img image) {
    int width = image.getWidth();
    int height = image.getHeight();
    BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = image.getPixel(i, j).getRed();
        int g = image.getPixel(i, j).getGreen();
        int b = image.getPixel(i, j).getBlue();
        int rgb = (r << 16) | (g << 8) | b;
        buffImg.setRGB(j, i, rgb);
      }
    }
    return buffImg;
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    //hist.repaint();
  }

  public void renderHistogram() {
  }

  public void renderImage() {
  }

  public void renderControls() {
  }

  @Override
  public void addFeatures(Features features) {
    for (JButton b: commandButtons) {
      b.addActionListener(evt -> features.doCommand(Command.getCommand(b.getText())));
    }
  }

  public void textBox(String text) {
    JOptionPane.showMessageDialog(null, text,"Message", JOptionPane.ERROR_MESSAGE);
  }


  public void inputBox(String prompt){
    JTextField field = new JTextField(10);
    JPanel panel = new JPanel();
    panel.add(field);
    JButton submit = new JButton("Echo password");
    submit.addActionListener(evt -> makeInputVisibleToController(field));
  }

  public String getMostRecentInput(){
    return mostRecentInput;
  }
  public void resetMostRecentInput(){
    mostRecentInput = null;
  }
  public void makeInputVisibleToController(JTextField field){
    mostRecentInput = field.getText();
  }


}
