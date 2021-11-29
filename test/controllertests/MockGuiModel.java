package controllertests;

import java.io.IOException;

import img.Img;
import model.Command;
import model.ImgModel;

/**
 * A mock Model which provides access
 * to the command most recently called, as well as its inputs.
 */
public class MockGuiModel implements ImgModel {

  StringBuilder recentlyCalled;
  StringBuilder recentInputs;


  /**
   * Constructs a MockGuiModel with the given StringBuilders.
   *
   * @param recentlyCalled A StringBuilder that appends the recently called commands.
   * @param recentInputs   A StringBuilder that appends the inputs called.
   */
  public MockGuiModel(StringBuilder recentlyCalled, StringBuilder recentInputs) {
    this.recentlyCalled = recentlyCalled;
    this.recentInputs = recentInputs;
  }

  @Override
  public void load(String filePath, String destinationImageName) {
    recentlyCalled.append(Command.load);
    recentInputs.append(filePath);
    recentInputs.append(destinationImageName);
  }

  @Override
  public void save(String filePath, String destinationImageName) throws IOException {
    recentlyCalled.append(Command.save);
    recentInputs.append(filePath);
    recentInputs.append(destinationImageName);
  }

  @Override
  public void exportComponentByPixel(Command command,
                                     String imageName,
                                     String destinationImageName) {
    recentlyCalled.append(command);
    recentInputs.append(imageName);
    recentInputs.append(destinationImageName);
  }

  @Override
  public void brighten(int increment, String imageName, String destinationImageName) {
    recentlyCalled.append(Command.brighten);
    recentInputs.append(imageName);
    recentInputs.append(increment + "");
    recentInputs.append(destinationImageName);

  }

  @Override
  public void flip(Command command, String imageName, String destinationImageName) {
    recentlyCalled.append(command);
    recentInputs.append(imageName);
    recentInputs.append(destinationImageName);
  }

  @Override
  public Img getImage(String imageName) {
    recentInputs.append(imageName);
    return null;
  }

  @Override
  public void applyFilter(double[][] filter, String imageName, String destinationImageName) {
    if (filter == Command.blurFilter) {
      recentlyCalled.append("blur");
    }
    if (filter == Command.sharpenFilter) {
      recentlyCalled.append("sharpen");
    }
    recentInputs.append(imageName);
    recentInputs.append(destinationImageName);
  }

  @Override
  public void applyColorTransformation(double[][] matrix,
                                       String imageName,
                                       String destinationImageName) {
    if (matrix == Command.greyTransform) {
      recentlyCalled.append("grey");
    }
    if (matrix == Command.greyTransform) {
      recentlyCalled.append("sepia");
    }
    recentInputs.append(imageName);
    recentInputs.append(destinationImageName);
  }
}
