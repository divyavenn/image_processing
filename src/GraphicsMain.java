

import controller.GUIControllerImplementation;

import model.ImgModel;
import model.ImgModelImplementation;
import view.GraphicsView;
import view.IGraphicsView;


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
    IGraphicsView view = new GraphicsView(model);
    GUIControllerImplementation controller = new GUIControllerImplementation(model, view);
    controller.setView();
    controller.start();
  }

}
