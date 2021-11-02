package view;

import java.io.IOException;

import model.ImgModel;

public class TextView extends ImgViewAbstract{

  /**
   * Creates a TextView object.
   *
   * @throws IllegalArgumentException if object is null
   * @model a ImgModel object
   */
  public TextView(ImgModel model, Appendable out) throws IllegalArgumentException {
    super(model, out);
  }

  @Override
  public void renderMessage(String message){
    try {
      this.out.append(message);
    }
    catch (IOException e) {
      System.out.println("Cannot use Appendable.");
    }
  }
}
