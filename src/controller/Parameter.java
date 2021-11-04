package controller;

import util.Tools;

/**
 * Represents all the possible parameter types, as well as methods to process parameters.
 */
public enum Parameter {
  increment,
  PPMpath,
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
      case PPMpath:
        if (inp.length() > 4) {
          return inp.substring(inp.length() - 4, inp.length()).equals(".ppm");
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
