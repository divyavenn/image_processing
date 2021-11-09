package modeltests;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import img.FileType;
import img.Img;
import img.Pixel;
import model.Command;
import model.ImgModel;
import model.ImgModelImplementation;
import util.Tools;

/**
 * Tests the methods of ImageModel.
 */
public class ImgModelTest {

  Img littlePic;
  ImgModel model;


  public ImgModelTest() throws IOException {
    littlePic = new Img( "littlePic", 4, 2);
    littlePic.setPixel(0, 0, 110, 115, 119);
    littlePic.setPixel(0, 1, 120, 125, 129);
    littlePic.setPixel(1, 0, 130, 135, 139);
    littlePic.setPixel(1, 1,  140, 145, 149);

    littlePic.setPixel(2, 0,  150, 155, 159);
    littlePic.setPixel(2, 1,  160, 165, 169);
    littlePic.setPixel(3, 0,  170, 175, 179);
    littlePic.setPixel(3, 1,  180, 185, 189);
    littlePic.save("image_processing/res/littlePic/littlePic.ppm");
    littlePic.save("image_processing/res/littlePic/littlePic.png");
    littlePic.save("image_processing/res/littlePic/littlePic.jpeg");

    model = new ImgModelImplementation();
    model.load("image_processing/res/littlePic/littlePic.ppm", "littlePic");
  }

  /**
   * Checks if the pixels of two images match.
   *
   * @param a first image
   * @param b second image.
   * @return if they both match.
   */
  protected boolean contentsMatch(Img a, Img b) {
    boolean same = true;
    if (a.getHeight() == b.getHeight() && a.getWidth() == b.getWidth()) {
      for (int i = 0; i < a.getHeight(); i++) {
        for (int j = 0; j < a.getWidth(); j++) {
          same = same
                  && a.getPixel(i, j).getRed() == b.getPixel(i, j).getRed()
                  && a.getPixel(i, j).getGreen() == b.getPixel(i, j).getGreen()
                  && a.getPixel(i, j).getBlue() == b.getPixel(i, j).getBlue();
          if (same == false) {
            System.out.println(a.toString() + ": " + a.getPixel(i, j).toString());
            System.out.println(b.toString() + ": " + b.getPixel(i,j).toString());
            System.out.println("----------------------------------------------");
            same = true;
          }
        }
      }
      return same;
    }
    return false;
  }

  /**
   * Checks if all the three values in a pixel match the given value.
   *
   * @param p   Pixel to check
   * @param val the given value
   * @return true if the pixel equals the given value.
   */
  private boolean pixelEquals(Pixel p, int val) {
    return (p.getRed() == val) && (p.getGreen() == val) && (p.getBlue() == val);
  }

  /**
   * Tests all the getComponent commands.
   *
   * @param a the Image
   * @param c the command
   * @return if all components are working correctly.
   */
  protected boolean testComponent(Img a, Command c) {
    boolean correct = true;
    Pixel thisPixel;
    boolean thisPixelCorrect = true;
    for (int i = 0; i < a.getHeight(); i++) {
      for (int j = 0; j < a.getWidth(); j++) {
        thisPixel = a.getPixel(i, j);
        switch (c) {
          case rc:
            thisPixelCorrect = pixelEquals(thisPixel, thisPixel.getRed());
            break;
          case gc:
            thisPixelCorrect = pixelEquals(thisPixel, thisPixel.getGreen());
            break;
          case bc:
            thisPixelCorrect = pixelEquals(thisPixel, thisPixel.getBlue());
            break;
          case ic:
            thisPixelCorrect = pixelEquals(thisPixel, thisPixel.getIntensity());
            break;
          case vc:
            thisPixelCorrect = pixelEquals(thisPixel, thisPixel.getValue());
            break;
          case lc:
            thisPixelCorrect = pixelEquals(thisPixel, thisPixel.getLuma());
            break;
          default:
            break;
        }
        correct = correct && thisPixelCorrect;
      }
    }
    return correct;
  }


  @Test
  public void testBlur() throws IOException {
    Img blurPic = new Img("blur", 4, 2);

    blurPic.setPixel(0, 0,
            (int)Math.round(62.5),
            (int)Math.round(70.31) ,
            (int)Math.round(72.56));
    blurPic.setPixel(0, 1,
            (int)Math.round(54.38),
            (int)Math.round(72.19) ,
            (int)Math.round(74.44));
    blurPic.setPixel(1, 0,
            (int)Math.round(101.25),
            (int)Math.round(103.75) ,
            (int)Math.round(106.75));
    blurPic.setPixel(1, 1,
            (int)Math.round(85),
            (int)Math.round(106.25) ,
            (int)Math.round(109.25));

    blurPic.setPixel(2, 0,
            (int)Math.round(116.25),
            (int)Math.round(118.75) ,
            (int)Math.round(121.75));
    blurPic.setPixel(2, 1,
            (int)Math.round(97.5),
            (int)Math.round(121.25) ,
            (int)Math.round(124.25));
    blurPic.setPixel(3, 0,
            (int)Math.round(82.5),
            (int)Math.round(96.56) ,
            (int)Math.round(98.81));
    blurPic.setPixel(3, 1,
            (int)Math.round(73.13),
            (int)Math.round(98.44) ,
            (int)Math.round(100.69));

    model.applyFilter(Command.blurFilter, "littlePic", "blurryPic");
    assertEquals(contentsMatch(blurPic, model.getImage("blurryPic")), true);

  }

  @Test
  public void testSavePPM() throws IOException {
    String fPath = "image_processing/res/littlePic/littlePic.ppm";
    model.save( fPath, "littlePic");
    try {
      Readable in = new FileReader(fPath);
      Scanner scan = new Scanner(in);
      int width = Tools.getNextNumericInput(scan);
      int height = Tools.getNextNumericInput(scan);
      Tools.getNextNumericInput(scan);
      Img image = new Img( "", height, width);
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          image.setPixel(
                  i,
                  j,
                  Tools.getNextNumericInput(scan),
                          Tools.getNextNumericInput(scan),
                          Tools.getNextNumericInput(scan));
        }
      }
      assertEquals(contentsMatch(littlePic, image), true);
    } catch (FileNotFoundException e) {
      System.out.println("Unable to find file.");
      throw new IllegalArgumentException("");
    }
  }

  @Test
  public void testSaveJPEG() throws IOException {
    String fPath = "image_processing/res/littlePic/littlePic2.jpeg";
    model.save( fPath, "littlePic");
    BufferedImage buffImg;
    try {
      buffImg = ImageIO.read(new File(fPath));
      int width = buffImg.getWidth();
      int height = buffImg.getHeight();
      Img image = new Img("", height, width);
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          Color c = new Color(buffImg.getRGB(j, i));
          int r = c.getRed();
          int g = c.getGreen();
          int b = c.getBlue();
          image.setPixel(i, j, r, g, b);
        }
      }
      assertEquals(FileType.getCorrectFileType(fPath), FileType.jpeg);
    } catch (FileNotFoundException e) {
      System.out.println("Unable to find file.");
      throw new IllegalArgumentException("");
    }

  }

  @Test
  public void testSavePNG() throws IOException {
    String fPath = "image_processing/res/littlePic/littlePic2.png";
    model.save( fPath, "littlePic");
    BufferedImage buffImg;
    try {
      buffImg = ImageIO.read(new File(fPath));
      int width = buffImg.getWidth();
      int height = buffImg.getHeight();
      Img image = new Img("", height, width);
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          Color c = new Color(buffImg.getRGB(j, i));
          int r = c.getRed();
          int g = c.getGreen();
          int b = c.getBlue();
          image.setPixel(i, j, r, g, b);
        }
      }
      assertEquals(contentsMatch(littlePic, image), true);
    } catch (FileNotFoundException e) {
      System.out.println("Unable to find file.");
      throw new IllegalArgumentException("");
    }
  }

  @Test(expected = Exception.class)
  public void testSaveException() throws Exception {
    String fPath = "/";
    model.save(fPath, "littlePic");
  }

  @Test
  public void testLoadPPM() {
    String fPath = "image_processing/res/littlePic/littlePic.ppm";
    model.load(fPath, "loadedImage");
    Img loadedImage = model.getImage("loadedImage");
    assertEquals(contentsMatch(littlePic, loadedImage), true);
  }

  @Test
  public void testLoadJPEG() {
    String fPath = "image_processing/res/littlePic/littlePic.jpeg";
    model.load(fPath, "loadedImage");
    Img loadedImage = model.getImage("loadedImage");
    boolean isNull = loadedImage == null;
    assertEquals(isNull, false);
  }

  @Test
  public void testLoadPNG() {
    String fPath = "image_processing/res/littlePic/littlePic.png";
    model.load(fPath, "loadedImage");
    Img loadedImage = model.getImage("loadedImage");
    assertEquals(contentsMatch(littlePic, loadedImage), true);
  }


  @Test
  public void testAllComponents() {
    boolean allComponentspassed = true;
    boolean componentPassed = true;
    Img img;
    for (Command c : Command.values()) {
      model.exportComponentByPixel(c, "littlePic", c.toString() + "littlePic");
      img = model.getImage(c.toString() + "littlePic");
      componentPassed = testComponent(img, c);
      allComponentspassed = allComponentspassed & componentPassed;
    }
    assertEquals(allComponentspassed, true);
  }


  @Test
  public void testVerticalFlip() {
    Img flipPic = new Img("flip", 4, 2);

    flipPic.setPixel(0, 0, 170, 175, 179);
    flipPic.setPixel(0, 1,  180, 185, 189);
    flipPic.setPixel(1, 0,  150, 155, 159);
    flipPic.setPixel(1, 1,  160, 165, 169);

    flipPic.setPixel(2, 0,  130, 135, 139);
    flipPic.setPixel(2, 1,  140, 145, 149);
    flipPic.setPixel(3, 0,  110, 115, 119);
    flipPic.setPixel(3, 1, 120, 125, 129);

    model.flip(Command.vflip, "littlePic", "vFlipPic");
    Img modelFlipPic = model.getImage("vFlipPic");
    assertEquals(contentsMatch(flipPic, modelFlipPic), true);
  }

  @Test
  public void testHorizontalFlip() {
    Img flipPic = new Img( "flip", 4, 2);

    flipPic.setPixel(0, 1, 110, 115, 119);
    flipPic.setPixel(0, 0,  120, 125, 129);
    flipPic.setPixel(1, 1,  130, 135, 139);
    flipPic.setPixel(1, 0,  140, 145, 149);

    flipPic.setPixel(2, 1,  150, 155, 159);
    flipPic.setPixel(2, 0, 160, 165, 169);
    flipPic.setPixel(3, 1,  170, 175, 179);
    flipPic.setPixel(3, 0,  180, 185, 189);


    model.flip(Command.hflip, "littlePic", "hFlipPic");
    Img modelFlipPic = model.getImage("hFlipPic");
    assertEquals(contentsMatch(flipPic, modelFlipPic), true);
  }

  @Test
  public void testBrighten() throws IOException {
    Img brightLittlePic;
    brightLittlePic = new Img( "brightLittlePic", 4, 2);
    int brightenBy = 50;
    brightLittlePic.setPixel(0, 0,
            Math.min(110 + brightenBy, 255),
            Math.min(115 + brightenBy, 255),
            Math.min(119 + brightenBy, 255));
    brightLittlePic.setPixel(0, 1,
            Math.min(120 + brightenBy, 255),
            Math.min(125 + brightenBy, 255),
            Math.min(129 + brightenBy, 255));
    brightLittlePic.setPixel(1, 0,
            Math.min(130 + brightenBy, 255),
            Math.min(135 + brightenBy, 255),
            Math.min(139 + brightenBy, 255));
    brightLittlePic.setPixel(1, 1,
            Math.min(140 + brightenBy, 255),
            Math.min(145 + brightenBy, 255),
            Math.min(149 + brightenBy, 255));

    brightLittlePic.setPixel(2, 0,
            Math.min(150 + brightenBy, 255),
            Math.min(155 + brightenBy, 255),
            Math.min(159 + brightenBy, 255));
    brightLittlePic.setPixel(2, 1,
            Math.min(160 + brightenBy, 255),
            Math.min(165 + brightenBy, 255),
            Math.min(169 + brightenBy, 255));
    brightLittlePic.setPixel(3, 0,
            Math.min(170 + brightenBy, 255),
            Math.min(175 + brightenBy, 255),
            Math.min(179 + brightenBy, 255));
    brightLittlePic.setPixel(3, 1,
            Math.min(180 + brightenBy, 255),
            Math.min(185 + brightenBy, 255),
            Math.min(189 + brightenBy, 255));

    model.brighten(brightenBy, "littlePic", "alsoBrightLittlePic");
    assertEquals(contentsMatch(brightLittlePic, model.getImage("alsoBrightLittlePic")), true);
  }

  @Test
  public void testDarken() throws IOException {
    Img darkLittlePic;
    darkLittlePic = new Img("darkLittlePic", 4, 2);
    int brightenBy = -120;
    darkLittlePic.setPixel(0, 0,
            Math.max(110 + brightenBy, 0),
            Math.max(115 + brightenBy, 0),
            Math.max(119 + brightenBy, 0));
    darkLittlePic.setPixel(0, 1,
            Math.max(120 + brightenBy, 0),
            Math.max(125 + brightenBy, 0),
            Math.max(129 + brightenBy, 0));
    darkLittlePic.setPixel(1, 0,
            Math.max(130 + brightenBy, 0),
            Math.max(135 + brightenBy, 0),
            Math.max(139 + brightenBy, 0));
    darkLittlePic.setPixel(1, 1,
            Math.max(140 + brightenBy, 0),
            Math.max(145 + brightenBy, 0),
            Math.max(149 + brightenBy, 0));

    darkLittlePic.setPixel(2, 0,
            Math.max(150 + brightenBy, 0),
            Math.max(155 + brightenBy, 0),
            Math.max(159 + brightenBy, 0));
    darkLittlePic.setPixel(2, 1,
            Math.max(160 + brightenBy, 0),
            Math.max(165 + brightenBy, 0),
            Math.max(169 + brightenBy, 0));
    darkLittlePic.setPixel(3, 0,
            Math.max(170 + brightenBy, 0),
            Math.max(175 + brightenBy, 0),
            Math.max(179 + brightenBy, 0));
    darkLittlePic.setPixel(3, 1,
            Math.max(180 + brightenBy, 0),
            Math.max(185 + brightenBy, 0),
            Math.max(189 + brightenBy, 0));

    model.brighten(brightenBy, "littlePic", "darkLittlePic");
    assertEquals(contentsMatch(darkLittlePic, model.getImage("darkLittlePic")), true);

  }


}