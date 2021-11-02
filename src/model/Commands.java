package model;

/**
 * A list of commands the program can run.SS
 */
public enum Commands {
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

  // constructor to set the string
  Commands(String name){string = name;}

  // the toString just returns the given name
  @Override
  public String toString() {
    return string;
  }


}