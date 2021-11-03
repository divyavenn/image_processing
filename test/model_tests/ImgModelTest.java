package model_tests;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import img.Img;
import img.PPM;
import img.PPMPixel;
import img.Pixel;
import model.ImgModel;
import model.PPMModel;

public class ImgModelTest {
  private String fullPath(String name){
    return "image_processing/images/" + name;
  }
  Img littlePic;
  ImgModel model;




  public ImgModelTest() throws IOException {
    littlePic = new PPM("small", 4, 2);
    littlePic.setPixel(0, 0, new PPMPixel(110, 115, 119));
    littlePic.setPixel(0, 1, new PPMPixel(120, 125, 129));
    littlePic.setPixel(1, 0, new PPMPixel(130, 135, 139));
    littlePic.setPixel(1, 1, new PPMPixel(140, 145, 149));

    littlePic.setPixel(2, 0, new PPMPixel(150, 155, 159));
    littlePic.setPixel(2, 1, new PPMPixel(160, 165, 169));
    littlePic.setPixel(3, 0, new PPMPixel(170, 175, 179));
    littlePic.setPixel(3, 1, new PPMPixel(180, 185, 189));
    littlePic.save("image_processing/res/littlePic.ppm");

    model = new PPMModel();
    model.load("image_processing/res/littlePic.ppm", "small");
  }

  protected boolean contentsMatch(Img a, Img b){
    boolean same = true;
    if (a.getHeight() == b.getHeight() && a.getWidth() == b.getWidth()){
      for (int i = 0; i < a.getHeight(); i++){
        for (int j = 0; j < a.getWidth(); j++) {
          same = same
                  && a.getPixel(i,j).getRed() == b.getPixel(i,j).getRed()
                  && a.getPixel(i,j).getGreen() == b.getPixel(i,j).getGreen()
                  && a.getPixel(i,j).getBlue() == b.getPixel(i,j).getBlue();
        }
      }
      return same;
    }
    return false;
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


  @Test
  public void testSave() throws IOException {
    String fPath = "image_processing/res/littlePic2.ppm";
    model.save(fPath,  "small");
    try {
      Readable in = new FileReader(fPath);
      Scanner scan = new Scanner(in);
      int width = getNextNumericInput(scan);
      int height = getNextNumericInput(scan);
      getNextNumericInput(scan);
      Img image = new PPM("", height, width);
      for (int i = 0; i<height; i++){
        for (int j = 0; j<width; j++) {
          image.setPixel(
                  i,
                  j,
                  new PPMPixel(getNextNumericInput(scan),
                          getNextNumericInput(scan),
                          getNextNumericInput(scan)));
        }
      }
      assertEquals(contentsMatch(littlePic, image), true);
    }
    catch (FileNotFoundException e) {
      System.out.println("Unable to find file.");
      throw new IllegalArgumentException("");
    }


  }

  @Test
  public void testLoad(){
    String fPath = "image_processing/res/littlePic.ppm";
    model.load(fPath, "loadedImage");


  }

  @Test
  public void testGetComponent(){

  }

  @Test
  public void testFlip() {

  }

  @Test
  public void testBrighten(){

  }




  
  
}
