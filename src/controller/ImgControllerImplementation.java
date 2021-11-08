package controller;


import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.Command;
import model.ImgModel;
import view.ImgView;

/**
 * Controls the program that processes images.
 */
public class ImgControllerImplementation implements ImgController {
  ImgModel model;
  ImgView view;
  Readable in;


  /**
   * Constructs an ImgController object.
   *
   *
   * @param model the model
   * @param view the view
   * @param in the readable
   * @throws IllegalArgumentException if any inputs are null
   */
  public ImgControllerImplementation(ImgModel model,
                                     ImgView view,
                                     Readable in) throws IllegalArgumentException {
    if (model == null || view == null || in == null) {
      throw new IllegalArgumentException("Null input.");
    }
    this.model = model;
    this.view = view;
    this.in = in;
  }


  /**
   * Takes next input in scanner and checks if it is a valid command.
   *
   * @return the command if it is valid
   * @throws IllegalArgumentException if command not valid
   */
  protected String isValidCommand(Scanner scan) {
    if (scan.hasNext()) {
      String inp = scan.next();
      for (Command c : Command.values()) {
        if (inp.equals(c.toString())) {
          return inp;
        }
      }
      throw new IllegalArgumentException("'" + inp + "' is not a valid command!");
    }
    throw new IllegalArgumentException("Nothing was inputted.");
  }


  /**
   * A simple xnor gate - checks if both inputs are same.
   *
   * @param a one input
   * @param b other input
   * @return true if both are same.q
   */
  protected boolean xnor(boolean a, boolean b) {
    return (a && b) || (!a && !b);
  }

  /**
   * Checks if all needed parameters have been inputted.
   *
   * @param commandName the name of the command
   * @param hasParam    the parameters it was given
   */
  protected boolean allNeededParamsInputted(String commandName, Map<Parameter, String> hasParam) {
    boolean b = true;
    for (Parameter p : Parameter.values()) {
      b = b && xnor(Command.needsParam(commandName, p), (hasParam.get(p) != null));
    }
    return b;
  }

  /**
   * Recursively takes input and follows appopriate prompt.
   * Catches all exceptions. Program ends if quit Command is entered.
   *
   * @param scan to read input
   */
  protected void recurse(Scanner scan) {
    String commandName = "";
    try {
      commandName = isValidCommand(scan);
    } catch (IllegalArgumentException e) {
      view.renderMessage("Invalid command name! Try again.");
      recurse(scan);
    } catch (NoSuchElementException e) {
      return;
    } catch (NullPointerException e) {
      view.renderMessage("Nothing was inputted");
      return;
    }

    //the value is false if not inputted and the correct value if inputted
    Map<Parameter, String> paramValues = new HashMap();
    for (Parameter p : Parameter.values()) {
      paramValues.put(p, null);
    }

    //Get all needed inputs
    while (!(allNeededParamsInputted(commandName, paramValues))) {
      String nextInput = "";
      if (scan.hasNext()) {
        nextInput = scan.next();
      } else {
        break;
      }
      //the requirement for the targetImage parameter is met either if the command in question
      //does not need that parameter or if it has already been found
      boolean targetImageParamRequirementMet = !Command.needsParam(commandName,
              Parameter.targetImage) || (paramValues.get(Parameter.targetImage) != null);
      for (Parameter p : Parameter.values()) {
        //Fill the scanned value into the parameter if the parameter is needed and the input is
        // valid
        if (Command.needsParam(commandName, p) && p.isValidParameter(nextInput)) {
          if ((!(p.equals(Parameter.targetImage))) || !targetImageParamRequirementMet) {
            paramValues.put(p, nextInput);
          } else if (p.equals(Parameter.destinationImage) && targetImageParamRequirementMet) {
            paramValues.put(p, nextInput);
          }
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
    try {
      c.run(model, paramValues);
      view.renderMessage(c.acknowledge(paramValues) + "\n");
      if (!(c.equals(Command.quit))) {
        recurse(scan);
      }
    } catch (Exception e) {
      view.renderMessage("Command unsuccessful. Try again. \n");
      recurse(scan);
    }

  }

  @Override
  public void start() {
    Scanner scan = new Scanner(in);
    recurse(scan);
  }
}
