package view;

import java.io.IOException;

public interface ImgView {

  /**
   * Render a specific message to the provided data destination.
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   * @throws NullPointerException if message is null
   */
  void renderMessage(String message);
}
