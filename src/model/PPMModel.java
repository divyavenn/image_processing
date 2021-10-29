package model;

import java.util.ArrayList;

import img.Img;

public class PPMModel implements ImgModel{
  ArrayList<Img> images;

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
  public void exportComponentByPixel(String command, String imageName, String destinationImageName){
    if (images.contains(imageName)){
      if (command.)

      }
    }
  }

}
