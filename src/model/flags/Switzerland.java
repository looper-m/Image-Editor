package model.flags;

import java.awt.image.BufferedImage;

import model.AbstractGeneratorModel;

/**
 * This class represents the flag generation method for Switzerland.
 */
public class Switzerland extends AbstractGeneratorModel {
  private final int imageWidth;
  private final Flag type;

  /**
   * The constructor sets the width of the flag and initializes the color set. The height is same as
   * the width (square) according to accepted proportions.
   *
   * @param imageWidth width of the flag in pixels, as an int.
   */
  public Switzerland(int imageWidth) {
    this.imageWidth = imageWidth;
    this.type = Flag.SWISS;
  }

  @Override
  public BufferedImage generateImage() {
    int edge = (imageWidth - 2) / 5;
    int[][][] outputImage = new int[imageWidth][imageWidth][3];

    for (int y = 0; y < imageWidth; y++) {
      for (int x = 0; x < imageWidth; x++) {
        outputImage[x][y] = type.getKernel()[0];
      }
    }

    for (int y = edge; y < imageWidth - edge; y++) {
      for (int x = 2 * edge + 1; x < 3 * edge + 1; x++) {
        outputImage[x][y] = type.getKernel()[1];
        outputImage[y][x] = type.getKernel()[1];
      }
    }
    return generateBufferedImage(outputImage, imageWidth, imageWidth);
  }
}