package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Command;
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
    for (Command c: Command.values()){
      if (inp.equals(c.toString())){
        return inp;
      }
    }
    throw new IllegalArgumentException("'" + inp + "' is not a valid command!");
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
   * Analyzes if params list has one increment param
   * @returns if there is one valid increment param
   * @param params the parameters given for command
   */
  protected boolean checkIncrementParam(String[] params) {
    for (int i = 0; i < params.length; i++) {
      if ()
    }
  }

  /**
   * Analyzes if params are valid for command
   * @returns if params are valid
   * @param command the command being entered
   * @param params the parameters given for command
   */
  protected boolean validParams(String command, String[] params) {
    boolean valid = false;
    for (int i = 0; i < params.length; i++) {
      if (pathArg(command)) {

      }
    }
  }

  /**
   * Recursively takes input and follows appopriate prompt
   * @throws IllegalStateException
   * @param scan to read input
   */
  protected void recurse(Scanner scan) throws IllegalStateException, IOException {
    String command = isValidCommand(scan);
    if (Command.quit.toString().equals(command)) {
      programQuit();
    }
    else {
      boolean mustHavePathArg = true;
      boolean mustHaveIncrement = true;
      boolean mustHaveTargetImg = true;
      boolean mustHaveDestinationImg = true;

      boolean hasPathArg = false;
      boolean hasIncrement = false;
      boolean hasTargetImg = false;
      boolean hasDestinationImg = false;

      while (mustHavePathArg != hasPathArg
              || mustHaveIncrement != hasIncrement
              || mustHaveTargetImg != hasTargetImg
              || mustHaveDestinationImg != hasDestinationImg) {

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
