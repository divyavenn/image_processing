package controller;

import java.awt.event.ActionListener;
import java.io.IOException;


import model.Command;

public interface Features extends ActionListener {


  void doCommand(Command command) throws IOException;

}

