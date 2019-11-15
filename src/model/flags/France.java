package model.flags;

import java.awt.image.BufferedImage;

import model.AbstractGenerator;

/**
 * This class represents the flag generation method for France.
 */
public class France extends AbstractGenerator {
  private final int imageWidth;
  private final Flag type;

  /**
   * The constructor sets the width of the flag and initializes the color set. The height is set
   * according to accepted proportions.
   *
   * @param imageWidth width of the flag in pixels, as an int.
   */
  public France(int imageWidth) {
    this.imageWidth = imageWidth;
    this.type = Flag.FRENCH;
  }

  @Override
  public BufferedImage generateImage() {
    int imageWidth = this.imageWidth;
    int imageHeight = (this.imageWidth * 2) / 3;
    int[][][] outputImage = new int[imageWidth][imageHeight][3];
    int k = 0;
    for (int y = 0; y < imageHeight; y++) {
      for (int x = 0; x < imageWidth; x++) {
        outputImage[x][y] = type.getKernel()[(k / (this.imageWidth / 3)) % 3];
        k++;
      }
    }
    return generateBufferedImage(outputImage, imageHeight, imageWidth);
  }
}