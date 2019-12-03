package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileActionListener implements ActionListener {
  private GUIController control;

  public FileActionListener(GUIController control) {
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
//        enclosingFrame.getLoadDisplayLabel().setText(f.getAbsolutePath());
        try {
          control.history.push(control.view.getImageOnCanvas());
          BufferedImage image = ImageIO.read(new File(f.getAbsolutePath()));
          control.view.setImageOnCanvas(image);
        } catch (IOException ex) {
          ex.printStackTrace();
        }
//        new Toast("Opening  " + f.getName(), 700, 700).showToast(); // todo try for relative
//         position
      }
    } else if (command.equals("save")) {
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
        System.out.println(fileToSave + " " + extension);
        try {
          ImageIO.write(control.view.getImageOnCanvas(), extension.substring(1),
                  new File(fileName + extension));
        } catch (IOException ex) {
          ex.printStackTrace();
        }
        new Toast(fileToSave.getName() + "  saved!", 700, 700).showToast(); // todo try for
        // relative position
      }
    }
  }
}
