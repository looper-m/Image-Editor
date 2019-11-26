package model;

import java.awt.image.BufferedImage;

/**
 * The interface represents the model of the program. It declares a method that will generate a new
 * or alter an existing image.
 */
public interface GeneratorModel {

  /**
   * This method calculates every pixel value to alter an original or generate a complete new image
   * depending on the implementation.
   *
   * @return modified or new image, as BufferedImage type.
   */
  BufferedImage generateImage();
}
