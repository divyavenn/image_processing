package controllertests;

import java.io.IOException;

import img.Img;
import model.Command;
import model.ImgModel;



/**
 * A model which always throws Exceptions.
 */
public class ExceptionThrowingModel implements ImgModel {

  @Override
  public void load(String filePath, String destinationImageName) throws IllegalArgumentException {
    throw new IllegalArgumentException();
  }

  @Override
  public void save(String filePath, String destinationImageName) throws IOException {
    throw new IOException();
  }

  @Override
  public void exportComponentByPixel(Command command,
                                     String imageName,
                                     String destinationImageName) {
    throw new IllegalArgumentException();
  }

  @Override
  public void brighten(int increment, String imageName, String destinationImageName) {
    throw new IllegalArgumentException();
  }

  @Override
  public void flip(Command command, String imageName, String destinationImageName) {
    throw new IllegalArgumentException();
  }

  @Override
  public Img getImage(String imageName) {
    return null;
  }

  @Override
  public void applyFilter(double[][] filter, String imageName, String destinationImageName) {
    throw new IllegalArgumentException();
  }

  @Override
  public void applyColorTransformation(double[][] matrix,
                                       String imageName,
                                       String destinationImageName) {
    throw new IllegalArgumentException();
  }

}
