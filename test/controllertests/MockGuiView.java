package controllertests;

import controller.Features;
import model.ImgModel;
import view.IGraphicsView;

public class MockGuiView implements IGraphicsView {
  private ImgModel model;
  public StringBuilder log;

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
