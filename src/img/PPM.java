package img;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;


/**
 * Represents an Image of type PPM.
 */
public class PPM extends Img {

  /**
   * Make PPM image.
   * @param name the name of image.
   * @param height the height.
   * @param width the width.
   */
  public PPM(String name, int height, int width) {
    super(name, height, width);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        setPixel(i, j, new Pixel());
      }
    }
  }


  @Override
  public void save(String fPath) throws IOException {
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
  }
}

