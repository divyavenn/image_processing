package img;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import controller.ImgController;
import controller.PPMController;
import model.ImgModel;
import model.JPEGModel;
import model.PNGModel;
import model.PPMModel;
import util.Tools;
import view.ImgView;

/**
 * Represents the different image types this program can handle. Has methods
 * to return appopriate objects based on ImageType.
 */
public enum ImageType {
  ppm, jpeg, png;


  /**
   * Make model specific to ImageType.
   *
   * @param type the type.
   * @return the model.
   */
  public static ImgModel makeModel(ImageType type) {
    if (type.equals(ImageType.ppm)) {
      return new PPMModel();
    }
    if (type.equals(ImageType.jpeg)) {
      return new JPEGModel();
    }
    if (type.equals(ImageType.png)) {
      return new PNGModel();
    } else {
      throw new IllegalArgumentException("Not a valid image type");
    }
  }


  /**
   * Returns an Img Object corresponding to the implementing class.
   *
   * @param type   the type of Image
   * @param name   the name of the image
   * @param height the height of the image
   * @param width  the width of the image
   * @returns an Img Object
   */
  public static Img makeImg(ImageType type, String name, int height, int width) {
    if (type.equals(ImageType.ppm)) {
      return new PPM(name, height, width);
    }
    if (type.equals(ImageType.jpeg)) {
      return new JPEG(name, height, width);
    }
    if (type.equals(ImageType.png)) {
      return new PNG(name, height, width);
    } else {

      throw new IllegalArgumentException("Not a valid image type");
    }
  }


  /**
   * Returns the format of the image of the given file path.
   *
   * @param filePath The directory of the file.
   * @return The ImageType enum of the file.
   */
  public static ImageType getCorrectFileType(String filePath) {
    String format = "";
    ImageType type = null;
    try {
      FileInputStream in = new FileInputStream(filePath);
      Scanner scan = new Scanner(in);
      String tag = scan.next();
      if (tag.equals("P3")) {
        return ImageType.ppm;
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
    if (format.equals("JPEG")) return ImageType.jpeg;
    else if (format.equals("PNG")) return ImageType.png;
    else return null;
  }

  public Img makeImgFromFile(String filepath, String name) throws IllegalArgumentException {
    BufferedImage buffImg;
    Img image = null;
    ImageType type = getCorrectFileType(filepath);
    switch (type) {
      case ppm:
        try {
          Readable in = new FileReader(filepath);
          Scanner scan = new Scanner(in);
          int width = Tools.getNextNumericInput(scan);
          int height = Tools.getNextNumericInput(scan);
          Tools.getNextNumericInput(scan);
          image = new PPM(name, height, width);
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
          image = makeImg(type, name, height, width);
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
