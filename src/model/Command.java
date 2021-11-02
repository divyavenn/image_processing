package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import controller.Parameter;

/**
 * A list of commands the program can run.SS
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
  vflip("hflip"),
  quit("quit");

  // Member to hold the name
  private String string;

  private static HashMap<Command, Parameter[]> commandParamMap = new HashMap<>();

  //creates mapping
  private static void createMapping() {
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

  public static boolean needsParam(Command c, Parameter p) {
    if (commandParamMap.containsKey(c)) {
      Parameter[] params = commandParamMap.get(c);
      for (int i = 0; i< params.length; i++) {
         if (params[i].equals(p)) {
           return true;
         }
      }
    }
    return false;
  }

}