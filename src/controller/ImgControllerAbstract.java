package controller;

import model.Command;
import model.ImgModel;
import view.ImgView;

public abstract class ImgControllerAbstract implements ImgController{
  ImgModel model;
  ImgView view;
  Readable in;


  /**
   * Constructs an ImgController object.
   *
   * @throws IllegalArgumentException if any inputs are null
   */
  public ImgControllerAbstract(ImgModel model,
                               ImgView view,
                               Readable in) throws IllegalArgumentException {
    if (model == null || view == null || in == null) {
      throw new IllegalArgumentException("Null input.");
    }
    this.model = model;
    this.view = view;
    this.in = in;
  }

  @Override
  public abstract void start() throws IllegalStateException;
}
