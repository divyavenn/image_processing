package view_tests;

import java.io.IOException;

/**
 * Appendable that always outputs an exception.
 */
public class BadAppendable implements Appendable {

  /**
   * Constructs a BadAppendable.
   */
  public BadAppendable() {
  }

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("");
  }
}
