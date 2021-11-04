package controller;


/**
 * Controlls the program that manipulates images.
 */
public interface ImgController {



  /**
   * Starts the image processing program. Catches all exceptions, allows for inputs with
   * different orders, only requires that when two image paths are specified, the destination image
   * path goes second. Runs appopriate command with given parameters.
   */
  void start();


}
