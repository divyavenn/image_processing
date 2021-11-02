package controller;


import java.io.IOException;

public interface ImgController {
  /**
   * Starts the image processing program.
   *
   * @Throws IllegalStateException if unable to successfully read input/transmitoutput
   */
  void start();

  /**
   * What to do when user indicates they wish to quit program
   */
  void programQuit();








}
