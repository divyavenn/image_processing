import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import controller.ImgController;
import controller.ImgControllerImplementation;
import model.ImgModel;
import model.ImgModelImplementation;
import view.ImgView;
import view.TextView;

public class FileMain {
  public static void main(String[] args) {
    String fPath = "image_processing/script.txt";
    ImgModel model = new ImgModelImplementation();
    Readable fileIn;
    Appendable out = new StringBuilder();
    ImgView tView = new TextView(model, out);
    try {
      out = new PrintStream(System.out, true, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      System.out.println("could not op.");
      e.printStackTrace();
    }
    try {
      fileIn = new FileReader(fPath);
      ImgController controller = new ImgControllerImplementation(model, tView, fileIn);
      controller.start();
    } catch (FileNotFoundException e) {
      System.out.println("File not found! Goodbye!");
      return;
    }
  }
}
