package model.flags;

import java.awt.image.BufferedImage;

import model.AbstractGenerator;

/**
 * This class represents the flag generation method for Greece.
 */
public class Greece extends AbstractGenerator {
  private final int imageWidth;
  private final Flag type;

  /**
   * The constructor sets the width of the flag and initializes the color set. The height is
   * computed according to accepted proportions.
   *
   * @param imageWidth width of the flag in pixels, as an int.
   */
  public Greece(int imageWidth) {
    this.imageWidth = imageWidth;
    this.type = Flag.GREEK;
  }

  @Override
  public BufferedImage generateImage() {
    int imageWidth = this.imageWidth;
    int imageHeight = (this.imageWidth * 2) / 3;
    int stripWidth = imageHeight / 9;
    int[][][] outputImage = new int[imageWidth][imageHeight][3];
    int k = 0;

    for (int y = 0; y < imageHeight; y++) {
      for (int x = 0; x < imageWidth; x++) {
        outputImage[x][y] = type.getKernel()[(k / stripWidth) % 2];
      }
      k++;
    }

    for (int y = 0; y < 5 * stripWidth; y++) {
      for (int x = 0; x < 5 * stripWidth; x++) {
        outputImage[x][y] = type.getKernel()[0];
      }
    }

    for (int y = 2 * stripWidth; y < 3 * stripWidth; y++) {
      for (int x = 0; x < 5 * stripWidth; x++) {
        outputImage[x][y] = type.getKernel()[1];
        outputImage[y][x] = type.getKernel()[1];
      }
    }
    return generateBufferedImage(outputImage, imageHeight, imageWidth);
  }
}