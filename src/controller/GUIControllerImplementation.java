package controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
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
  public GUIControllerImplementation(ImgModel model) {
    this.model = model;
  }

  public void setView(GraphicsView v) {
    view = v;
    view.addFeatures(this);
  }

  @Override
  public void start() {

  }


  @Override
  public void actionPerformed(ActionEvent e) {

  }

  @Override
  public void doCommand(Command command) throws IOException {

  }
}
