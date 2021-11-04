package controller;

/**
 * Represents all the possible parameter types, as well as methods to process parameters.
 */
public enum Parameter {
  increment,
  PPMpath,
  targetImage,
  destinationImage;

  /**
   * Checks if given String is purely alphabetic.
   *
   * @param inp String representing a parameter
   * @return if is alphabetic
   */
  private boolean isAlphabetic(String inp) {
    for (int i = 0; i != inp.length(); ++i) {
      if (!Character.isLetter(inp.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks if given String is purely numeric.
   *
   * @param inp String representing a parameter
   * @return if is numeric
   */
  private boolean isNumeric(String inp) {
    try {
      Integer.parseInt(inp);
      return true;
    } catch (NumberFormatException e) {
      // s is not numeric
      return false;
    }
  }


  /**
   * Checks if given String is a valid parameter, based on type of the Parameter calling the method.
   *
   * @param inp String representing a parameter
   * @return if is valid
   */
  public boolean isValidParameter(String inp) {
    switch (this) {
      case increment:
        return isNumeric(inp);
      case PPMpath:
        if (inp.length() > 4) {
          return inp.substring(inp.length() - 4, inp.length()).equals(".ppm");
        } else {
          return false;
        }
      case targetImage:
      case destinationImage:
        return isAlphabetic(inp);
    }
    throw new IllegalArgumentException("Invalid parameter valued");
  }
}
