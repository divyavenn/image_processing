package controller_tests;

import org.junit.Test;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import controller.ImgController;
import controller.PPMController;
import img.Img;
import img.PPM;
import img.PPMPixel;
import model.ImgModel;
import model.PPMModel;
import view.ImgView;
import view.TextView;

import static org.junit.Assert.assertEquals;

public class ImgControllerTest {

  Img bigPic;
  public ImgControllerTest() throws IOException {
    bigPic = new PPM("big", 1080, 1080);
    int r, g, b;
    for (int h = 0; h < 1080; h++) {
      for (int w = 0; w < 1080; w++) {
        if (h < 180 && w < 180) {
          r = 255;
          g = 50;
          b = 0;
        } else if (h > 1000 && w > 1000) {
          r = 50;
          g = 255;
          b = 25;
        } else if (h > 800 && w < 200) {
          r = 0;
          g = 30;
          b = 255;
        } else {
          r = 255;
          g = 255;
          b = 255;
        }
        bigPic.setPixel(h, w, new PPMPixel(r, g, b));
      }
    }
    bigPic.save("image_processing/res/bigPic.ppm");
  }
  private String fullPathKoala(String name) {
    return " image_processing/res/koala" + name + " ";
  }

  private String fullPathBigPic(String name) {
    return " image_processing/res/koala" + name + " ";
  }

  private String fullPathLittlePic(String name) {
    return " image_processing/res/koala" + name + " ";
  }

  @Test
  public void runBigPicTest() throws IOException {
    ImgModel model = new PPMModel();
    Appendable out = new StringBuilder();
    Readable in = new StringReader("load" + fullPathBigPic("big.ppm") + "square "
            + "save square" + fullPathBigPic("another_square.ppm")
            + "\n brighten 50 square brightSquare "
            + "save brightSquare" + fullPathBigPic("bright_square.ppm")
            + "\n brighten -50 square darkSquare "
            + "save darkSquare" + fullPathBigPic("dark_square.ppm")
            + "vflip square upsidedownSquare "
            + "save upsidedownSquare" + fullPathBigPic("upside_down_square.ppm")
            + "hflip square mirrorSquare "
            + "save mirrorSquare" + fullPathBigPic("mirror_square.ppm")
            + "just-green square greenSquare "
            + "save greenSquare" + fullPathBigPic("green_square.ppm")
            + "just-blue square blueSquare "
            + "save blueSquare" + fullPathBigPic("blue_square.ppm")
            + "just-red square redSquare "
            + "save redSquare" + fullPathBigPic("red_square.ppm")
            + "just-value square valueSquare "
            + "save valueSquare" + fullPathBigPic("value_square.ppm")
            + "just-luma square lumaSquare "
            + "save lumaSquare" + fullPathBigPic("luma_square.ppm")
            + "just-intensity square intenseSquare "
            + "save intenseSquare" + fullPathBigPic("intense_square.ppm")
            + "hflip brightSquare flippedBrightSquare"
            +  "save flippedBrightSquare" + fullPathBigPic("flipped_bright_square.ppm")
            + "quit");

    try {
      out = new PrintStream(System.out, true, "UTF-8");
    } catch (UnsupportedEncodingException e) {

    }
    ImgView view = new TextView(model, out);
    ImgController controller = new PPMController(model, view, in);
    controller.start();
  }

  @Test
  public void runKoalaTest() throws IOException {
    ImgModel model = new PPMModel();
    Appendable out = new StringBuilder();
    Readable in = new StringReader("load" + fullPathKoala("koala.ppm") + "koala "
            + "save koala" + fullPathKoala("another_koala.ppm")
            + "\n brighten 50 koala brighterkoala "
            + "save koala" + fullPathKoala("another_koala.ppm")
            + "vflip koala upsidedownkoala "
            + "save upsidedownkoala" + fullPathKoala("upside_down_koala.ppm")
            + "hflip koala mirrorkoala "
            + "save mirrorkoala" + fullPathKoala("mirror_koala.ppm")
            + "just-green koala greenkoala "
            + "save greenkoala" + fullPathKoala("green_koala.ppm")
            + "just-blue koala bluekoala "
            + "save bluekoala" + fullPathKoala("blue_koala.ppm")
            + "just-red koala redkoala "
            + "save redkoala" + fullPathKoala("red_koala.ppm")
            + "just-value koala valuekoala "
            + "save valuekoala" + fullPathKoala("value_koala.ppm")
            + "just-luma koala lumakoala "
            + "save lumakoala" + fullPathKoala("luma_koala.ppm")
            + "just-intensity koala intensekoala "
            + "save intensekoala" + fullPathKoala("intense_koala.ppm")
            + "quit");

    try {
      out = new PrintStream(System.out, true, "UTF-8");
    } catch (UnsupportedEncodingException e) {

    }
    ImgView view = new TextView(model, out);
    ImgController controller = new PPMController(model, view, in);
    controller.start();
  }


}
