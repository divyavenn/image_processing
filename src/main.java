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
  static Readable in = new StringReader("load image_processing/res/koala/koala.ppm koala " +
          "blur koala blurryKoala save blurryKoala image_processing/res/koala/blurrykoala.png " +
            " sharpen koala unblurrykoala save unblurrykoala " +
          "image_processing/res/koala/unblurrykoala.ppm " +
          "grey koala greykoala save greykoala image_processing/res/koala/greykoala.jpeg " +
          "sepia koala brownkoala save brownkoala image_processing/res/koala/brownkoala.png" +
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
