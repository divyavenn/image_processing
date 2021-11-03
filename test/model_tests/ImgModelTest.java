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
    model.load("image_processing/res/littlePic.ppm", "littlePic");
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
    model.save(fPath,  "littlePic");
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
    Img loadedImage = model.getImage("loadedImage");
    contentsMatch(littlePic, loadedImage);
  }

  @Test
  public void testGetComponent(){

  }

  @Test
  public void testVerticalFlip() {

  }

  @Test
  public void testHorizontalFlip() {

  }

  @Test
  public void testBrighten() throws IOException {
    Img brightLittlePic;
    brightLittlePic = new PPM("brightLittlePic", 4, 2);
    int brightenBy = 50;
    brightLittlePic.setPixel(0, 0, new PPMPixel(
            Math.min(110+brightenBy,255),
            Math.min(115+brightenBy,255),
            Math.min(119+brightenBy,255)));
    brightLittlePic.setPixel(0, 1, new PPMPixel(
            Math.min(120+brightenBy,255),
            Math.min(125+brightenBy,255),
            Math.min(129+brightenBy,255)));
    brightLittlePic.setPixel(1, 0, new PPMPixel(
            Math.min(130+brightenBy,255),
            Math.min(135+brightenBy,255),
            Math.min(139+brightenBy,255)));
    brightLittlePic.setPixel(1, 1, new PPMPixel(
            Math.min(140+brightenBy,255),
            Math.min(145+brightenBy,255),
            Math.min(149+brightenBy,255)));

    brightLittlePic.setPixel(2, 0, new PPMPixel(
            Math.min(150+brightenBy,255),
            Math.min(155+brightenBy,255),
            Math.min(159+brightenBy,255)));
    brightLittlePic.setPixel(2, 1, new PPMPixel(
            Math.min(160+brightenBy,255),
            Math.min(165+brightenBy,255),
            Math.min(169+brightenBy,255)));
    brightLittlePic.setPixel(3, 0, new PPMPixel(
            Math.min(170+brightenBy,255),
            Math.min(175+brightenBy,255),
            Math.min(179+brightenBy,255)));
    brightLittlePic.setPixel(3, 1, new PPMPixel(
            Math.min(180+brightenBy,255),
            Math.min(185+brightenBy,255),
            Math.min(189+brightenBy,255)));

    model.brighten(brightenBy, "littlePic", "alsoBrightLittlePic");
    assertEquals(contentsMatch(brightLittlePic, model.getImage("alsoBrightLittlePic")), true);
  }

  @Test
  public void testDarken() throws IOException {
    Img darkLittlePic;
    darkLittlePic = new PPM("darkLittlePic", 4, 2);
    int brightenBy = -120;
    darkLittlePic.setPixel(0, 0, new PPMPixel(
            Math.max(110+brightenBy,0),
            Math.max(115+brightenBy,0),
            Math.max(119+brightenBy,0)));
    darkLittlePic.setPixel(0, 1, new PPMPixel(
            Math.max(120+brightenBy,0),
            Math.max(125+brightenBy,0),
            Math.max(129+brightenBy,0)));
    darkLittlePic.setPixel(1, 0, new PPMPixel(
            Math.max(130+brightenBy,0),
            Math.max(135+brightenBy,0),
            Math.max(139+brightenBy,0)));
    darkLittlePic.setPixel(1, 1, new PPMPixel(
            Math.max(140+brightenBy,0),
            Math.max(145+brightenBy,0),
            Math.max(149+brightenBy,0)));

    darkLittlePic.setPixel(2, 0, new PPMPixel(
            Math.max(150+brightenBy,0),
            Math.max(155+brightenBy,0),
            Math.max(159+brightenBy,0)));
    darkLittlePic.setPixel(2, 1, new PPMPixel(
            Math.max(160+brightenBy,0),
            Math.max(165+brightenBy,0),
            Math.max(169+brightenBy,0)));
    darkLittlePic.setPixel(3, 0, new PPMPixel(
            Math.max(170+brightenBy,0),
            Math.max(175+brightenBy,0),
            Math.max(179+brightenBy,0)));
    darkLittlePic.setPixel(3, 1, new PPMPixel(
            Math.max(180+brightenBy,0),
            Math.max(185+brightenBy,0),
            Math.max(189+brightenBy,0)));

    model.brighten(brightenBy, "littlePic", "darkLittlePic");
    assertEquals(contentsMatch(darkLittlePic, model.getImage("darkLittlePic")), true);

  }







}
