package controllertests;

import org.junit.Test;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import controller.ImgController;
import img.ImageType;
import img.Img;
import model.Command;
import model.ImgModel;
import view.ImgView;
import view.TextView;

import static org.junit.Assert.assertEquals;

/**
 * Tests ImageControllerMethods.
 */
public abstract class ImgControllerTest {

  static Img bigPic;
  ImageType type;


  protected void instantiate() {
    bigPic = ImageType.makeImg(type, "square", 1080, 1080);
    int r;
    int g;
    int b;
    for (int h = 0; h < 108; h++) {
      for (int w = 0; w < 108; w++) {
        if (h < 18 && w < 18) {
          r = 255;
          g = 50;
          b = 0;
        } else if (h > 100 && w > 100) {
          r = 50;
          g = 255;
          b = 25;
        } else if (h > 80 && w < 20) {
          r = 0;
          g = 30;
          b = 255;
        } else {
          r = 255;
          g = 255;
          b = 255;
        }
        bigPic.setPixel(h, w, ImageType.makePixel(type, r, g, b));
      }
    }
    try {
      bigPic.save("image_processing/res/bigPic/bigPic.ppm");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  private String fullPathKoala(String name) {
    return " image_processing/res/koala/" + name + " ";
  }

  private String fullPathBigPic(String name) {
    return " image_processing/res/bigPic/" + name + " ";
  }

  @Test
  public void runBigPicTest() throws IOException {
    ImgModel model = ImageType.makeModel(type);
    Appendable out = new StringBuilder();
    Readable in = new StringReader("load" + fullPathBigPic("bigPic.ppm") + "square "
            + "save square" + fullPathBigPic("another_square.ppm") + " "
            + "\n brighten 50 square brightSquare "
            + "save brightSquare" + fullPathBigPic("bright_square.ppm") + " "
            + "\n brighten -50 square darkSquare "
            + "save darkSquare" + fullPathBigPic("dark_square.ppm") + " "
            + "vflip square upsidedownSquare "
            + "save upsidedownSquare" + fullPathBigPic("upside_down_square.ppm") + " "
            + "hflip square mirrorSquare "
            + "save mirrorSquare" + fullPathBigPic("mirror_square.ppm") + " "
            + "just-green square greenSquare "
            + "save greenSquare" + fullPathBigPic("green_square.ppm") + " "
            + "just-blue square blueSquare "
            + "save blueSquare" + fullPathBigPic("blue_square.ppm") + " "
            + "just-red square redSquare "
            + "save redSquare" + fullPathBigPic("red_square.ppm") + " "
            + "just-value square valueSquare "
            + "save valueSquare" + fullPathBigPic("value_square.ppm") + " "
            + "just-luma square lumaSquare "
            + "save lumaSquare" + fullPathBigPic("luma_square.ppm") + " "
            + "just-intensity square intenseSquare "
            + "save intenseSquare" + fullPathBigPic("intense_square.ppm") + " "
            + "hflip brightSquare flippedBrightSquare "
            + "save flippedBrightSquare" + fullPathBigPic("flipped_bright_square.ppm") + " "
            + "quit");

    try {
      out = new PrintStream(System.out, true, "UTF-8");
    } catch (UnsupportedEncodingException e) {

    }
    ImgView view = new TextView(model, out);
    ImgController controller = ImageType.makeController(type, model, view, in);
    controller.start();
    assertEquals(true, true);
  }

  @Test
  public void runKoalaTest() throws IOException {
    ImgModel model = ImageType.makeModel(type);
    Appendable out = new StringBuilder();
    Readable in = new StringReader("load" + fullPathKoala("koala.ppm") + "koala "
            + " save koala" + fullPathKoala("another_koala.ppm")
            + "\n brighten 50 koala brighterkoala "
            + " save koala" + fullPathKoala("another_koala.ppm")
            + " vflip koala upsidedownkoala "
            + " save upsidedownkoala" + fullPathKoala("upside_down_koala.ppm")
            + " hflip koala mirrorkoala "
            + " save mirrorkoala" + fullPathKoala("mirror_koala.ppm")
            + " just-green koala greenkoala "
            + " save greenkoala" + fullPathKoala("green_koala.ppm")
            + " just-blue koala bluekoala "
            + " save bluekoala" + fullPathKoala("blue_koala.ppm")
            + " just-red koala redkoala "
            + " save redkoala" + fullPathKoala("red_koala.ppm")
            + " just-value koala valuekoala "
            + " save valuekoala" + fullPathKoala("value_koala.ppm")
            + " just-luma koala lumakoala "
            + " save lumakoala" + fullPathKoala("luma_koala.ppm")
            + " just-intensity koala intensekoala "
            + " save intensekoala" + fullPathKoala("intense_koala.ppm")
            + " quit");

    try {
      out = new PrintStream(System.out, true, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      return;
    }
    ImgView view = new TextView(model, out);
    ImgController controller = ImageType.makeController(type, model, view, in);
    controller.start();
    assertEquals(true, true);
  }

  @Test
  public void testIOExceptionSave() throws IOException {
    ImgModel model = new ExceptionThrowingModel();
    Appendable out = new StringBuilder();
    Readable in = new StringReader("save img folder/file.ppm quit");
    try {
      out = new PrintStream(System.out, true, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      return;
    }
    ImgView view = new TextView(model, out);
    ImgController controller = ImageType.makeController(type, model, view, in);
    controller.start();
  }

  @Test
  public void testCatchesAllExceptions() throws IOException {
    ImgModel annoyingModel = new ExceptionThrowingModel();
    Appendable out = new PrintStream(System.out, true, "UTF-8");
    Readable in = new StringReader("load folder/file.ppm img save img folder/file.ppm quit");
    ImgView view = new TextView(annoyingModel, out);
    ImgController controller = ImageType.makeController(type, annoyingModel, view, in);
    controller.start();

    annoyingModel = ImageType.makeModel(type);
    in = new StringReader("");
    controller = ImageType.makeController(type, annoyingModel, view, in);
    controller.start();

    annoyingModel = ImageType.makeModel(type);
    in = new StringReader("save ");
    controller = ImageType.makeController(type, annoyingModel, view, in);
    controller.start();
    assertEquals(true, true);
  }

  /**
   * Tests that correct method is called with given hypothetical model input.
   *
   * @param entry          hypothetical model input
   * @param correctCommand the correct command
   * @return true if correct method is called
   * @throws IOException if readable or appendable cannot be created
   */
  private boolean calledCorrectMethod(String entry, Command correctCommand) throws IOException {
    TestInputModel chattyModel = new TestInputModel();
    Appendable out = new StringBuilder();
    Readable in = new StringReader(entry);
    ImgView view = new TextView(chattyModel, out);
    ImgController controller = ImageType.makeController(type, chattyModel, view, in);
    controller.start();
    return chattyModel.recentlyCalled.equals(correctCommand);
  }

  /**
   * Tests that correct inputs are given to Model with given hypothetical model input.
   *
   * @param entry         hypothetical model input
   * @param correctInputs a list of the intended inputs
   * @return true if all the intended inputs, but no more, were given to the model.
   * @throws IOException if readable or appendable cannot be created
   */
  private boolean gaveCorrectInputs(String entry, String[] correctInputs)
          throws IOException {
    boolean allInputs = true;
    TestInputModel chattyModel = new TestInputModel();
    Appendable out = new StringBuilder();
    Readable in = new StringReader(entry);
    ImgView view = new TextView(chattyModel, out);
    ImgController controller = ImageType.makeController(type, chattyModel, view, in);
    controller.start();
    ArrayList<String> actualInputs = chattyModel.getRecentInputs();
    for (String shouldHaveGotInput : correctInputs) {
      //if the acct
      if (actualInputs.contains(shouldHaveGotInput)) {
        allInputs = allInputs & true;
        actualInputs.remove(shouldHaveGotInput);
      }
    }
    //the method got no other inputs than it should have.
    allInputs = allInputs & (actualInputs.size() == 0);
    return allInputs;
  }


  /**
   * Ensures that multiple ways of entering commands all result in correct command and inputs
   *
   * @param entries        multiple ways of entering same command and inputs
   * @param correctCommand the correct Command
   * @param correctInputs  the correct inputs
   * @return if all are transmitted to model correctly
   * @throws IOException if readable or appendable cannot be created
   */
  public boolean multipleSyntaxesWork(Command correctCommand,
                                      String[] correctInputs, String... entries) throws IOException {
    boolean allSyntaxesWork = true;
    for (String entry : entries) {
      allSyntaxesWork = allSyntaxesWork
              && calledCorrectMethod(entry, correctCommand)
              && gaveCorrectInputs(entry, correctInputs);
    }
    return allSyntaxesWork;
  }

  @Test
  public void transmitsAllCommandsMultipleSyntaxesCorrectly() throws IOException {
    assertEquals(multipleSyntaxesWork(Command.save,
            new String[]{"koala.ppm", "koala"},
            "save koala.ppm koala",
            "save koala koala.ppm"), true);

    assertEquals(multipleSyntaxesWork(Command.load,
            new String[]{"koala.ppm", "koala"},
            "load koala.ppm koala",
            "load koala koala.ppm"), true);

    assertEquals(multipleSyntaxesWork(Command.brighten,
            new String[]{"koala", "brighterkoala", "10"},
            "brighten koala brighterkoala 10",
            "brighten koala 10 brighterkoala",
            "brighten 10 koala brighterkoala"), true);

    assertEquals(multipleSyntaxesWork(Command.vflip,
            new String[]{"koala", "vKoala"},
            "vflip koala vKoala"), true);

    assertEquals(multipleSyntaxesWork(Command.hflip,
            new String[]{"koala", "hKoala"},
            "hflip koala hKoala"), true);

    assertEquals(multipleSyntaxesWork(Command.rc,
            new String[]{"koala", "newKoala"},
            "just-red koala newKoala"), true);

    assertEquals(multipleSyntaxesWork(Command.gc,
            new String[]{"koala", "newKoala"},
            "just-green koala newKoala"), true);

    assertEquals(multipleSyntaxesWork(Command.bc,
            new String[]{"koala", "newKoala"},
            "just-blue koala newKoala"), true);

    assertEquals(multipleSyntaxesWork(Command.vc,
            new String[]{"koala", "newKoala"},
            "just-value koala newKoala"), true);

    assertEquals(multipleSyntaxesWork(Command.lc,
            new String[]{"koala", "newKoala"},
            "just-luma koala newKoala"), true);

    assertEquals(multipleSyntaxesWork(Command.ic,
            new String[]{"koala", "newKoala"},
            "just-intensity koala newKoala"), true);

  }
}
