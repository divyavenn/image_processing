
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import controller.ImgController;
import controller.ImgControllerImplementation;
import model.ImgModel;
import model.ImgModelImplementation;
import view.ImgView;
import view.TextView;

/**
 * Class meant for running program using console input.
 */
public class TextMain {
  public static void main(String[] args) {
    ImgModel model = new ImgModelImplementation();
    Readable consoleIn = new InputStreamReader(System.in);
    Appendable out = new StringBuilder();
    try {
      out = new PrintStream(System.out, true, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      System.out.println("could not op.");
      e.printStackTrace();
    }
    ImgView tView = new TextView(model, out);
    ImgController controller = new ImgControllerImplementation(model, tView, consoleIn);
    controller.start();
  }
}
