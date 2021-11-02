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
  protected Pixel[][] readPixelsImageFile(String filepath){
    int width = 0;
    int height = 0;
    Pixel[][] pixels;
    try {
      Readable in = new FileReader(filepath);
      Scanner scan = new Scanner(in);
      width = getNextNumericInput(scan);
      height = getNextNumericInput(scan);
      pixels = new Pixel[width][height];
      for (int i = 0; i<width; i++){
        for (int j = 0; j<height; j++) {
          pixels[i][j] = makePixel(getNextNumericInput(scan),
                  getNextNumericInput(scan),
                  getNextNumericInput(scan));

        }
      }
      return pixels;
    }
    catch (FileNotFoundException e) {
      System.out.println("Unable to find file.");
    }
    return null;
  }

  private boolean isNumeric(String inp) {
    try {
      Integer.parseInt(inp);
      return true;
    } catch (NumberFormatException e) {
      // s is not numeric
      return false;
    }
  }

  private int getNextNumericInput(Scanner scan) {
    String next = scan.next();
    if (isNumeric(next)) return Integer.parseInt(next);
    else return getNextNumericInput(scan);
  }

  @Override
  protected int readHeightImageFile(String filepath) {
    try {
      System.out.println(filepath);
      Readable in = new FileReader(filepath);
      Scanner scan = new Scanner(in);
      int h = getNextNumericInput(scan);
      /** Debugging **/
      System.out.println(h);
      return h;
    }
    catch (FileNotFoundException e) {
      System.out.println("Unable to find file.");
    }
    return 0;
  }

  @Override
  protected int readWidthImageFile(String filepath) {
    try {
      Readable in = new FileReader(filepath);
      Scanner scan = new Scanner(in);
      getNextNumericInput(scan);
      int w = getNextNumericInput(scan);
      /** Debugging **/
      System.out.println(w);
      return w;
    }
    catch (FileNotFoundException e) {
      System.out.println("Unable to find file.");
    }
    return 0;
  }

  @Override
  protected boolean isCorrectFileType(String filePath) {
    try {
      Readable in = new FileReader(filePath);
      Scanner scan = new Scanner(in);
      String tag = scan.next();
      return (tag.equals("P3"));
    }
    catch (FileNotFoundException e) {
      System.out.println("Unable to find file.");
    }
    return false;
  }

}
