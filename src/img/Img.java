package img;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class Img {
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
  }


  @Override
  public String toString() {
    return name;
  }

  /**
   * Sets pixels.
   */
  public void setPixel(int height, int width, Pixel p) {
    pixels[height][width] = p;
  }

  /**
   * Gets pixel at specific index
   * @returns Pixel at specific index
   */
  public Pixel getPixel(int height, int width) {
    return pixels[height][width];
  }

  /**
   * Saves image to specified file path.
   *
   * @param fPath the path to save the image to
   * @throws java.io.IOException if writing is unsuccessful
   */
  public abstract void save(String fPath) throws IOException;

  /**
   * Gets height of image
   * @return height of image
   */
  public int getHeight(){
    return this.height;
  }

  /**
   * Gets width of image
   * @return width of image
   */
  public int getWidth(){
    return this.width;
  }


  @Override
  public boolean equals(Object o){
    return (this.toString().equals(o.toString()));
  }

  @Override
  public int hashCode() {
    return Integer.parseInt(toString() + height + width);
  }

}