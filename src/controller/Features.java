package controller;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Command;

public interface Features extends ActionListener {

  void doCommand(Command command) throws IOException;

  void openFile();

}
