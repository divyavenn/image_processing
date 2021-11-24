package controllertests;

import controller.Features;
import model.ImgModel;
import view.IGraphicsView;

/**
 * A mock view that implements the IGraphicsView interface. Meant for testing integration between
 * the view and the controller.
 */
public class MockGuiView implements IGraphicsView {
  private ImgModel model;
  private StringBuilder log;

  /**
   * Constructor for a MockGuiView
   * @param model An ImgModel.
   * @param log A StringBuilder for the log.
   */
  public MockGuiView(ImgModel model, StringBuilder log) {
    this.log = log;
    try {
      this.model = model;
    } catch (NullPointerException e) {
      log.append("Model is null");
    }
  }

  public void addFeatures(Features features) {
    log.append("Features added!");

  }


  public void setVisible() {
    log.append("View is visible.");

  }

  public void errorMessage(String msg) {
    log.append(msg);
  }
}
