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

public class main {
  static ImgModel annoyingModel = new ImgModelImplementation();
  static Appendable out;
  static {
    try {
      out = new PrintStream(System.out, true, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }

  private static String fPath = " image_processing/res/littlePic/";
  private static String getName(String adjective) {
    return " " + adjective + "pic ";
  }
  static Readable in = new StringReader(
          "load " + fPath + "littlePic.ppm" + getName("") + "\n" +
          "blur " + getName("") + getName("blurry") + "\n" +
            " sharpen koala unblurrykoala save unblurrykoala " +
          "image_processing/res/koala/unblurrykoala.ppm " +
          "grey koala greykoala save greykoala image_processing/res/koala/greykoala.jpeg " +
          "sepia koala brownkoala save image_processing/res/koala/brownkoala.png brownkoala " +
          "quit");
  //static Readable in = new InputStreamReader(System.in);
  static ImgView view = new TextView(annoyingModel, out);
  static ImgController controller = new ImgControllerImplementation(annoyingModel, view, in);

  public main() throws UnsupportedEncodingException {
  }

  public static void main (String[] args){
    controller.start();
  }
}
