package img;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import util.Tools;

/**
 * Represents the different image types this program can handle. Has methods
 * to return appopriate objects based on ImageType.
 */
public enum FileType {
  ppm, jpeg, png;



  /**
   * Returns the format of the image of the given file path.
   *
   * @param filePath The directory of the file.
   * @return The ImageType enum of the file.
   */
  public static FileType getCorrectFileType(String filePath) {
    String format = "";
    FileType type = null;
    try {
      FileInputStream in = new FileInputStream(filePath);
      Scanner scan = new Scanner(in);
      String tag = scan.next();
      if (tag.equals("P3")) {
        return FileType.ppm;
      }
    } catch (FileNotFoundException e) {
      System.out.println("Unable to find file.");
    }
    if (format.equals("")) {
      try {
        FileInputStream in = new FileInputStream(filePath);
        ImageInputStream iis = ImageIO.createImageInputStream(in);
        Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
        if (!iter.hasNext()) {
          type = null;
          System.out.println("File not supported");
        }
        format = iter.next().getFormatName();
        iis.close();
      } catch (IOException e) {
        System.out.println("Unable to find file.");
      }
    }
    if (format.equals("JPEG")) return FileType.jpeg;
    else if (format.equals("PNG")) return FileType.png;
    else return null;
  }

  /**
   * Makes image from contents stored at given filepath.
   * @param filepath the file path.
   * @param name the name of the image.
   * @return an Img.
   * @throws IllegalArgumentException
   */
  public Img makeImgFromFile(String filepath, String name) throws IllegalArgumentException {
    BufferedImage buffImg;
    Img image = null;
    FileType type = getCorrectFileType(filepath);
    switch (type) {
      case ppm:
        try {
          Readable in = new FileReader(filepath);
          Scanner scan = new Scanner(in);
          int width = Tools.getNextNumericInput(scan);
          int height = Tools.getNextNumericInput(scan);
          Tools.getNextNumericInput(scan);
          image = new Img(name, height, width);
          for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
              image.setPixel(
                      i,
                      j,
                      Tools.getNextNumericInput(scan),
                      Tools.getNextNumericInput(scan),
                      Tools.getNextNumericInput(scan));
            }
          }
        } catch (FileNotFoundException e) {
          System.out.println("Unable to find file.");
          throw new IllegalArgumentException("");
        }
        break;
      case jpeg:
      case png:
        try {
          buffImg = ImageIO.read(new File(filepath));
          int width = buffImg.getWidth();
          int height = buffImg.getHeight();
          image = new Img(name, height, width);
          for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
              Color c = new Color(buffImg.getRGB(j, i));
              int r = c.getRed();
              int g = c.getGreen();
              int b = c.getBlue();
              image.setPixel(i, j, r, b, g);
            }
          }
        } catch (IOException e) {
          System.out.println("Unable to find file.");
          throw new IllegalArgumentException("");
        }
        break;
      default:
        break;
    }
    return image;
  }

}
