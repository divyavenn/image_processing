package model;

import java.io.IOException;
import java.util.ArrayList;

import img.Img;
import img.Pixel;

public abstract class ImgModelAbstract implements ImgModel {

  ArrayList<Img> images;

  public ImgModelAbstract(){
    images = new ArrayList<Img>();
  }

  private String printImages(){
    String list = "";
    for (Img i: images) {
      list = list + i.toString() + "\n";
    }
    return list;
  }

  /**
   * Returns the image with matching name from the list of images if it exists, otherwise throws
   * IllegalArgumentException
   *
   * @throws IllegalArgumentException if image is not in list
   */
  protected Img getImage(String imageName) {
    /** Debugging
    System.out.println("Num of images in list:" + images.size() + "\n");
    System.out.println("Want to get image " + imageName + "\n");
    System.out.println(printImages());
    **/

    if (images.contains(makeImg(imageName,0,0))) {
      return images.get(images.indexOf(makeImg(imageName,0,0)));
    }
    else {
      throw new IllegalArgumentException("Image not in list");
    }
  }

  /**
   * Returns an Img Object corresponding to the implementing class
   * @returns an Img Object
   * @param imageName the name of the image
   * @param height the height of the image
   * @param width the width of the image
   */
  protected abstract Img makeImg(String imageName, int height, int width);

  /**
   * Returns a Pixel Object corresponding to the implementing class
   * @returns a Pixel Object
   * @param r the red component.
   * @param g the green component.
   * @param b the blue component.
   */
  protected abstract Pixel makePixel(int r, int b, int g);

  /**
   * Reads image file and exports to two-dimensional array of pixels
   *
   * @param filepath the file path of the image
   */
  protected abstract Pixel[][] readPixelsImageFile(String filepath);

  /**
   * Reads image file and finds height of image
   *
   * @param filepath the file path of the image
   * @return the height of the image
   */
  protected abstract int readHeightImageFile(String filepath);

  /**
   * Reads image file and finds width of image
   *
   * @param filepath the file path of the image
   * @return the height of the image
   */
  protected abstract int readWidthImageFile(String filepath);

  @Override
  public void load(String filePath, String destinationImageName) {
    if (isCorrectFileType(filePath)) {
      Img destinationImage = makeImg(destinationImageName,
              readHeightImageFile(filePath),
              readWidthImageFile(filePath));
      destinationImage.setPixels(readPixelsImageFile(filePath));
      images.add(destinationImage);
    }
  }

  @Override
  public void save(String filePath, String targetImageName) throws IOException {
    Img targetImage = getImage(targetImageName);
    targetImage.save(filePath);
  }


  @Override
  public void exportComponentByPixel(Command command, String imageName,
                                      String destinationImageName) {
    Img targetImage = getImage(imageName);
    int height = targetImage.getHeight();
    int width = targetImage.getWidth();
    Img destinationImage = makeImg(imageName, height, width);
    Pixel[][] destinationPixels = new Pixel[width][height];
    int value = 0;
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        switch (command) {
          case rc:
            value = targetImage.getPixel(i, j).getRed();
            break;
          case gc:
            value = targetImage.getPixel(i, j).getGreen();
            break;
          case bc:
            value = targetImage.getPixel(i, j).getBlue();
            break;
          case lc:
            value = targetImage.getPixel(i, j).getLuma();
            break;
          case vc:
            value = targetImage.getPixel(i, j).getValue();
            break;
          case ic:
            value = targetImage.getPixel(i, j).getIntensity();
            break;
        }
        destinationPixels[i][j] = makePixel(value, value, value);
      }
    }
    destinationImage.setPixels(destinationPixels);
    images.add(destinationImage);
  }

  @Override
  public void brighten(int increment, String imageName, String destinationImageName) {
    Img targetImage = makeImg("", 0,0);
    try {
      targetImage = getImage(imageName);
    }
    catch (Exception e) {
      System.out.println("Image not in list!");
      throw new IllegalArgumentException("");
    }
    int height = targetImage.getHeight();
    int width = targetImage.getWidth();
    Img destinationImage = makeImg(destinationImageName, height, width);
    Pixel[][] destinationPixels = new Pixel[width][height];
    Pixel targetPixel;
    /** Debugging
    System.out.println("Height: "
            + height
            + "\n Width: "
            + width+ "\n");
    **/

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        /** Debugging
        System.out.println("i :" + i + ", j: " + j + "\n");
         **/
        targetPixel = targetImage.getPixel(i, j);
        int r = targetPixel.getRed();
        int g = targetPixel.getBlue();
        int b = targetPixel.getGreen();
        destinationPixels[i][j] = makePixel(
                Math.min(Math.max(r + increment, 0), 255),
                Math.min(Math.max(g + increment, 0), 255),
                Math.min(Math.max(b + increment, 0), 255));
      }
    }
    destinationImage.setPixels(destinationPixels);
    images.add(destinationImage);
  }

  @Override
  public void flip(Command command, String imageName,
                               String destinationImageName){
    Img targetImage = getImage(imageName);
    int height = targetImage.getHeight();
    int width = targetImage.getWidth();
    Img destinationImage = makeImg(imageName, height, width);
    Pixel[][] destinationPixels = new Pixel[width][height];
    switch (command) {
      case vflip:
        destinationPixels = destinationImage.verticallyFlipped();
      case hflip:
        destinationPixels = destinationImage.horizontallyFlipped();
    }
    destinationImage.setPixels(destinationPixels);
    images.add(destinationImage);
  }

  /**
   * Confirms that is correct file type
   * @return if file is correct type
   */
  protected abstract boolean isCorrectFileType(String filePath);
}
