package controller;

import java.awt.image.BufferedImage;

/**
 * This interface represents a generator command. Any new image generator that is introduced must be
 * defined in a separate class and must implement this interface.
 */
public interface GeneratorCommand {
  /**
   * This method invokes the corresponding generator implementation classes at the Model.
   */
  BufferedImage executeCommand(BufferedImage input);
}
