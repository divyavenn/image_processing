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

}
