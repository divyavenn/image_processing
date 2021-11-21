package controller;

import java.util.HashMap;
import java.util.Map;

import model.Command;
import model.ImgModel;
import view.GraphicsView;
import view.IGraphicsView;

public class GUIControllerImplementation implements ImgController, Features{
  private ImgModel model;
  private GraphicsView view;
  Map<Parameter, String> paramValues = new HashMap();
  public GUIControllerImplementation(ImgModel model) {
    this.model = model;
    for (Parameter p : Parameter.values()) {
      paramValues.put(p, null);
    }
  }

  public void setView(GraphicsView v) {
    view = v;
    view.addFeatures(this);
  }

  @Override
  public void start() {

  }

  @Override
  public void doCommand(Command c) {
    if (view == null) throw new IllegalArgumentException("Null view");
    if (c == null) throw new IllegalArgumentException("Not a valid command");
    view.textBox(c.acknowledge(paramValues));
  }
}
