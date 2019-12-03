package view;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public interface GeneratorView {

  void addActionListenerForFile(ActionListener listener);

  void addActionListenerForOperations(ActionListener listener);

  void addActionListenerForMiscellaneous(ActionListener listener);

  BufferedImage getImageOnCanvas();

  void setImageOnCanvas(BufferedImage image);
}
