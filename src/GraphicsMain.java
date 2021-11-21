import java.io.PrintStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import controller.GUIControllerImplementation;
import controller.ImgController;
import controller.ImgControllerImplementation;
import model.ImgModel;
import model.ImgModelImplementation;
import view.GraphicsView;
import view.IGraphicsView;
import view.ImgView;
import view.TextView;

/**
 * Class meant for running program using graphics input.
 */
public class GraphicsMain {

  public static void main(String[] args) {
    ImgModel model = new ImgModelImplementation();
    GUIControllerImplementation controller = new GUIControllerImplementation(model);
    GraphicsView view = new GraphicsView(model);
    controller.setView(view);
    controller.start();
  }

}
