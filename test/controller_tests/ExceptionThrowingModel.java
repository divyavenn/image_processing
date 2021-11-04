package controller_tests;

import java.io.IOException;

import img.Img;
import model.Command;
import model.ImgModel;


/**
 * A model which always throws Exceptions.
 */
public class ExceptionThrowingModel implements ImgModel {

  public ExceptionThrowingModel() {
  }

  @Override
  public void load(String filePath, String destinationImageName) throws IllegalArgumentException {
    throw new IllegalArgumentException();
  }

  @Override
  public void save(String filePath, String destinationImageName) throws IOException {
    throw new IOException();
  }

  @Override
  public void exportComponentByPixel(Command command, String imageName, String destinationImageName) {

  }

  @Override
  public void brighten(int increment, String imageName, String destinationImageName) {

  }

  @Override
  public void flip(Command command, String imageName, String destinationImageName) {

  }

  @Override
  public Img getImage(String imageName) {
    return null;
  }
}
