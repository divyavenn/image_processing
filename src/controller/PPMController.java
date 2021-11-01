package controller;

import model.ImgModel;
import view.ImgView;

public class PPMController extends ImgControllerAbstract{

  /**
   * Constructs an ImgController object.
   *
   * @param model
   * @param view
   * @param in
   * @throws IllegalArgumentException if any inputs are null
   */
  public PPMController(ImgModel model, ImgView view, Readable in) throws IllegalArgumentException {
    super(model, view, in);
  }

  @Override
  public void start() throws IllegalStateException {


  }
}
