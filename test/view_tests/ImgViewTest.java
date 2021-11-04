package view_tests;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

import model.ImgModel;
import model.PPMModel;
import view.ImgView;
import view.TextView;

/**
 * Class to Test Image View
 */
public class ImgViewTest {
  ImgView view;
  Appendable out;

  public ImgViewTest() {
    out = new StringBuilder();
    ImgModel model = new PPMModel();
    view = new TextView(model, out);
  }

  @Test
  public void testModel() {
    view.renderMessage("Message");
    assertEquals(out.toString(), "Message");
  }

  @Test(expected = NullPointerException.class)
  public void testNull() {
    view.renderMessage(null);
  }

  @Test
  public void catchesIOException() {
    out = new BadAppendable();
    ImgModel model = new PPMModel();
    view = new TextView(model, out);
    view.renderMessage("Valid message");
  }
}
