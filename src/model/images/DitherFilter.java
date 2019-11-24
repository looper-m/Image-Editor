package model.images;

import java.awt.image.BufferedImage;

/**
 * This class extends {@code FilterImage} class to provide somewhat variable implementation of the
 * {@code generateImage} function for dithering an input image.
 */
public class DitherFilter extends FilterImage {
  /**
   * This constructor creates the image pixel array to apply the filter on. The filter is received
   * as an enum variable through the second parameter.
   *
   * @param image the input image, as BufferedImage type.
   * @param type  the type of filter to use- DITHER.
   */
  public DitherFilter(BufferedImage image, Filter type) {
    super(image, type);
  }

  private int getClosestColor(int oldColor) {
    return oldColor > 127 ? 255 : 0;
  }

  @Override
  public BufferedImage generateImage() {
    int oldColor;
    int newColor;
    int errorValue;
    float pixelRefactor;
    float[][] currentKernel = type.getKernel();
    int[][] directions = generateDirectionalMatrix(currentKernel.length);

    for (int y = 0; y < imageHeight; y++) {
      for (int x = 0; x < imageWidth; x++) {
        oldColor = pixelArray[x][y][0];
        newColor = getClosestColor(oldColor);
        errorValue = oldColor - newColor;
        pixelArray[x][y] = new int[]{newColor, newColor, newColor};

        for (int[] dir : directions) {
          int xImg = x + dir[0];
          int yImg = y + dir[1];
          int xDir = currentKernel.length / 2 + dir[0];
          int yDir = currentKernel.length / 2 + dir[1];

          if (inValidBounds(xImg, yImg, imageWidth, imageHeight)
                  && currentKernel[xDir][yDir] != 1f) {
            pixelRefactor = errorValue * currentKernel[xDir][yDir];

            pixelArray[xImg][yImg] = new int[]{
                    Math.round(pixelRefactor + pixelArray[xImg][yImg][0]),
                    Math.round(pixelRefactor + pixelArray[xImg][yImg][1]),
                    Math.round(pixelRefactor + pixelArray[xImg][yImg][2])};
          }
        }
      }
    }
    return generateBufferedImage(pixelArray, imageHeight, imageWidth);
  }
}
