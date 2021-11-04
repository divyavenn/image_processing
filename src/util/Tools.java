package util;

import java.util.Scanner;

/**
 * A class containing common methods and functionalities used throughout the program.
 */
public class Tools {
  /**
   * Checks if given String is purely alphabetic.
   *
   * @param inp String representing a parameter
   * @return if is alphabetic
   */
  public static boolean isAlphabetic(String inp) {
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
  public static boolean isNumeric(String inp) {
    try {
      Integer.parseInt(inp);
      return true;
    } catch (NumberFormatException e) {
      // s is not numeric
      return false;
    }
  }

  /**
   * Gets next numeric input from Scanner.
   * @param scan the scanner.
   * @return the next numeric input.
   */
  public static int getNextNumericInput(Scanner scan) {
    String next = scan.next();
    if (isNumeric(next)) {
      return Integer.parseInt(next);
    }
    else {
      return getNextNumericInput(scan);
    }
  }

}
