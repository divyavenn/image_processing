package img;

import java.io.IOException;

/**
 * Represents an Image.
 */
public abstract class Img {
  String name;
  int height;
  int width;
  Pixel[][] pixels;
  ImageType type;

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
        setPixel(i, j, 0,0,0);
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
    if (height > 0 && height < this.height) {
      if (width > 0 && width < this.width) {
        return pixels[height][width];
      }
    }
    return null;
  }

  /**
   * Saves image to specified file path.
   *
   * @param fPath the path to save the image to
   * @throws java.io.IOException if writing is unsuccessful
   */
  public abstract void save(String fPath) throws IOException;

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

}