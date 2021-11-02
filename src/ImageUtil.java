import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import controller.ImgController;
import controller.PPMController;
import model.ImgModel;
import model.PPMModel;
import view.ImgView;
import view.TextView;


/**
 * This class contains utility methods to read a img.PPM image from file and simply print its contents. Feel free to change this method
 *  as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the img.PPM format and print the colors.
   *
   * @param filename the path of the file. 
   */
  public static void readPPM(String filename) {
    Scanner sc;
    
    try {
        sc = new Scanner(new FileInputStream(filename));
    }
    catch (FileNotFoundException e) {
        System.out.println("File "+filename+ " not found!");
        return;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.charAt(0)!='#') {
            builder.append(s+System.lineSeparator());
        }
    }
    
    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token; 

    token = sc.next();
    if (!token.equals("P3")) {
        System.out.println("Invalid img.PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: "+width);
    int height = sc.nextInt();
    System.out.println("Height of image: "+height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): "+maxValue);
    
    for (int i=0;i<height;i++) {
        for (int j=0;j<width;j++) {
            int r = sc.nextInt();
            int g = sc.nextInt();
            int b = sc.nextInt();
            System.out.println("Color of pixel ("+j+","+i+"): "+ r+","+g+","+b);
        }
    }
  }

  //demo main
  public static void main(String []args) throws UnsupportedEncodingException {
     /** String filename;
      
      if (args.length>0) {
          filename = args[0];
      }
      else {
          filename = "image_processing/src/Koala.ppm";
      }
      
      ImageUtil.readPPM(filename);
      **/
     ImgModel model = new PPMModel();
     Appendable out = new PrintStream(System.out, true, "UTF-8");
     Readable in = new InputStreamReader(System.in);
     ImgView view = new TextView(model, out);
    ImgController controller = new PPMController(model, view, in);
    controller.start();


  }
}

