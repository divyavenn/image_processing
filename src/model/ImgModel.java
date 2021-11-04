package model;


import java.io.IOException;

import img.Img;

/**
 * Represents the backend operations processing an image.
 */
public interface ImgModel {
  /**
   * Loads image from file.
   *
   * @param filePath             the path of the target image file.
   * @param destinationImageName the name of the image to load into.
   */
  public void load(String filePath, String destinationImageName) throws IllegalArgumentException;

  /**
   * Save image to image path file.
   *
   * @param filePath        the path of the target image file.
   * @param targetImageName the name of the image to save from
   */
  public void save(String filePath, String targetImageName) throws IOException;


  /**
   * Saves component of image by pixel in greyscale under destination image name.
   *
   * @param imageName            the name of the target image file.
   * @param destinationImageName name of the image to load into.
   */
  public void exportComponentByPixel(Command command, String imageName,
                                     String destinationImageName);

  /**
   * Brightens or darkens image by given increment.
   *
   * @param increment            the increment to brighten by
   * @param imageName            the name of the target image file.
   * @param destinationImageName name of the image to load into.
   */
  public void brighten(int increment, String imageName, String destinationImageName);

  /**
   * Saves horizontally or vertically flipped version of image
   *
   * @param command              verticalFlip or horizontalFlip
   * @param imageName            the name of the target image file.
   * @param destinationImageName name of the image to load into.
   */
  public void flip(Command command, String imageName, String destinationImageName);

  /**
   * Returns the image with matching name from the list of images if it exists, otherwise throws
   * IllegalArgumentException
   *
   * @throws IllegalArgumentException if image is not in list
   */
  public Img getImage(String imageName);


}
