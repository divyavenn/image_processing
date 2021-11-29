package controller;

import java.io.IOException;
import java.util.Map;

import img.Img;
import model.Command;
import model.ImgModel;
import view.IGraphicsView;

/**
 * A class that implements ImgController and Features interface. Represents a controller for the
 * GUI implementation of the img program.
 */
public class GUIControllerImplementation implements ImgController, Features {
  private ImgModel model;
  private IGraphicsView view;


  /**
   * A constructor for GUIControllerImplementation.
   *
   * @param model An ImgModel
   * @param view  An IGraphicView
   */
  public GUIControllerImplementation(ImgModel model, IGraphicsView view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Gave null object");
    } else {
      this.model = model;
      this.view = view;
    }
  }

  @Override
  public void setView() {
    view.addFeatures(this);
  }

  @Override
  public void start() {
    this.view.setVisible();
  }

  @Override
  public void doCommand(Command command, Map<Parameter, String> paramValues) {
    try {
      command.run(model, paramValues);
    } catch (IllegalArgumentException | IOException e) {
      view.errorMessage("Command cannot be loaded");
    }
  }

  @Override
  public Img getImageFromModel(String name) {
    Img image;
    try {
      image = model.getImage(name);
    } catch (IllegalArgumentException e) {
      view.errorMessage("No image loaded");
      return null;
    }
    return image;
  }

}