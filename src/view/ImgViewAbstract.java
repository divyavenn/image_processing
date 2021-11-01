package view;

import model.ImgModel;

public abstract class ImgViewAbstract implements ImgView{
  protected ImgModel model;
  protected Appendable out;

  /**
   * Creates a ImgView object.
   *
   * @throws IllegalArgumentException if object is null
   * @model a ImgModel object
   */
  public ImgViewAbstract(ImgModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Gave null object");
    } else {
      this.model = model;
    }
  }
}
