package img;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.Color;

import javax.imageio.ImageIO;

/**
 * Represents a JPG format Img.
 */
public class JPG extends Img{

  /**
   * Creates a JPG image.
   * @param name name of image
   * @param height height of image.
   * @param width width of image.
   */
  public JPG(String name, int height, int width) {
    super(name, height, width);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        setPixel(i, j, new PPMPixel());
      }
    }
  }

  @Override
  public void save(String fPath) throws IOException {
    BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < height; i ++) {
        for (int j = 0; j < width; j ++) {
          int r = pixels[i][j].getRed();
          int g = pixels[i][j].getGreen();
          int b = pixels[i][j].getBlue();
          int rgb = (r << 16) | (g << 8) | b;
          buffImg.setRGB(j, i, rgb);
        }
      }
    ImageIO.write(buffImg, "jpg" , new FileOutputStream(fPath));
    }
}
