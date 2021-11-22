package controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Command;
import model.ImgModel;
import view.GraphicsView;
import view.IGraphicsView;

public class GUIControllerImplementation implements ImgController, Features{
  private ImgModel model;
  private GraphicsView view;
  Map<Parameter, String> paramValues = new HashMap();
  public GUIControllerImplementation(ImgModel model) {
    this.model = model;
  }

  public void resetParams(){
    for (Parameter p : Parameter.values()) {
      paramValues.put(p, null);
    }
  }

  public void setView(GraphicsView v) {
    view = v;
    view.addFeatures(this);
  }

  @Override
  public void start() {

  }

  @Override
  public void doCommand(Command c) {
    if (view == null) throw new IllegalArgumentException("Null view");
    if (c == null) throw new IllegalArgumentException("Not a valid command");
    resetParams();
    for (Parameter p: Parameter.values()) {
      if (Command.needsParam(c.toString(),p)) {
        String prompt = "Enter the "  + p.toString();
        String input = view.inputBox(prompt);
        while(!(p.isValidParameter(input))) {
          view.errorMessage("Not a valid input for " + p.toString());
          input = view.inputBox(prompt);
        }
        paramValues.put(p, input);
      }
    }
    view.textBox(c.acknowledge(paramValues));
  }

  @Override
  public void openFile() {
    /**
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG & GIF Images", "jpg", "gif");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      fileOpenDisplay.setText(f.getAbsolutePath());
     **/
  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }
}
