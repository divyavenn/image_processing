package controllertests;

import controller.Features;
import view.IGraphicsView;

/**
 * A mock view that implements the IGraphicsView interface. Meant for testing integration between
 * the view and the controller.
 */
public class MockGuiView implements IGraphicsView {
  private StringBuilder log;

  /**
   * Constructor for a MockGuiView.
   *
   * @param log A StringBuilder for the log.
   */
  public MockGuiView(StringBuilder log) {
    this.log = log;
    try {
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
