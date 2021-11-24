package controllertests;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import controller.Features;
import controller.GUIControllerImplementation;
import controller.ImgController;
import controller.ImgControllerImplementation;
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
    this.controller = new GUIControllerImplementation(mockModel);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerConstructorNullModel() {
    mockModel = null;
    controller = new GUIControllerImplementation(mockModel);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerConstructorNullView() {
    mockView = null;
    controller = new GUIControllerImplementation(mockModel);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGraphicsViewControllerNullModel() {
    mockModel = null;
    //view = new GraphicsView(mockModel);
  }

  /**
   * Controller.setView should call view.AddFeatures
   */
  @Test
  public void testSetViewToAddFeaturesInput() {
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
  public void testGetDoCommandsInput() {

  }

  /////EVERYTHING BELOW HERE IS NOT DONE


  /**
   * Tests that correct method is called with given hypothetical model input.
   *
   * @param entry          hypothetical model input
   * @param correctCommand the correct command
   * @return true if correct method is called
   * @throws IOException if readable or appendable cannot be created
   */
  private boolean calledCorrectMethod(String entry, Command correctCommand) throws IOException {
    TestInputModel chattyModel = new TestInputModel();
    Appendable out = new StringBuilder();
    Readable in = new StringReader(entry);
    ImgView view = new TextView(chattyModel, out);
    ImgController controller = new ImgControllerImplementation(chattyModel, view, in);
    controller.start();
    return chattyModel.getRecentlyCalled().equals(correctCommand);
  }

  /**
   * Tests that correct inputs are given to Model with given hypothetical model input.
   *
   * @param entry         hypothetical model input
   * @param correctInputs a list of the intended inputs
   * @return true if all the intended inputs, but no more, were given to the model.
   * @throws IOException if readable or appendable cannot be created
   */
  private boolean gaveCorrectInputs(String entry, String[] correctInputs)
          throws IOException {
    boolean allInputs = true;
    controller.start();
    TestInputModel chattyModel = (TestInputModel)mockModel;
    ArrayList<String> actualInputs = chattyModel.getRecentInputs();
    for (String shouldHaveGotInput : correctInputs) {
      //if the acct
      if (actualInputs.contains(shouldHaveGotInput)) {
        allInputs = allInputs & true;
        actualInputs.remove(shouldHaveGotInput);
      }
    }
    //the method got no other inputs than it should have.
    allInputs = allInputs & (actualInputs.size() == 0);
    return allInputs;
  }


  /**
   * Ensures that multiple ways of entering commands all result
   * in correct command and inputs.
   *
   * @param entries        multiple ways of entering same command and inputs
   * @param correctCommand the correct Command
   * @param correctInputs  the correct inputs
   * @return if all are transmitted to model correctly
   * @throws IOException if readable or appendable cannot be created
   */
  public boolean multipleSyntaxesWork(Command correctCommand,
                                      String[] correctInputs,
                                      String... entries) throws IOException {
    boolean allSyntaxesWork = true;
    for (String entry : entries) {
      allSyntaxesWork = allSyntaxesWork
              && calledCorrectMethod(entry, correctCommand)
              && gaveCorrectInputs(entry, correctInputs);
    }
    return allSyntaxesWork;
  }

  @Test
  public void transmitsAllCommandsMultipleSyntaxesCorrectly() throws IOException {
    assertEquals(multipleSyntaxesWork(Command.save,
            new String[]{"koala.png", "koala"},
            "save koala.png koala",
            "save koala koala.png"), true);


    assertEquals(multipleSyntaxesWork(Command.load,
            new String[]{"koala.ppm", "koala"},
            "load koala.ppm koala",
            "load koala koala.ppm"), true);

    assertEquals(multipleSyntaxesWork(Command.brighten,
            new String[]{"koala", "brighterkoala", "10"},
            "brighten koala brighterkoala 10",
            "brighten koala 10 brighterkoala",
            "brighten 10 koala brighterkoala"), true);

    assertEquals(multipleSyntaxesWork(Command.vflip,
            new String[]{"koala", "vKoala"},
            "vflip koala vKoala"), true);

    assertEquals(multipleSyntaxesWork(Command.hflip,
            new String[]{"koala", "hKoala"},
            "hflip koala hKoala"), true);

    assertEquals(multipleSyntaxesWork(Command.rc,
            new String[]{"koala", "newKoala"},
            "just-red koala newKoala"), true);

    assertEquals(multipleSyntaxesWork(Command.gc,
            new String[]{"koala", "newKoala"},
            "just-green koala newKoala"), true);

    assertEquals(multipleSyntaxesWork(Command.bc,
            new String[]{"koala", "newKoala"},
            "just-blue koala newKoala"), true);

    assertEquals(multipleSyntaxesWork(Command.vc,
            new String[]{"koala", "newKoala"},
            "just-value koala newKoala"), true);

    assertEquals(multipleSyntaxesWork(Command.lc,
            new String[]{"koala", "newKoala"},
            "just-luma koala newKoala"), true);

    assertEquals(multipleSyntaxesWork(Command.ic,
            new String[]{"koala", "newKoala"},
            "just-intensity koala newKoala"), true);


  }
}
