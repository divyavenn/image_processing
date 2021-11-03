package controller_tests;

import java.io.IOException;

import model.Command;
import model.ImgModel;

public class TestInputModel implements ImgModel{

  String recentlyCalled;

  public String getRecentlyCalled(){
    return recentlyCalled;
  }
  @Override
  public void load(String filePath, String destinationImageName) {
    recentlyCalled = ("load," + filePath + "," + destinationImageName);
  }

  @Override
  public void save(String filePath, String destinationImageName) throws IOException {
    recentlyCalled = ("save," + filePath + "," + destinationImageName);
  }

  @Override
  public void exportComponentByPixel(Command command, String imageName, String destinationImageName) {
    recentlyCalled = (command.toString() + "," + imageName + "," + destinationImageName);
  }

  @Override
  public void brighten(int increment, String imageName, String destinationImageName) {
    recentlyCalled = ("brighten" + "," + increment + "," + "," + destinationImageName);

  }

  @Override
  public void flip(Command command, String imageName, String destinationImageName) {
    recentlyCalled = (command.toString() + "," + imageName + "," + destinationImageName);
  }
}
