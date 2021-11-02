package model;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

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
  protected Pixel[][] readPixelsImageFile(String filepath) throws FileNotFoundException {
    Readable in = new FileReader(filepath);
    Scanner scan = new Scanner(in);
    int width = scan.nextInt();
    int height = scan.nextInt();
    Pixel[][] pixels = new Pixel[width][height];
    for (int i = 0; i<width; i++){
      for (int j = 0; j<height; j++) {
        pixels[i][j] = makePixel(scan.nextInt(), scan.nextInt(), scan.nextInt());
      }
    }
    return pixels;
  }

  @Override
  protected int readHeightImageFile(String filepath) throws FileNotFoundException {
    Readable in = new FileReader(filepath);
    Scanner scan = new Scanner(in);
    return scan.nextInt();
  }

  @Override
  protected int readWidthImageFile(String filepath) throws FileNotFoundException {
    Readable in = new FileReader(filepath);
    Scanner scan = new Scanner(in);
    scan.nextInt();
    return scan.nextInt();
  }

}
