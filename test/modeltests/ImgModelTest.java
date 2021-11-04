package modeltests;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import img.ImageType;
import img.Img;
import img.Pixel;
import model.Command;
import model.ImgModel;

/**
 * Tests the methods of ImageModel.
 */
public abstract class ImgModelTest {

  Img littlePic;
  ImgModel model;
  ImageType type;


  /**
   * Instantiates class fields based on type.
   * @throws IOException if cannot save to file.
   */
  protected void instantiate() throws IOException {
    littlePic = ImageType.makeImg(type, "small", 4, 2);
    littlePic.setPixel(0, 0, ImageType.makePixel(type, 110, 115, 119));
    littlePic.setPixel(0, 1, ImageType.makePixel(type, 120, 125, 129));
    littlePic.setPixel(1, 0, ImageType.makePixel(type, 130, 135, 139));
    littlePic.setPixel(1, 1, ImageType.makePixel(type, 140, 145, 149));

    littlePic.setPixel(2, 0, ImageType.makePixel(type, 150, 155, 159));
    littlePic.setPixel(2, 1, ImageType.makePixel(type, 160, 165, 169));
    littlePic.setPixel(3, 0, ImageType.makePixel(type, 170, 175, 179));
    littlePic.setPixel(3, 1, ImageType.makePixel(type, 180, 185, 189));
    littlePic.save("image_processing/res/littlePic.ppm");

    model = ImageType.makeModel(type);
    model.load("image_processing/res/littlePic.ppm", "littlePic");
  }

  /**
   * Checks if the pixels of two images match.
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
        }
      }
      return same;
    }
    return false;
  }

  /**
   * Checks if all the three values in a pixel match the given value.
   * @param p Pixel to check
   * @param val the given value
   * @return true if the pixel equals the given value.
   */
  private boolean pixelEquals(Pixel p, int val) {
    return (p.getRed() == val) && (p.getGreen() == val) && (p.getBlue() == val);
  }

  /**
   * Tests all the getComponent commands.
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


  /**
   * Tests if input is numeric.
   * @param inp the String
   * @return true if numeric
   */
  private boolean isNumeric(String inp) {
    try {
      Integer.parseInt(inp);
      return true;
    } catch (NumberFormatException e) {
      // s is not numeric
      return false;
    }
  }

  /**
   * Gets next numeric input from Scanner.
   * @param scan the scanner.
   * @return the next numeric input.
   */
  private int getNextNumericInput(Scanner scan) {
    String next = scan.next();
    if (isNumeric(next)) {
      return Integer.parseInt(next);
    }
    else {
      return getNextNumericInput(scan);
    }
  }


  @Test
  public void testSave() throws IOException {
    String fPath = "image_processing/res/littlePic2.ppm";
    model.save(fPath, "littlePic");
    try {
      Readable in = new FileReader(fPath);
      Scanner scan = new Scanner(in);
      int width = getNextNumericInput(scan);
      int height = getNextNumericInput(scan);
      getNextNumericInput(scan);
      Img image = ImageType.makeImg(type, "", height, width);
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          image.setPixel(
                  i,
                  j,
                  ImageType.makePixel(type, getNextNumericInput(scan),
                          getNextNumericInput(scan),
                          getNextNumericInput(scan)));
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
  public void testLoad() {
    String fPath = "image_processing/res/littlePic.ppm";
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
    Img flipPic = ImageType.makeImg(type, "flip", 4, 2);

    flipPic.setPixel(0, 0, ImageType.makePixel(type, 170, 175, 179));
    flipPic.setPixel(0, 1, ImageType.makePixel(type, 180, 185, 189));
    flipPic.setPixel(1, 0, ImageType.makePixel(type, 150, 155, 159));
    flipPic.setPixel(1, 1, ImageType.makePixel(type, 160, 165, 169));

    flipPic.setPixel(2, 0, ImageType.makePixel(type, 130, 135, 139));
    flipPic.setPixel(2, 1, ImageType.makePixel(type, 140, 145, 149));
    flipPic.setPixel(3, 0, ImageType.makePixel(type, 110, 115, 119));
    flipPic.setPixel(3, 1, ImageType.makePixel(type, 120, 125, 129));

    model.flip(Command.vflip, "littlePic", "vFlipPic");
    Img modelFlipPic = model.getImage("vFlipPic");
    assertEquals(contentsMatch(flipPic, modelFlipPic), true);
  }

  @Test
  public void testHorizontalFlip() {
    Img flipPic = ImageType.makeImg(type, "flip", 4, 2);

    flipPic.setPixel(0, 1, ImageType.makePixel(type, 110, 115, 119));
    flipPic.setPixel(0, 0, ImageType.makePixel(type, 120, 125, 129));
    flipPic.setPixel(1, 1, ImageType.makePixel(type, 130, 135, 139));
    flipPic.setPixel(1, 0, ImageType.makePixel(type, 140, 145, 149));

    flipPic.setPixel(2, 1, ImageType.makePixel(type, 150, 155, 159));
    flipPic.setPixel(2, 0, ImageType.makePixel(type, 160, 165, 169));
    flipPic.setPixel(3, 1, ImageType.makePixel(type, 170, 175, 179));
    flipPic.setPixel(3, 0, ImageType.makePixel(type, 180, 185, 189));


    model.flip(Command.hflip, "littlePic", "hFlipPic");
    Img modelFlipPic = model.getImage("hFlipPic");
    assertEquals(contentsMatch(flipPic, modelFlipPic), true);
  }

  @Test
  public void testBrighten() throws IOException {
    Img brightLittlePic;
    brightLittlePic = ImageType.makeImg(type, "brightLittlePic", 4, 2);
    int brightenBy = 50;
    brightLittlePic.setPixel(0, 0, ImageType.makePixel(type,
            Math.min(110 + brightenBy, 255),
            Math.min(115 + brightenBy, 255),
            Math.min(119 + brightenBy, 255)));
    brightLittlePic.setPixel(0, 1, ImageType.makePixel(type,
            Math.min(120 + brightenBy, 255),
            Math.min(125 + brightenBy, 255),
            Math.min(129 + brightenBy, 255)));
    brightLittlePic.setPixel(1, 0, ImageType.makePixel(type,
            Math.min(130 + brightenBy, 255),
            Math.min(135 + brightenBy, 255),
            Math.min(139 + brightenBy, 255)));
    brightLittlePic.setPixel(1, 1, ImageType.makePixel(type,
            Math.min(140 + brightenBy, 255),
            Math.min(145 + brightenBy, 255),
            Math.min(149 + brightenBy, 255)));

    brightLittlePic.setPixel(2, 0, ImageType.makePixel(type,
            Math.min(150 + brightenBy, 255),
            Math.min(155 + brightenBy, 255),
            Math.min(159 + brightenBy, 255)));
    brightLittlePic.setPixel(2, 1, ImageType.makePixel(type,
            Math.min(160 + brightenBy, 255),
            Math.min(165 + brightenBy, 255),
            Math.min(169 + brightenBy, 255)));
    brightLittlePic.setPixel(3, 0, ImageType.makePixel(type,
            Math.min(170 + brightenBy, 255),
            Math.min(175 + brightenBy, 255),
            Math.min(179 + brightenBy, 255)));
    brightLittlePic.setPixel(3, 1, ImageType.makePixel(type,
            Math.min(180 + brightenBy, 255),
            Math.min(185 + brightenBy, 255),
            Math.min(189 + brightenBy, 255)));

    model.brighten(brightenBy, "littlePic", "alsoBrightLittlePic");
    assertEquals(contentsMatch(brightLittlePic, model.getImage("alsoBrightLittlePic")), true);
  }

  @Test
  public void testDarken() throws IOException {
    Img darkLittlePic;
    darkLittlePic = ImageType.makeImg(type, "darkLittlePic", 4, 2);
    int brightenBy = -120;
    darkLittlePic.setPixel(0, 0, ImageType.makePixel(type,
            Math.max(110 + brightenBy, 0),
            Math.max(115 + brightenBy, 0),
            Math.max(119 + brightenBy, 0)));
    darkLittlePic.setPixel(0, 1, ImageType.makePixel(type,
            Math.max(120 + brightenBy, 0),
            Math.max(125 + brightenBy, 0),
            Math.max(129 + brightenBy, 0)));
    darkLittlePic.setPixel(1, 0, ImageType.makePixel(type,
            Math.max(130 + brightenBy, 0),
            Math.max(135 + brightenBy, 0),
            Math.max(139 + brightenBy, 0)));
    darkLittlePic.setPixel(1, 1, ImageType.makePixel(type,
            Math.max(140 + brightenBy, 0),
            Math.max(145 + brightenBy, 0),
            Math.max(149 + brightenBy, 0)));

    darkLittlePic.setPixel(2, 0, ImageType.makePixel(type,
            Math.max(150 + brightenBy, 0),
            Math.max(155 + brightenBy, 0),
            Math.max(159 + brightenBy, 0)));
    darkLittlePic.setPixel(2, 1, ImageType.makePixel(type,
            Math.max(160 + brightenBy, 0),
            Math.max(165 + brightenBy, 0),
            Math.max(169 + brightenBy, 0)));
    darkLittlePic.setPixel(3, 0, ImageType.makePixel(type,
            Math.max(170 + brightenBy, 0),
            Math.max(175 + brightenBy, 0),
            Math.max(179 + brightenBy, 0)));
    darkLittlePic.setPixel(3, 1, ImageType.makePixel(type,
            Math.max(180 + brightenBy, 0),
            Math.max(185 + brightenBy, 0),
            Math.max(189 + brightenBy, 0)));

    model.brighten(brightenBy, "littlePic", "darkLittlePic");
    assertEquals(contentsMatch(darkLittlePic, model.getImage("darkLittlePic")), true);

  }


}
