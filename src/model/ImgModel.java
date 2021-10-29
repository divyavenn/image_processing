package model;

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
   * Saves red component of image in greyscale under destination image name.
   * @param imageName the name of the target image file.
   * @param destinationImageName name of the image to load into.
   */
  public void redComponent(String imageName, String destinationImageName);

  /**
   * Saves blue component of image in greyscale under destination image name.
   * @param imageName the name of the target image file.
   * @param destinationImageName name of the image to load into.
   */
  public void blueComponent(String imageName, String destinationImageName);

  /**
   * Saves green component of image in greyscale under destination image name.
   * @param imageName the name of the target image file.
   * @param destinationImageName name of the image to load into.
   */
  public void greenComponent(String imageName, String destinationImageName);

  /**
   * Saves value of image's pixels in greyscale under destination image name.
   * @param imageName the name of the target image file.
   * @param destinationImageName name of the image to load into.
   */
  public void valueByPixel(String imageName, String destinationImageName);

  /**
   * Saves luma of image's pixels in greyscale under destination image name.
   * @param imageName the name of the target image file.
   * @param destinationImageName name of the image to load into.
   */
  public void lumaByPixel(String imageName, String destinationImageName);

  /**
   * Saves intensity of image's pixels in greyscale under destination image name.
   * @param imageName the name of the target image file.
   * @param destinationImageName name of the image to load into.
   */
  public void intensityByPixel(String imageName, String destinationImageName);

  /**
   * Saves given component of image's pixels in greyscale under destination image name.
   * @param component the component to isolate in the image
   * @param imageName the name of the target image file.
   * @param destinationImageName name of the image to load into.
   */
  public void intensityByPixel(String component, String imageName, String destinationImageName);

  /**
   * Brightens or darkens image by given increment.
   * @param increment the increment to brighten by
   * @param imageName the name of the target image file.
   * @param destinationImageName name of the image to load into.
   */
  public void brighten(int increment, String imageName, String destinationImageName);

}
