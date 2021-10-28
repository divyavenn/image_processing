package model;

/**
 *
 */
public interface ImgModel {


  /**
   * Flip an image horizontally.
   */
  void horizontalFlip(String imgName);

  /**
   * Flip an image vertically
   */
  void verticalFlip(String imgName);

  /**
   *
   */
  void brighten(String imgName);

  /**
   *
   */
  void grayscaleR(String imgName);




}
