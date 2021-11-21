package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


import controller.Features;
import model.Command;
import model.ImgModel;

public class GraphicsView extends JFrame implements IGraphicsView {
  protected ImgModel model;

  ArrayList<JButton> commandButtons;

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
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new FlowLayout());

    commandButtons = new ArrayList<JButton>();

    for (Command c: Command.values()) {
      JButton b = new JButton(c.toString());
      commandButtons.add(b);
      this.add(b);
    }

    Histogram hist = new Histogram();
    hist.showHistogram(new int[]{1,2,3,4,5});
    this.add(hist);
    setVisible(true);
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
    System.out.println("HI!");
    JOptionPane.showMessageDialog(null, text,"Message", JOptionPane.ERROR_MESSAGE);
  }
}
