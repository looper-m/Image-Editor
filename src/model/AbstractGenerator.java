package model;

import java.awt.image.BufferedImage;

/**
 * This abstract class implements one common method for all its sub-classes.
 */
public abstract class AbstractGenerator implements Generator {

  /**
   * Using the inputs passed, this method creates a {@code BufferedImage} variable to be sent back
   * to the controller class.
   *
   * @param pixelArray  the image pixel array to create the BufferedImage on, as a 2D array.
   * @param imageHeight the height of the input image, as an int.
   * @param imageWidth  the width of the input image, as an int.
   * @return the image packaged in a BufferedImage variable.
   */
  protected BufferedImage generateBufferedImage(int[][][] pixelArray, int imageHeight,
                                                int imageWidth) {
    BufferedImage output = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
    for (int y = 0; y < imageHeight; y++) {
      for (int x = 0; x < imageWidth; x++) {
        output.getRaster().setPixel(x, y, pixelArray[x][y]);
      }
    }
    return output;
  }
}
