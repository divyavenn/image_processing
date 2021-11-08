package controllertests;

import java.io.IOException;

import model.ImgModelImplementation;


/**
 * A model which always throws Exceptions.
 */
public class ExceptionThrowingModel extends ImgModelImplementation {

  @Override
  public void load(String filePath, String destinationImageName) throws IllegalArgumentException {
    throw new IllegalArgumentException();
  }

  @Override
  public void save(String formatName, String filePath, String destinationImageName) throws IOException {
    throw new IOException();
  }

}
