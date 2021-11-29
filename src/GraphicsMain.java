

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;

import controller.GUIControllerImplementation;

import controller.ImgController;
import controller.ImgControllerImplementation;
import model.ImgModel;
import model.ImgModelImplementation;
import view.GraphicsView;
import view.ImgView;
import view.TextView;


/**
 * Class meant for running program using graphics input.
 */
public class GraphicsMain {

  /**
   * Runs the image processor with graphical interface.
   * @param args The main arguments.
   */
  public static void main(String[] args) {
    //GUI
    if (args.length == 0) {
      ImgModel model = new ImgModelImplementation();
      GraphicsView view = new GraphicsView();
      GUIControllerImplementation controller = new GUIControllerImplementation(model, view);
      controller.setView();
      controller.start();
      return;
    }
    else {
      String runType = args[0];
      ImgModel model = new ImgModelImplementation();
      Appendable out = new StringBuilder();
      try {
        out = new PrintStream(System.out, true, "UTF-8");
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
      ImgView tView = new TextView(model, out);

      if (runType.equals("-file")) {
        try {
          String fPath = args[1];
          String fileToString = Files.readString(Path.of(fPath));
          Readable in = new StringReader(fileToString);
          ImgView view = new TextView(model, out);
          ImgController controller = new ImgControllerImplementation(model, view, in);
          controller.start();
        } catch (FileNotFoundException e) {
          System.out.println("File not found! Goodbye!");
          return;
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      else if (runType.equals("-text")) {
        Readable consoleIn = new InputStreamReader(System.in);
        ImgController controller = new ImgControllerImplementation(model, tView, consoleIn);
        controller.start();
      }
    }
  }
}
