import java.io.PrintStream;
import java.io.StringReader;

import controller.ImgController;
import img.ImageType;
import model.ImgModel;
import model.PPMModel;
import view.ImgView;
import view.TextView;

public class main {
  ImgModel annoyingModel = new PPMModel();
  Appendable out = new PrintStream(System.out, true, "UTF-8");
  Readable in = new StringReader("load folder/file.ppm img save img folder/file.ppm quit");
  ImgView view = new TextView(annoyingModel, out);
  ImgController controller = ImageType.makeController(type, annoyingModel, view, in);
    controller.start();
}
