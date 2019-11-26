package model.images;

import java.awt.Color;
import java.awt.image.BufferedImage;

import model.AbstractGeneratorModel;

/**
 * This class implements the method defined in {@code GeneratorModel} to transform an input image.
 */
public class TransformImage extends AbstractGeneratorModel {
  private final int[][][] pixelArray;
  private final Transform type;
  private final int imageHeight;
  private final int imageWidth;

  /**
   * This constructor creates the image pixel array to apply a transformation. The transformation
   * type is received as an enum variable through the second parameter.
   *
   * @param image the input image, as BufferedImage type.
   * @param type  the type of transformation to apply- SEPIA/GREYSCALE.
   */
  public TransformImage(BufferedImage image, Transform type) {
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

  @Override
  public BufferedImage generateImage() {
    float[][] currentKernel = type.getKernel();

    for (int y = 0; y < imageHeight; y++) {
      for (int x = 0; x < imageWidth; x++) {
        applyTransformation(pixelArray[x][y], currentKernel);
      }
    }
    return generateBufferedImage(pixelArray, imageHeight, imageWidth);
  }

  private int getClampedValue(int value) {
    return value < 0 ? 0 : Math.min(value, 255);
  }

  private void applyTransformation(int[] pixel, float[][] kernel) {
    int[] tempPixel = {0, 0, 0};
    for (int i = 0; i < kernel.length; i++) {
      for (int j = 0; j < tempPixel.length; j++) {
        tempPixel[i] += kernel[i][j] * pixel[j];
      }
      tempPixel[i] = getClampedValue(tempPixel[i]);
    }
    System.arraycopy(tempPixel, 0, pixel, 0, tempPixel.length);
  }
}
