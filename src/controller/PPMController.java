package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Commands;
import model.ImgModel;
import view.ImgView;

public class PPMController extends ImgControllerAbstract{

  /**
   * Constructs an ImgController object.
   *
   * @param model
   * @param view
   * @param in
   * @throws IllegalArgumentException if any inputs are null
   */
  public PPMController(ImgModel model, ImgView view, Readable in) throws IllegalArgumentException {
    super(model, view, in);
  }

  /**
   * Takes next input in scanner and checks if it is a valid command
   * @return the command if it is valid
   * @throws IllegalArgumentException
   */
  protected String isValidCommand(Scanner scan){
    String inp = scan.next();
    for (Commands c: Commands.values()){
      if (inp.equals(c)){
        return inp;
      }
    }
    throw new IllegalArgumentException("Not a valid command!");
  }

  /**
   * Takes next input in scanner and checks if it is a valid filePath
   * @return the file path if it is valid
   * @throws IllegalArgumentException
   */
  protected String isValidFilePath(Scanner scan){
    String inp = scan.next();
    if (inp.substring(inp.length()-4, inp.length()).equals(".ppm")) {
      return inp;
    }
    throw new IllegalArgumentException("Can only write and read to ppm files!");
  }


  /**
   * Recursively takes input and follows appopriate prompt
   * @throws IllegalStateException
   * @param scan to read input
   */
  protected void recurse(Scanner scan) throws IllegalStateException {
    String command = isValidCommand(scan);
    if (command.equals(Commands.quit)) {
      return;
    }
    else {
      ArrayList<String> params = new ArrayList<String>();
      while (scan.hasNext()){
        params.add(scan.next());
      }
      for (int i = 0; i<params.size(); i++){
        System.out.println(params.get(i));
      }
      recurse(scan);
    }
  }
  @Override
  public void start() throws IllegalStateException {
    Scanner scan = new Scanner(in);
    recurse(scan);
  }

  @Override
  public void programQuit() throws IOException {
    view.renderMessage("Thank you for using this program. Goodbye! \n");
  }
}
