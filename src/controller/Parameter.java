package controller;

import img.FileType;
import util.Tools;
import view.IGraphicsView;

/**
 * Represents all the possible parameter types, as well as methods to process parameters.
 */
public enum Parameter {
  increment,
  filePath,
  targetImage,
  destinationImage;


  /**
   * Checks if given String is a valid parameter, based on type of the Parameter calling the method.
   *
   * @param inp String representing a parameter
   * @return if is valid
   */
  public boolean isValidParameter(String inp) {
    switch (this) {
      case increment:
        return Tools.isNumeric(inp);
      case filePath:
        if (inp.length() > 4) {
          return (FileType.fileTypeOfPath(inp) != null);
        } else {
          return false;
        }
      case targetImage:
      case destinationImage:
        return Tools.isAlphabetic(inp);
      default:
        throw new IllegalArgumentException("Invalid parameter valued");
    }
  }

}
