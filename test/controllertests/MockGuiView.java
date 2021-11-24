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
  @Override
  public void addFeatures() {
    log.append("Features added!");

  }

  @Override
  public void setVisible() {
    log.append("View is visible.");

  }

  @Override
  public void errorMessage(String msg) {
    log.append(msg);
  }
}
