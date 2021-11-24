package controller;

import java.awt.event.ActionEvent;
import java.io.IOException;

import model.Command;
import model.ImgModel;
import view.GraphicsView;

public class GUIControllerImplementation implements ImgController, Features{
  private ImgModel model;
  private GraphicsView view;
  String imgName = "pic";

  public GUIControllerImplementation(ImgModel model) {
    this.model = model;
  }

  public void setView(GraphicsView v) {
    view = v;
    view.addFeatures(this);
  }

  @Override
  public void start() {
    this.view.setVisible(true);

  }


  @Override
  public void actionPerformed(ActionEvent e) {

  }

  @Override
  public void doCommand(Command command) throws IOException {

  }

}
