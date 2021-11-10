package controllertests;

import java.io.IOException;
import java.util.ArrayList;

import img.Img;
import model.Command;
import model.ImgModel;

/**
 * A mock Model which provides access
 * to the command most recently called, as well as its inputs.
 */
public class TestInputModel implements ImgModel {

  Command recentlyCalled;
  ArrayList<String> recentInputs;

  /**
   * Get most recently called command.
   *
   * @return command.
   */
  public Command getRecentlyCalled() {
    return recentlyCalled;
  }

  /**
   * Get the most recently given inputs.
   *
   * @return inputs in an Arraylist.
   */
  public ArrayList<String> getRecentInputs() {
    return recentInputs;
  }

  @Override
  public void load(String filePath, String destinationImageName) {
    recentlyCalled = Command.load;
    recentInputs = new ArrayList<String>();
    recentInputs.add(filePath);
    recentInputs.add(destinationImageName);
  }

  @Override
  public void save(String filePath, String destinationImageName) throws IOException {
    recentlyCalled = Command.save;
    recentInputs = new ArrayList<String>();
    recentInputs.add(filePath);
    recentInputs.add(destinationImageName);
  }

  @Override
  public void exportComponentByPixel(Command command,
                                     String imageName,
                                     String destinationImageName) {
    recentlyCalled = command;
    recentInputs = new ArrayList<String>();
    recentInputs.add(imageName);
    recentInputs.add(destinationImageName);
  }

  @Override
  public void brighten(int increment, String imageName, String destinationImageName) {
    recentlyCalled = Command.brighten;
    recentInputs = new ArrayList<String>();
    recentInputs.add(imageName);
    recentInputs.add(increment + "");
    recentInputs.add(destinationImageName);

  }

  @Override
  public void flip(Command command, String imageName, String destinationImageName) {
    recentlyCalled = command;
    recentInputs = new ArrayList<String>();
    recentInputs.add(imageName);
    recentInputs.add(destinationImageName);
  }

  @Override
  public Img getImage(String imageName) {
    return null;
  }

  @Override
  public void applyFilter(double[][] filter, String imageName, String destinationImageName) {
    recentInputs = new ArrayList<String>();
    recentInputs.add(imageName);
    recentInputs.add(destinationImageName);
  }

  @Override
  public void applyColorTransformation(double[][] matrix,
                                       String imageName,
                                       String destinationImageName) {
    recentInputs = new ArrayList<String>();
    recentInputs.add(imageName);
    recentInputs.add(destinationImageName);
  }
}
