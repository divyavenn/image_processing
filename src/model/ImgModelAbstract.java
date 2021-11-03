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

  @Override
  public Img getImage(String imageName) {
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


  protected abstract Img makeImgFromFile(String filepath, String name);

  /**
   * Returns a Pixel Object corresponding to the implementing class
   * @returns a Pixel Object
   * @param r the red component.
   * @param g the green component.
   * @param b the blue component.
   */
  protected abstract Pixel makePixel(int r, int b, int g);

  @Override
  public void load(String filePath, String destinationImageName) {
    if (isCorrectFileType(filePath)) {
      Img destinationImage = makeImgFromFile(filePath, destinationImageName);
      images.add(destinationImage);
    }
  }

  @Override
  public void save(String filePath, String targetImageName) throws IOException {
    Img targetImage;
    try {
      targetImage = getImage(targetImageName);
    }
    catch (Exception e) {
      System.out.println("Image not in list!");
      throw new IllegalArgumentException("");
    }
    targetImage.save(filePath);
  }


  @Override
  public void exportComponentByPixel(Command command, String imageName,
                                      String destinationImageName) {
    Img targetImage;
    try {
      targetImage = getImage(imageName);
    }
    catch (Exception e) {
      System.out.println("Image not in list!");
      throw new IllegalArgumentException("");
    }
    Img destinationImage = copyImage(targetImage, destinationImageName);
    int height = targetImage.getHeight();
    int width = targetImage.getWidth();
    int value = 0;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
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
        destinationImage.setPixel(i,j,makePixel(value, value, value));
      }
    }
    images.add(destinationImage);
  }

  @Override
  public void brighten(int increment, String imageName, String destinationImageName) {
    Img targetImage;
    try {
      targetImage = getImage(imageName);
    }
    catch (Exception e) {
      System.out.println("Image not in list!");
      throw new IllegalArgumentException("");
    }
    Img destinationImage = copyImage(targetImage, destinationImageName);
    Pixel targetPixel;
    int height = targetImage.getHeight();
    int width = targetImage.getWidth();
    for (int i = 0; i<height; i++){
      for (int j = 0; j<width; j++) {
        targetPixel = targetImage.getPixel(i, j);
        int r = targetPixel.getRed();
        int g = targetPixel.getGreen();
        int b = targetPixel.getBlue();
        destinationImage.setPixel(i,j,
                makePixel(
                Math.min(Math.max(r + increment, 0), 255),
                Math.min(Math.max(g + increment, 0), 255),
                Math.min(Math.max(b + increment, 0), 255)));
      }
    }
    images.add(destinationImage);
  }

  @Override
  public void flip(Command command, String imageName,
                               String destinationImageName) {
    Img targetImage;
    try {
      targetImage = getImage(imageName);
    }
    catch (Exception e) {
      System.out.println("Image not in list!");
      throw new IllegalArgumentException("");
    }
    Img destinationImage = copyImage(targetImage, destinationImageName);
    Pixel targetPixel;
    int height = targetImage.getHeight();
    int width = targetImage.getWidth();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        targetPixel = targetImage.getPixel(i, j);
        int r = targetPixel.getRed();
        int g = targetPixel.getGreen();
        int b = targetPixel.getBlue();
        switch (command) {
          case vflip:
            destinationImage.setPixel(height-i-1, j, makePixel(r,g,b));
            break;
          case hflip:
            destinationImage.setPixel(i, width - j - 1, makePixel(r,g,b));
            break;
        }
      }
    }
    images.add(destinationImage);
  }

  /**
   * Copies all of image's attributes except name
   * @param fromImage the image you wish to copy
   * @param newImageName name of copy
   * @return the copy of the image
   */
  protected Img copyImage(Img fromImage, String newImageName){
    int height = fromImage.getHeight();
    int width = fromImage.getWidth();
    Img copy = makeImg(newImageName, height, width);
    return copy;
  }

  /**
   * Confirms that is correct file type
   * @return if file is correct type
   */
  protected abstract boolean isCorrectFileType(String filePath);
}
