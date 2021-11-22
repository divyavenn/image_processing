package controller;

import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Command;

public interface Features extends ActionListener {

  void doCommand(Command command);

  void openFile();

}
