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
    pixels = new Pixel[width][height];
  }


  @Override
  public String toString() {
    return name;
  }

  /**
   * Outputs a string that represents the image as a file according to its image type.
   *
   * @return String with file reprenstation of image.
   */
  public abstract String fileRepresentation();

  /**
   * Sets pixels.
   */
  public void setPixels(Pixel[][] pixels){
    this.pixels = pixels;
  }

  /**
   * Gets pixel at specific index
   * @returns Pixel at specific index
   */
  public Pixel getPixel(int width, int height) {
    return pixels[width][height];
  }

  /**
   * Saves image to specified file path.
   *
   * @param fPath the path to save the image to
   * @throws java.io.IOException if writing is unsuccessful
   */
  public void save(String fPath){
    Path file = Path.of(fPath);
    try {
      Files.writeString(file, fileRepresentation());
    }
    catch (IOException e) {
      System.out.println("Unable to write to file.");
    }
  }

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

  protected String allPixelsToString() {
    String str = "";
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        str = str + pixels[i][j];
      }
    }
    return str;
  }

  /**
   * returns horizontally flipped Pixel Array
   * @return horizontally flipped Pixel[][] array
   */
  public Pixel[][] horizontallyFlipped() {
    Pixel[][] flippedPixels = new Pixel[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        flippedPixels[width - i][height] = pixels[width][height];
      }
    }
    return flippedPixels;
  }

  /**
   * returns vertically flipped Pixel Array
   * @return vertically flipped Pixel[][] array
   */
  public Pixel[][] verticallyFlipped() {
    Pixel[][] flippedPixels = new Pixel[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        flippedPixels[width][height - j] = pixels[width][height];
      }
    }
    return flippedPixels;
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