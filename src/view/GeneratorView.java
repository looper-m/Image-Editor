package view;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public interface GeneratorView {

  void addActionListenerForFile(ActionListener listener);

  void addActionListenerForOperations(ActionListener listener);

  BufferedImage getImageOnCanvas();

  void setImageOnCanvas(BufferedImage image);
}
