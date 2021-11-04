package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import controller.Parameter;

/**
 * A list of commands the program can run.
 */
public enum Command {
  load("load"),
  brighten("brighten"),
  save("save"),
  rc("just-red"),
  gc("just-green"),
  bc("just-blue"),
  vc("just-value"),
  lc("just-luma"),
  ic("just-intensity"),
  hflip("hflip"),
  vflip("vflip"),
  quit("quit");

  // Member to hold the name
  private String string;

  //maps the command to the parameters it requires.
  private static HashMap<Command, Parameter[]> commandParamMap = new HashMap<>();

  static {
    commandParamMap.put(Command.load, new Parameter[]{
            Parameter.PPMpath,
            Parameter.destinationImage});

    commandParamMap.put(Command.save, new Parameter[]{
            Parameter.PPMpath,
            Parameter.targetImage});

    commandParamMap.put(Command.brighten, new Parameter[]{
            Parameter.increment,
            Parameter.targetImage,
            Parameter.destinationImage});

    commandParamMap.put(Command.rc, new Parameter[]{
            Parameter.targetImage,
            Parameter.destinationImage});

    commandParamMap.put(Command.gc, new Parameter[]{
            Parameter.targetImage,
            Parameter.destinationImage});

    commandParamMap.put(Command.bc, new Parameter[]{
            Parameter.targetImage,
            Parameter.destinationImage});

    commandParamMap.put(Command.vc, new Parameter[]{
            Parameter.targetImage,
            Parameter.destinationImage});

    commandParamMap.put(Command.lc, new Parameter[]{
            Parameter.targetImage,
            Parameter.destinationImage});

    commandParamMap.put(Command.ic, new Parameter[]{
            Parameter.targetImage,
            Parameter.destinationImage});

    commandParamMap.put(Command.vflip, new Parameter[]{
            Parameter.targetImage,
            Parameter.destinationImage});

    commandParamMap.put(Command.hflip, new Parameter[]{
            Parameter.targetImage,
            Parameter.destinationImage});

  }

  // constructor to set the string
  Command(String name) {
    string = name;
  }

  // the toString just returns the given name
  @Override
  public String toString() {
    return string;
  }


  /**
   * Gets the command given its name, returns null if command does not exist.
   *
   * @param commandName the name of the command
   * @return command or null
   */
  public static Command getCommand(String commandName) {
    for (Command c : Command.values()) {
      if (c.toString().equals(commandName)) {
        return c;
      }
    }
    return null;
  }


  /**
   * Finds if given command (referred to by its name) needs the given parameter.
   *
   * @param commandName name of command
   * @param p           parameter
   * @return true or false
   */
  public static boolean needsParam(String commandName, Parameter p) {
    Command c = getCommand(commandName);
    if (c == null) {
      System.out.println("not valid command.");
      return false;
    }
    if (commandParamMap.containsKey(c)) {
      Parameter[] params = commandParamMap.get(c);
      for (int i = 0; i < params.length; i++) {
        if (params[i].equals(p)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Runs the appopriate method from a model.
   * @param paramValues the values given to the method to run
   */
  public void run(ImgModel model, Map<Parameter, String> paramValues) throws IOException {
    switch (this) {
      case load:
        model.load(paramValues.get(Parameter.PPMpath),
                paramValues.get(Parameter.destinationImage));
        break;
      case brighten:
        model.brighten(Integer.parseInt(paramValues.get(Parameter.increment)),
                paramValues.get(Parameter.targetImage),
                paramValues.get(Parameter.destinationImage));
        break;
      case save:
        model.save(paramValues.get(Parameter.PPMpath),
                paramValues.get(Parameter.targetImage));
        break;
      case rc:
      case bc:
      case gc:
      case ic:
      case lc:
      case vc:
        model.exportComponentByPixel(this,
                paramValues.get(Parameter.targetImage),
                paramValues.get(Parameter.destinationImage));
        break;
      case hflip:
      case vflip:
        model.flip(this,
                paramValues.get(Parameter.targetImage),
                paramValues.get(Parameter.destinationImage));
        break;
       case quit:
        break;
    }
  }

  /**
   * Acknowledges the appropriate command has been run with the correct parameters.
   * @param paramValues the values given to the method to run
   * @returns a String with the acknowledgement.
   */
  public String acknowledge(Map<Parameter, String> paramValues) {
    switch (this) {
      case load:
        return ("Loaded "
                + paramValues.get(Parameter.PPMpath)
                + " into "
                + paramValues.get(Parameter.destinationImage));
      case brighten:
        return ("Brightened "
                + paramValues.get(Parameter.targetImage)
                + " by "
                + paramValues.get(Parameter.increment)
                + " and loaded into "
                + paramValues.get(Parameter.destinationImage));
      case save:
        return ("Saved "
                + paramValues.get(Parameter.targetImage)
                + " to "
                + paramValues.get(Parameter.PPMpath));
      case rc:
        return ("Exported red component of "
                + paramValues.get(Parameter.targetImage)
                + " into "
                + paramValues.get(Parameter.destinationImage));
      case bc:
        return ("Exported blue component of "
                + paramValues.get(Parameter.targetImage)
                + " into "
                + paramValues.get(Parameter.destinationImage));
      case gc:
        return ("Exported green component of "
                + paramValues.get(Parameter.targetImage)
                + " into "
                + paramValues.get(Parameter.destinationImage));
      case ic:
        return ("Exported pixelwise intensity of "
                + paramValues.get(Parameter.targetImage)
                + " into "
                + paramValues.get(Parameter.destinationImage));
      case lc:
        return ("Exported pixelwise luma of "
                + paramValues.get(Parameter.targetImage)
                + " into "
                + paramValues.get(Parameter.destinationImage));
      case vc:
        return ("Exported pixelwise value of "
                + paramValues.get(Parameter.targetImage)
                + " into "
                + paramValues.get(Parameter.destinationImage));
      case hflip:
        return ("Horizontally flipped"
                + paramValues.get(Parameter.targetImage)
                + " and loaded result into "
                + paramValues.get(Parameter.destinationImage));
      case vflip:
        return ("Vertically flipped value of "
                + paramValues.get(Parameter.targetImage)
                + " and loaded result into "
                + paramValues.get(Parameter.destinationImage));
      case quit:
        return ("Program quit. Goodbye!");
    }
    return "";
  }
}