package view;

import javax.swing.*;
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


/**
 * A graphical user interface for the image processor.
 */
public class GraphicsView extends JFrame implements IGraphicsView {
  protected ImgModel model;
  ArrayList<JMenuItem> commandButtons;
  private Img currentImg;
  private JLabel imageWindow;
  private HistogramPanel histogramPanel;
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
    buildCommandButtons();
    buildHistogramWindow();
    buildImageWindow();
  }

  /**
   * Builds the window holding the histogram.
   */
  private void buildHistogramWindow(){
    histogramPanel = new HistogramPanel();
    this.add(histogramPanel, BorderLayout.WEST);
  }

  /**
   * Builds the image window.
   */
  private void buildImageWindow() {
    // build separate method: setPicture, setPicture is called after every command
    // this method should just build the WINDOW
    imageWindow = new JLabel();
    imageWindow.setMinimumSize(new Dimension(500, 500));
    imageWindow.setBorder(new LineBorder(Color.black));
    imageWindow.setOpaque(true);
    imageWindow.setBackground(Color.gray);
    JScrollPane scrollPane = new JScrollPane(imageWindow);
    scrollPane.setMinimumSize(new Dimension(this.getWidth() - histogramPanel.getWidth(), 500));
    scrollPane.setPreferredSize(new Dimension(500, 500));
    this.add(scrollPane, BorderLayout.CENTER);
  }


  /**
   * Rebuilds the histogram in its panel
   */
  private void buildHistogram() {
    histogramPanel.setImage(currentImg);
  }

  /**
   * Rebuilds the image in its panel.
   */
  private void buildImage() {
    BufferedImage bImg = Tools.getBuffImg(currentImg);
    ImageIcon image = new ImageIcon(bImg);
    imageWindow.setIcon(image);
  }

  /**
   * Does the necessary IO following an input indicating a command.
   * @param s the input.
   */
  public void doCommand(String s) {
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
    try {
      c.run(model, paramValues);
    }
    catch (IllegalArgumentException | IOException e){
      errorMessage("Something went wrong. Try again.");
    }
    System.out.println(paramValues.get(Parameter.filePath));
    //textBox(c.acknowledge(paramValues));
    currentImg = model.getImage(paramValues.get(Parameter.destinationImage));
    System.out.println(paramValues.get(Parameter.destinationImage));
    System.out.println(currentImg.toString());
    buildImage();
    buildHistogram();
  }

  /**
   * Builds the command buttons.
   */
  private void buildCommandButtons() {
    MenuBar = new JMenuBar();
    Menu = new JMenu("Options");
    JMenu File = new JMenu("File");
    JMenu Transform = new JMenu("Transform");
    JMenu Filter = new JMenu("Filter");

    commandButtons = new ArrayList<>();
    for (Command c : Command.values()) {
      JMenuItem m = new JMenuItem(c.toString());
      commandButtons.add(m);
      switch(c){
        case load:
        case save:
        case quit:
          File.add(m);
          break;
        case brighten:
        case rc:
        case bc:
        case gc:
        case ic:
        case lc:
        case vc:
        case hflip:
        case vflip:
        case blur:
        case sharpen:
          Transform.add(m);
          break;
        case grey:
        case sepia:
          Filter.add(m);
          break;
        default:
          break;

      }

    }
    MenuBar.add(File);
    MenuBar.add(Transform);
    MenuBar.add(Filter);
    this.setJMenuBar(MenuBar);
  }


  @Override
  public void addFeatures() {
    for (JMenuItem b : commandButtons) {
      b.addActionListener(evt -> doCommand(b.getText()));
    }
  }

  @Override
  public void setVisible() {
    this.setVisible(true);
  }


  /**
   * Creates a textbox and displays to user.
   * @param text the text in the box.
   */
  private void textBox(String text) {
    JOptionPane.showMessageDialog(null, text, "Message", JOptionPane.OK_OPTION);
  }


  /**
   * Creates boxes until the correct input type for the given parameter is recieved.
   * @param p the needed Parameter.
   * @return the String with a valid input.
   */
  private String getInput(Parameter p) {
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

  /**
   * Puts out an error message given String.
   * @param msg the String to put on the error message.
   */
  public void errorMessage(String msg) {
    JPanel panel = new JPanel();
    JOptionPane pane = new JOptionPane();
    panel.add(pane);
    this.add(panel);
    setVisible(true);
    pane.showConfirmDialog(panel, msg);
  }

  /**
   * Gets an input.
   * @param prompt the prompt in the box.
   * @return the input.
   */
  private String inputBox(String prompt) {
    JPanel panel = new JPanel();
    JOptionPane pane = new JOptionPane();
    panel.add(pane);
    this.add(panel);
    return pane.showInputDialog(prompt);
  }


  /**
   * A box to select a filePath.
   * @return the filepath.
   */
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





}
