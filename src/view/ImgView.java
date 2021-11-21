package view;

import java.io.IOException;

/**
 * Provides tools to show output to user.
 */
public interface ImgView {

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission fails
   * @throws NullPointerException if message is null
   */
   void renderMessage(String message);
}
