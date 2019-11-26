package controller;

import java.awt.image.BufferedImage;

public interface GeneratorCommand {
  BufferedImage executeCommand(BufferedImage input);
}
