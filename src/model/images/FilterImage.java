package model.images;

import java.awt.Color;
import java.awt.image.BufferedImage;

import model.AbstractGeneratorModel;

/**
 * This class implements the method defined in {@code GeneratorModel} to apply filters on the input
 * image.
 */
public class FilterImage extends AbstractGeneratorModel {
  final int[][][] pixelArray;
  final Filter type;
  final int imageHeight;
  final int imageWidth;

  /**
   * This constructor creates the image pixel array to apply the filter on. The filter is received
   * as an enum variable through the second parameter.
   *
   * @param image the input image, as BufferedImage type.
   * @param type  the type of filter to use- BLUR/SHARPEN.
   */
  public FilterImage(BufferedImage image, Filter type) {
    this.type = type;
    this.imageHeight = image.getHeight();
    this.imageWidth = image.getWidth();
    this.pixelArray = new int[imageWidth][imageHeight][3];

    for (int y = 0; y < imageHeight; y++) {
      for (int x = 0; x < imageWidth; x++) {
        Color c = new Color(image.getRGB(x, y));
        pixelArray[x][y] = new int[]{c.getRed(), c.getGreen(), c.getBlue()};
      }
    }
  }

  int[][] generateDirectionalMatrix(int size) {
    int bound = -size / 2;
    int[][] directions = new int[size * size][2];

    int yBound = bound;
    int xBound = bound;
    for (int i = 0; i < size * size; i += size) {
      for (int j = i; j < i + size; j++) {
        directions[j] = new int[]{xBound, yBound++};
      }
      xBound++;
      yBound = bound;
    }
    return directions;
  }

  boolean inValidBounds(int x, int y, int width, int height) {
    return x >= 0 && x < width && y >= 0 && y < height;
  }

  private int getClampedValue(int value) {
    return value < 0 ? 0 : Math.min(value, 255);
  }

  @Override
  public BufferedImage generateImage() {
    int channelValue = 0;
    float[][] currentKernel = type.getKernel();
    int[][] directions = generateDirectionalMatrix(currentKernel.length);
    int[][][] outputImage = new int[imageWidth][imageHeight][3];

    for (int y = 0; y < imageHeight; y++) {
      for (int x = 0; x < imageWidth; x++) {
        for (int channel = 0; channel < 3; channel++) {
          for (int[] dir : directions) {
            int xImg = x + dir[0];
            int yImg = y + dir[1];
            int xDir = currentKernel.length / 2 + dir[0];
            int yDir = currentKernel.length / 2 + dir[1];

            if (inValidBounds(xImg, yImg, imageWidth, imageHeight)) {
              channelValue += Math.round(pixelArray[xImg][yImg][channel]
                      * currentKernel[xDir][yDir]);
            }
          }
          outputImage[x][y][channel] = getClampedValue(channelValue);
          channelValue = 0;
        }
      }
    }
    return generateBufferedImage(outputImage, imageHeight, imageWidth);
  }
}