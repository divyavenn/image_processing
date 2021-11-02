package controller;

public enum Parameter {
  increment,
  PPMpath,
  targetImage,
  destinationImage;

  private boolean isAlphabetic(String inp) {
    for (int i = 0; i != inp.length(); ++i) {
      if (!Character.isLetter(inp.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  private boolean isNumeric(String inp) {
    try {
      Integer.parseInt(inp);
      return true;
    } catch (NumberFormatException e) {
      // s is not numeric
      return false;
    }
  }


  public boolean isValidParameter(String inp) {
    switch (this) {
      case increment:
        return isNumeric(inp);
        break;
      case PPMpath:
        return inp.substring(inp.length()-4, inp.length()).equals(".ppm");
        break;
      case targetImage:
        return isAlphabetic(inp);
        break;
      case destinationImage:
        return isAlphabetic(inp);
        break;
    }
  }
}
