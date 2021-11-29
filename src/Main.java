
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
public class Main {
  static ImgModel annoyingModel = new ImgModelImplementation();
  static Appendable out;

  static {

    try {
      out = new PrintStream(System.out, true, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }

  private static String getfPath(String fileName) {
    return " image_processing/res/koala/" + fileName + ".ppm";
  }

  private static String getName(String adjective) {
    return adjective + "pic";
  }

  private static String getSave(String imageName) {
    return "save " + imageName + getfPath(imageName);
  }

  private static String makeReadable() {
    return "load image_processing/res/koala/koala.ppm " + getName("")
            + "\n"
            + "sepia " + getName("") + " " + getName("") + "\n"
            + "save pic image_processing/res/koala/sepiaKoala.png\n"
            + "quit";
  }

  static Readable in = new StringReader(makeReadable());
  //static Readable in = new InputStreamReader(System.in);
  static ImgView view = new TextView(annoyingModel, out);
  static ImgController controller = new ImgControllerImplementation(annoyingModel, view, in);

  public static void main(String[] args) {
    controller.start();
  }
}
