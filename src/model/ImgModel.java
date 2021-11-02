package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import img.Img;

/**
 *
 */
public interface ImgModel {
  /**
   * Loads image from file.
   * @param filePath the path of the target image file.
   * @param destinationImageName the name of the image to load into.
   */
  public void load(String filePath, String destinationImageName);

  /**
   * Save image to image path file.
   * @param filePath the path of the target image file.
   * @param destinationImageName the name of the image to load into.
   */
  public void save(String filePath, String destinationImageName);


  /**
   * Saves component of image by pixel in greyscale under destination image name.
   * @param imageName the name of the target image file.
   * @param destinationImageName name of the image to load into.
   */
  public void exportComponentByPixel(Command command, String imageName,
                                     String destinationImageName);

  /**
   * Brightens or darkens image by given increment.
   * @param increment the increment to brighten by
   * @param imageName the name of the target image file.
   * @param destinationImageName name of the image to load into.
   */
  public void brighten(int increment, String imageName, String destinationImageName);

  /**
   * Saves horizontally or vertically flipped version of image
   * @param command verticalFlip or horizontalFlip
   * @param imageName the name of the target image file.
   * @param destinationImageName name of the image to load into.
   */
  public void flip(Command command, String imageName, String destinationImageName);


}
