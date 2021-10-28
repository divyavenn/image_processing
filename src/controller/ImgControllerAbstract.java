package controller;

import model.ImgModel;
import view.ImgView;

public abstract class ImgControllerAbstract implements ImgController{
  ImgModel model;
  ImgView view;
  Readable in;

  final String load = "load";
  final String brighten = "brighten";
  final String save = "save";
  final String rc = "just-red";
  final String gc = "just-green";
  final String bc = "just-blue";
  final String vc = "just-value";
  final String lc = "just-luma";
  final String ic = "just-intensity";
  final String hflip = "hflip";
  final String vflip = "hflip";


  /**
   * Returns true if command requires an image path as argument.
   * Assumes that input is a valid command.
   * @param command the command
   * @return true or false.
   */
  public boolean pathArg(String command){
    if (command.equals(load)) {
      return true;
    }
    else if(command.equals(save)) {
      return true;
    }
    else return false;
  }


  /**
   * Returns true if command requires an increment as an argument.
   * Assumes that input is a valid command.
   * @param command the command
   * @return true or false.
   */
  public boolean incrementArg(String command){
    if (command.equals(brighten)) {
      return true;
    }
    else return false;
  }




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
