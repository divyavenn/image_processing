package controllertests;

import java.io.IOException;
import model.PPMModel;


/**
 * A model which always throws Exceptions.
 */
public class ExceptionThrowingModel extends PPMModel {

  @Override
  public void load(String filePath, String destinationImageName) throws IllegalArgumentException {
    throw new IllegalArgumentException();
  }

  @Override
  public void save(String filePath, String destinationImageName) throws IOException {
    throw new IOException();
  }

}
