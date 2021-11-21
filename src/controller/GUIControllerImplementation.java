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
  }

  public void resetParams(){
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
    resetParams();
    for (Parameter p: Parameter.values()) {
      if (Command.needsParam(c.toString(),p)) {
        view.inputBox("Enter the "  + p.toString());
        while (view.getMostRecentInput() == null ){

        }
        paramValues.put(p,view.getMostRecentInput());
        view.resetMostRecentInput();
      }
    }
    view.textBox(c.acknowledge(paramValues));
  }
}
