package img;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import javax.imageio.ImageIO;

/**
 * Represents an Image.
 */
public class Img {
  String name;
  int height;
  int width;
  Pixel[][] pixels;

  /**
   * Constructs an Img Object.
   *
   * @param name   name of image.
   * @param height of image.
   * @param width  width of image.
   */
  public Img(String name, int height, int width) {
    this.name = name;
    this.height = height;
    this.width = width;
    pixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        setPixel(i, j, 0, 0, 0);
      }
    }
  }


  @Override
  public String toString() {
    return name;
  }

  /**
   * Sets pixels.
   */
  public void setPixel(int height, int width, int r, int g, int b) {
    pixels[height][width] = new Pixel(
            Math.min(Math.max(r, 0), 255),
            Math.min(Math.max(g, 0), 255),
            Math.min(Math.max(b, 0), 255));
  }

  /**
   * Gets pixel at specific index.
   *
   * @returns Pixel at specific index
   */
  public Pixel getPixel(int height, int width) {
    if (height >= 0 && height < this.height) {
      if (width >= 0 && width < this.width) {
        return pixels[height][width];
      }
    }
    return null;
  }


  /**
   * Gets height of image.
   *
   * @return height of image
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Gets width of image.
   *
   * @return width of image
   */
  public int getWidth() {
    return this.width;
  }

  @Override
  public boolean equals(Object o) {
    return (this.toString().equals(o.toString()));
  }

  @Override
  public int hashCode() {
    return Integer.parseInt(toString() + height + width);
  }

  /**
   * Saves image to specified file path.
   *
   * @param fPath the path to save the image to
   * @param type  the file format to save to.
   * @throws java.io.IOException if writing is unsuccessful
   */
  public void save(FileType type, String fPath) throws IOException {
    String formatName = "";
    switch (type) {
      case ppm:
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fPath),
                Charset.forName("UTF-8").newEncoder()));
        out.write("P3", 0, 2);
        out.newLine();
        String widthHeight = this.width + " " + this.height;
        out.write(widthHeight, 0, widthHeight.length());
        out.newLine();
        out.write("255", 0, 3);
        out.newLine();
        for (int i = 0; i < height; i++) {
          for (int j = 0; j < width; j++) {
            String pixStr = getPixel(i, j).toString();
            out.write(pixStr, 0, pixStr.length());
          }
        }
        out.close();
        return;
      case png:
      case jpeg:
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++) {
          for (int j = 0; j < width; j++) {
            int r = pixels[i][j].getRed();
            int g = pixels[i][j].getGreen();
            int b = pixels[i][j].getBlue();
            int rgb = (r << 16) | (g << 8) | b;
            buffImg.setRGB(j, i, rgb);
          }
        }
        ImageIO.write(buffImg, type.toString(), new FileOutputStream(fPath));
        break;
    }
  }
}
