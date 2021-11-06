package model;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import img.Img;
import img.JPEG;

/**
 * Has backend operations for processing JPG images.
 */
public class JPEGModel extends ImgModelAbstract {

  /**
   * Constructs a JPGModel
   */
  public JPEGModel() {
    super();
  }

  @Override
  protected Img makeImg(String imageName, int height, int width) {
    return new JPEG(imageName, height, width);
  }

  @Override
  protected Img makeImgFromFile(String filepath, String name) throws IllegalArgumentException {
    BufferedImage buffImg;
    try {
      buffImg = ImageIO.read(new File(filepath));
      int width = buffImg.getWidth();
      int height = buffImg.getHeight();
      Img image = new JPEG(name, height, width);
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          Color c = new Color(buffImg.getRGB(j, i));
          int r = c.getRed();
          int g = c.getGreen();
          int b = c.getBlue();
          image.setPixel(i, j, makePixel(r, b, g));
        }
      }
      return image;
    } catch (IOException e) {
      System.out.println("Unable to find file.");
      throw new IllegalArgumentException("");
    }
  }

  @Override
  protected boolean isCorrectFileType(String filePath) {
    try {
      ImageInputStream iis = ImageIO.createImageInputStream(filePath);
      Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
      if (!iter.hasNext()) {
        return false;
      }
      ImageReader tag = iter.next();
      iis.close();
      return (tag.getFormatName().equals("JPEG"));
    } catch (IOException e) {
      System.out.println("Unable to find file.");
    }
    return false;
  }
}
