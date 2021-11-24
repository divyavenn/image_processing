package view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import controller.Features;
import controller.Parameter;
import img.Img;
import model.Command;
import model.ImgModel;
import util.Tools;


public class GraphicsView extends JFrame implements IGraphicsView {
  protected ImgModel model;
  Histogram hist;
  ArrayList<JMenuItem> commandButtons;
  private Img currentImg;
  private JLabel imageWindow;
  private JMenuBar MenuBar;
  private JMenu Menu;

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
    this.setLayout(new BorderLayout());
    initImage();
    buildCommandButtons();
    buildImageWindow();
    buildHist();
    pack();
  }

  // setPicture(

  private void buildHist() {
    hist = new Histogram(currentImg);
    this.add(hist, BorderLayout.PAGE_END);
    hist.setLayout(new BorderLayout());
    hist.setSize(new Dimension(100, 100));
  }


  private void buildImageWindow() {
    // build separate method: setPicture, setPicture is called after every command
    // this method should just build the WINDOW
    imageWindow = new JLabel();
    imageWindow.setMinimumSize(new Dimension(4, 2));
    imageWindow.setBorder(new LineBorder(Color.black));
    imageWindow.setOpaque(true);
    imageWindow.setBackground(Color.gray);
    JScrollPane scrollPane = new JScrollPane(imageWindow);
    scrollPane.setPreferredSize(new Dimension(500, 500));
    this.add(scrollPane, BorderLayout.CENTER);
  }

  private void buildImage() {
    BufferedImage bImg = Tools.getBuffImg(currentImg);
    ImageIcon image = new ImageIcon(bImg);
    imageWindow.setIcon(image);
  }


  private void buildCommandButtons() {
    MenuBar = new JMenuBar();
    Menu = new JMenu("Options");
    commandButtons = new ArrayList<>();
    for (Command c : Command.values()) {
      JMenuItem m = new JMenuItem(c.toString());
      commandButtons.add(m);
      Menu.add(m);
    }
    MenuBar.add(Menu);
    this.setJMenuBar(MenuBar);
  }

  /**
   * For the sake of testing
   */
  private void initImage() {
    currentImg = new Img("small", 4, 2);
    currentImg.setPixel(0, 0, 110, 115, 119);
    currentImg.setPixel(0, 1, 120, 125, 129);
    currentImg.setPixel(1, 0, 130, 135, 139);
    currentImg.setPixel(1, 1, 140, 145, 149);

    currentImg.setPixel(2, 0, 150, 155, 159);
    currentImg.setPixel(2, 1, 160, 165, 169);
    currentImg.setPixel(3, 0, 170, 175, 179);
    currentImg.setPixel(3, 1, 180, 185, 189);
  }


  @Override
  public void paint(Graphics g) {
    super.paint(g);
  }

  @Override
  public void addFeatures(Features features) {
    for (JMenuItem b : commandButtons) {
      b.addActionListener(evt -> {
        try {
          doCommand(b.getText());
        } catch (IOException e) {
          e.printStackTrace();
        }
      });
    }
  }


  public void doCommand(String s) throws IOException {
    Command c = Command.getCommand(s);
    if (c == null) throw new IllegalArgumentException("Not a valid command");
    Map<Parameter, String> paramValues = new HashMap();
    for (Parameter p : Parameter.values()) {
      paramValues.put(p, null);
    }
    for (Parameter p: Parameter.values()) {
      if (Command.needsParam(c.toString(),p)) {
        String input = getInput(p);
        paramValues.put(p, input);
      }
    }
    c.run(model, paramValues);
    textBox(c.acknowledge(paramValues));
    currentImg = model.getImage(paramValues.get(Parameter.destinationImage));
    System.out.println(paramValues.get(Parameter.destinationImage));
    System.out.println(currentImg.toString());
    buildImage();
    requestFocus();
    revalidate();
    repaint();
  }

  public void textBox(String text) {
    JOptionPane.showMessageDialog(null, text, "Message", JOptionPane.ERROR_MESSAGE);
  }


  public String getInput(Parameter p) {
    String input = "";
    String prompt = "Enter a value for " + p.toString();
    switch (p) {
      case increment:
        input = inputBox(prompt);
        while (!(p.isValidParameter(input))) {
          errorMessage("Not a valid input for " + p.toString());
          input = inputBox(prompt);
        }
        break;
      case filePath:
        System.out.println("Rstrstrstr");
        input = openFile();
        break;
      case targetImage:
      case destinationImage:
        input = "pic";
    }
    return input;
  }

  public void errorMessage(String msg) {
    JPanel panel = new JPanel();
    JOptionPane pane = new JOptionPane();
    panel.add(pane);
    this.add(panel);
    setVisible(true);
    pane.showConfirmDialog(panel, msg);
  }

  public String inputBox(String prompt) {
    JPanel panel = new JPanel();
    JOptionPane pane = new JOptionPane();
    panel.add(pane);
    this.add(panel);
    return pane.showInputDialog(prompt);
  }



  private String openFile() {
    JLabel fileOpenDisplay = new JLabel();
    this.add(fileOpenDisplay);
    JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG & GIF Images", "jpg", "gif");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      return f.getAbsolutePath();
    }
    return "";
  }

  public void refresh() {
    this.requestFocus();
    this.repaint();
  }


}
