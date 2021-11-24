

import controller.GUIControllerImplementation;

import model.ImgModel;
import model.ImgModelImplementation;
import view.GraphicsView;


/**
 * Class meant for running program using graphics input.
 */
public class GraphicsMain {

  /**
   * Runs the image processor with graphical interface.
   * @param args
   */
  public static void main(String[] args) {
    ImgModel model = new ImgModelImplementation();
    GUIControllerImplementation controller = new GUIControllerImplementation(model);
    GraphicsView view = new GraphicsView(model);
    controller.setView(view);
    controller.start();
  }

}
