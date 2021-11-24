package controllertests;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import controller.Features;
import controller.GUIControllerImplementation;
import controller.ImgController;
import controller.ImgControllerImplementation;
import controller.Parameter;
import model.Command;
import model.ImgModel;
import view.GraphicsView;
import view.IGraphicsView;
import view.ImgView;
import view.TextView;

import static org.junit.Assert.assertEquals;

/**
 * Tests the GraphicView, GUIController test, and their integration.
 */
public class GUITests {
  GUIControllerImplementation controller;
  IGraphicsView mockView;
  ImgModel mockModel;
  IGraphicsView view;
  StringBuilder log;

  @Before
  public void init() {
    log = new StringBuilder();
    this.mockModel = new TestInputModel();
    this.mockView = new MockGuiView(mockModel, log);
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
    controller.setView(mockView);
    assertEquals("Features added!", log.toString());
  }

  /**
   * Controller.start should call view.setVisible
   */
  @Test
  public void testStartToSetVisibleInput() {
    controller.start();
    assertEquals("View is visible.", log.toString());

  }

  @Test
  public void testGetImageFromModel() {
    controller.getImageFromModel("Test Image");
    TestInputModel chattyModel = (TestInputModel)mockModel;
    assertEquals(chattyModel.recentInputs.get(0), "Test Image");

  }

  @Test
  public void testGetDoCommandsInput() {
    Map<Parameter, String> paramValues = new HashMap();
    for (Parameter p : Parameter.values()) {
      paramValues.put(p, null);
    }
    for (Command c : Command.values()) {
      controller.doCommand(c, paramValues);
      TestInputModel chattyModel = (TestInputModel) mockModel;
      assertEquals(c, chattyModel.recentlyCalled);
    }
  }
}
