package model;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import img.Img;
import img.PPM;
import img.PPMPixel;
import img.Pixel;

/**
 * Has the backend operations processing PPM Images.
 */
public class PPMModel extends ImgModelAbstract{

  /**
   * Constructs a PPMModel
   */
  public PPMModel(){
    super();
  }

  @Override
  protected Img makeImg(String imageName, int height, int width) {
    return new PPM(imageName, height, width);
  }

  @Override
  protected Pixel makePixel(int r, int g, int b) {
    return new PPMPixel(r,g,b);
  }

  @Override
  protected Img makeImgFromFile(String filepath, String name) throws IllegalArgumentException{
    try {
      Readable in = new FileReader(filepath);
      Scanner scan = new Scanner(in);
      int width = getNextNumericInput(scan);
      int height = getNextNumericInput(scan);
      getNextNumericInput(scan);
      Img image = new PPM(name, height, width);
      for (int i = 0; i<height; i++){
        for (int j = 0; j<width; j++) {
          image.setPixel(
                  i,
                  j,
                  makePixel(getNextNumericInput(scan),
                  getNextNumericInput(scan),
                  getNextNumericInput(scan)));
        }
      }
      return image;
    }
    catch (FileNotFoundException e) {
      System.out.println("Unable to find file.");
      throw new IllegalArgumentException("");
    }
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
