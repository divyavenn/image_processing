package controller;

import java.util.HashMap;
import java.util.Map;
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


  protected boolean xnor(boolean a, boolean b) {
    return (a && b) || (!a && !b);
  }
  /**
   * Checks if all needed parameters have been inputted
   */
  protected boolean allNeededParamsInputted(String commandName, Map<Parameter, String> hasParam) {
    boolean b = true;
    for (Parameter p: Parameter.values()) {
      b = b && xnor(Command.needsParam(commandName, p), !(hasParam.get(p)==null));
    }
    return b;
  }

  /**
   * Recursively takes input and follows appopriate prompt
   * @throws IllegalStateException
   * @param scan to read input
   */
  protected void recurse(Scanner scan){
    String commandName = isValidCommand(scan);
    if (Command.quit.toString().equals(commandName)) {
      programQuit();
    }
    else {
      //the value is false if not inputted and the correct value if inputted
      Map<Parameter, String> paramValues  = new HashMap();
      for (Parameter p: Parameter.values()) {
        paramValues.put(p,null);
      }

      //Get all needed inputs
      while (!(allNeededParamsInputted(commandName, paramValues))){
        String nextInput = scan.next();
        //the requirement for the targetImage parameter is met either if the command in question
        //does not need that parameter or if it has already been found
        boolean targetImageParamRequirementMet = !Command.needsParam(commandName,
                Parameter.targetImage) || !(paramValues.get(Parameter.targetImage)==null);
        for (Parameter p: Parameter.values()) {
          //Fill the scanned value into the parameter if the parameter is needed and the input is
          // valid
          if (p.isValidParameter(nextInput) && Command.needsParam(commandName,p)){
            System.out.println("Putting " + nextInput + " into " + p.toString());
            paramValues.put(p,nextInput);
          }
          //if the parameter being tested is destinationImage, since parameters targetImage
          //and destinationImage follow the same format, ensure that the destinationImage
          // is not filled unless targetImage is either already filled or not required
          if (p.equals(Parameter.destinationImage)) {
            if (!(targetImageParamRequirementMet)) {
              paramValues.put(Parameter.destinationImage, null);
            }
          }
        }
      }

      Command c = Command.getCommand(commandName);
      c.run(model, paramValues);
      recurse(scan);
    }
  }
  @Override
  public void start() {
    Scanner scan = new Scanner(in);
    recurse(scan);
  }

  @Override
  public void programQuit() {
    view.renderMessage("Thank you for using this program. Goodbye! \n");
  }
}
