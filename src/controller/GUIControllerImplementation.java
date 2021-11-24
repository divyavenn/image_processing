package controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Map;

import img.Img;
import model.Command;
import model.ImgModel;
import view.GraphicsView;
import view.IGraphicsView;

public class GUIControllerImplementation implements ImgController, Features{
  private ImgModel model;
  private IGraphicsView view;

  public GUIControllerImplementation(ImgModel model, IGraphicsView view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Gave null object");
    } else {
      this.model = model;
      this.view = view;
    }
  }

  public void setView() {
    view.addFeatures();
  }

  @Override
  public void start() {
    this.view.setVisible();
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