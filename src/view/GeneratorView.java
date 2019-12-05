package view;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * This interface represents all separated listener, image setter and getter methods for the
 * interactive GUI view.
 */
public interface GeneratorView {

  /**
   * This action listener is specific for file handling buttons.
   *
   * @param listener the listener object to listen with.
   */
  void addActionListenerForFile(ActionListener listener);

  /**
   * This action listener is specific all the operations.
   *
   * @param listener the listener object to listen with.
   */
  void addActionListenerForOperations(ActionListener listener);

  /**
   * This action listener is for all other kinds of functionality, like, exit(), info(), etc.
   *
   * @param listener the listener object to listen with.
   */
  void addActionListenerForMiscellaneous(ActionListener listener);

  /**
   * This method gets the {@code BufferedImage} image object on the canvas.
   *
   * @return the image on the canvas, as a BufferedImage
   */
  BufferedImage getImageOnCanvas();

  /**
   * This method sets the {@code BufferedImage} image object on the canvas.
   *
   * @param image on the canvas, as a BufferedImage
   */
  void setImageOnCanvas(BufferedImage image);
}
