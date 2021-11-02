package controller_tests;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import controller.ImgController;
import controller.PPMController;
import model.ImgModel;
import model.PPMModel;
import view.ImgView;
import view.TextView;

import static org.junit.Assert.assertEquals;
public class ImgControllerTest {


  @Test
  public void runTest() throws IOException {
    ImgModel model = new PPMModel();
    Appendable out = new StringBuilder();
    Readable in = new StringReader("load image_processing/images/koala.ppm koala "
            + "save koala image_processing/images/another_koala.ppm "
            + "\n brighten 50 koala brighterkoala "
            + "save brighterkoala image_processing/images/brighter_koala.ppm "
            + "quit");

    try {
      out = new PrintStream(System.out, true, "UTF-8");
    }
    catch (UnsupportedEncodingException e) {

    }
    ImgView view = new TextView(model, out);
    ImgController controller = new PPMController(model, view, in);
    controller.start();
  }


}
