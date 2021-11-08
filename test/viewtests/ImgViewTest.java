package viewtests;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

import model.ImgModel;
import model.ImgModelImplementation;
import view.ImgView;
import view.TextView;

/**
 * Class to Test Image View.
 */
public class ImgViewTest {
  ImgView view;
  Appendable out;

  /**
   * Constructs ImageViewTestObect.
   */
  public ImgViewTest() {
    out = new StringBuilder();
    ImgModel model = new ImgModelImplementation();
    view = new TextView(model, out);
  }

  @Test
  public void testModel() {
    view.renderMessage("Message");
    assertEquals(out.toString(), "Message");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNull() {
    view.renderMessage(null);
  }

  @Test
  public void catchesIOException() {
    out = new BadAppendable();
    ImgModel model = new ImgModelImplementation();
    view = new TextView(model, out);
    view.renderMessage("Valid message");
    assertEquals(model != null, true);
  }
}
