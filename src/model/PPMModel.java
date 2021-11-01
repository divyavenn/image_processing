package model;


import img.Img;
import img.PPM;
import img.PPMPixel;
import img.Pixel;

public class PPMModel extends ImgModelAbstract{

  @Override
  protected Img makeImg(String imageName, int height, int width) {
    return new PPM(imageName, height, width);
  }

  @Override
  protected Pixel makePixel(int r, int g, int b) {
    return new PPMPixel(r,g,b);
  }

  @Override
  protected Pixel[][] readPixelsImageFile(String filepath) {
    return new Pixel[0][];
  }

  @Override
  protected int readHeightImageFile(String filepath) {
    return 0;
  }

  @Override
  protected int readWidthImageFile(String filepath) {
    return 0;
  }


  @Override
  public void load(String filePath, String destinationImageName) {
    int h = readHeightImageFile(filePath);
    int w = readWidthImageFile(filePath);
    Img image = new PPM(destinationImageName, h, w);
    image.setPixels(readPixelsImageFile(filePath));
    images.add(image);
  }

  @Override
  public void flipHorizontally(String imageName, String destinationImageName) {

  }

  @Override
  public void flipVertically(String imageName, String destinationImageName) {

  }


}
