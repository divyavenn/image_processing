package controllertests;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import controller.GUIControllerImplementation;
import controller.Parameter;
import model.Command;
import model.ImgModel;
import view.IGraphicsView;

import static org.junit.Assert.assertEquals;

/**
 * Tests the GraphicView, GUIController test, and their integration.
 */
public class GUITests {
  GUIControllerImplementation controller;
  IGraphicsView mockView;
  ImgModel mockModel;
  StringBuilder commandLog;
  StringBuilder inputLog;
  StringBuilder viewLog;

  @Before
  public void init() {
    commandLog = new StringBuilder();
    inputLog = new StringBuilder();
    viewLog = new StringBuilder();
    this.mockModel = new MockGuiModel(commandLog, inputLog);
    this.mockView = new MockGuiView(viewLog);
    this.controller = new GUIControllerImplementation(mockModel, mockView);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerConstructorNullModel() {
    mockModel = null;
    controller = new GUIControllerImplementation(mockModel, mockView);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerConstructorNullView() {
    mockView = null;
    controller = new GUIControllerImplementation(mockModel, mockView);
  }

  /**
   * Controller.setView should call view.AddFeatures
   */
  @Test
  public void testSetViewToAddFeaturesInput() {
    controller.setView();
    assertEquals("Features added!", viewLog.toString());
  }

  /**
   * Controller.start should call view.setVisible
   */
  @Test
  public void testStartToSetVisibleInput() {
    controller.start();
    assertEquals("View is visible.", viewLog.toString());

  }

  @Test
  public void testGetImageFromModel() {
    controller.getImageFromModel("Test Image");
    assertEquals(inputLog.toString(), "Test Image");

  }

  @Test
  public void testGetDoCommandsInput() {
    StringBuilder tempLog = new StringBuilder();
    Map<Parameter, String> paramValues = new HashMap();
    for (Parameter p : Parameter.values()) {
      paramValues.put(p, null);
    }
    paramValues.put(Parameter.increment, "10");
    paramValues.put(Parameter.destinationImage, "hi");
    paramValues.put(Parameter.targetImage, "hi");
    paramValues.put(Parameter.filePath,
            "C:\\Users\\Brandon's Computer\\Desktop\\" +
                    "OOD\\img\\image_processing\\res\\happyBright.png");


    for (Command c : Command.values()) {
      controller.doCommand(c, paramValues);
      tempLog.append(c);
    }
    // quit is in command enum but not defined in model
    commandLog.append("quit");
    assertEquals(commandLog.toString(), tempLog.toString());
  }
}