package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This {@code ActionListener} class listens for events from the file handling components of the GUI
 * view.
 */
public class FileActionListener implements ActionListener {
  private GUIController control;

  FileActionListener(GUIController control) {
    this.control = control;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand().toLowerCase();
    if (command.equals("load")) {
      final JFileChooser fChooser = new JFileChooser(".");
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
              "JPG, PNG & BMP images", "jpg", "png", "bmp");
      fChooser.setFileFilter(filter);
      int returnValue = fChooser.showOpenDialog(null);
      if (returnValue == JFileChooser.APPROVE_OPTION) {
        File f = fChooser.getSelectedFile();
        try {
          control.redoStack.removeAllElements();
          control.undoStack.push(control.view.getImageOnCanvas());
          BufferedImage image = ImageIO.read(new File(f.getAbsolutePath()));
          control.view.setImageOnCanvas(image);
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    } else if (command.equals("save")) {
      if (control.view.getImageOnCanvas() == null) {
        JOptionPane.showMessageDialog(null, "No image on canvas!", "Error",
                JOptionPane.ERROR_MESSAGE);
        return;
      }
      final JFileChooser fChooser = new JFileChooser(".");
      fChooser.setFileFilter(new FileNameExtensionFilter(".jpg", "jpg", "jpeg"));
      fChooser.addChoosableFileFilter(new FileNameExtensionFilter(".png", "png"));
      fChooser.addChoosableFileFilter(new FileNameExtensionFilter(".bmp", "bmp"));

      int returnValue = fChooser.showSaveDialog(null);
      if (returnValue == JFileChooser.APPROVE_OPTION) {
        File fileToSave = fChooser.getSelectedFile();
        String extension = fChooser.getFileFilter().getDescription();
        String fileName = fileToSave.toString();
        if (fileName.substring(fileName.length() - 4).equals(extension)) {
          fileName = fileName.substring(0, fileName.length() - 4);
        }
        // System.out.println(fileToSave + " " + extension);
        try {
          ImageIO.write(control.view.getImageOnCanvas(), extension.substring(1),
                  new File(fileName + extension));
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    }
  }
}
