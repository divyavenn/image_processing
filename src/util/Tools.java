package util;

import java.awt.image.BufferedImage;
import java.util.Scanner;

import img.Img;

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

  /**
   * Generates a BufferedImage from the given Img object.
   * @param image An Img object
   * @return A bufferedImg representing the given Img.
   */
  public static BufferedImage getBuffImg(Img image) {
    int width = image.getWidth();
    int height = image.getHeight();
    BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = image.getPixel(i, j).getRed();
        int g = image.getPixel(i, j).getGreen();
        int b = image.getPixel(i, j).getBlue();
        int rgb = (r << 16) | (g << 8) | b;
        buffImg.setRGB(j, i, rgb);
      }
    }
    return buffImg;
  }

}
