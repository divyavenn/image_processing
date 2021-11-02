package controller;

import model.Commands;
import model.ImgModel;
import view.ImgView;

public abstract class ImgControllerAbstract implements ImgController{
  ImgModel model;
  ImgView view;
  Readable in;


  /**
   * Returns true if command requires destination image as an input
   * Assumes that input is a valid command.
   * @param command the command
   * @return true or false.
   */
  protected boolean destinationImageArg(String command){
    if (command.equals(Commands.quit)) {
      return false;
    }
    else return true;
  }

  /**
   * Returns true if command requires an target image as an input
   * Assumes that input is a valid command.
   * @param command the command
   * @return true or false.
   */
  protected boolean targetImageArg(String command){
    if (command.equals(Commands.quit)) {
      return false;
    }
    else if(command.equals(Commands.load)) {
      return false;
    }
    else if(command.equals(Commands.save)) {
      return false;
    }
    else return true;
  }

  /**
   * Returns true if command requires an image path as argument.
   * Assumes that input is a valid command.
   * @param command the command
   * @return true or false.
   */
  protected boolean pathArg(String command){
    if (command.equals(Commands.load)) {
      return true;
    }
    else if(command.equals(Commands.save)) {
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
  protected boolean incrementArg(String command){
    if (command.equals(Commands.brighten)) {
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
