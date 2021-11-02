package img;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PPM extends Img {

  public PPM(String name, int height, int width) {
    super(name, height, width);
  }

  public PPM() {
    super("", 0, 0);
  }

  @Override
  public void save(String fPath) throws IOException {
    FileWriter pen;
    //try {
    pen = new FileWriter(fPath);
    pen.write("P3\n");
    pen.write(this.width + " " + this.height + "\n");
    pen.write("# Created by Divya Venkatraman " + "\n");
    for (int i = 0; i<height; i++){
      for (int j = 0; j<width; j++) {
        pen.write(getPixel(i, j).toString());
      }
    }
    //catch (Exception e) {
    //System.out.println("Could not write to file.");
    //throw new IOException("");
    //}
    //}
  }
}

