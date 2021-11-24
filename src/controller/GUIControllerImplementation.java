package controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Map;

import img.Img;
import model.Command;
import model.ImgModel;
import view.GraphicsView;

public class GUIControllerImplementation implements ImgController, Features{
  private ImgModel model;
  private GraphicsView view;
  String imgName = "pic";

  public GUIControllerImplementation(ImgModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Gave null object");
    } else {
      this.model = model;
    }
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
  public void doCommand(Command command, Map<Parameter, String> paramValues){
    try {
      command.run(model, paramValues);
    }
    catch (IllegalArgumentException | IOException e){
    }
  }

  @Override
  public Img getImageFromModel(String name) {
    Img image;
    try {
      image = model.getImage(name);
    }
    catch (IllegalArgumentException e) {
      view.errorMessage("No image loaded");
      return null;
    }
    return image;
  }

}
