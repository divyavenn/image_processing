package controller;


import java.util.Map;


import img.Img;
import model.Command;

public interface Features  {

  /**
   * Runs the given command with the given parameters on the model.
   * @param command the command to run.
   * @param paramValues the parameters to run the command on.
   */
  public void doCommand(Command command, Map<Parameter, String> paramValues);

  /**
   * Gets the image from the model's list of images with the given name.
   * @param name of the image
   * @returns the image with the given name
   */
  public Img getImageFromModel(String name);
}

