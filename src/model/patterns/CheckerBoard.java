package model.patterns;

import java.awt.image.BufferedImage;

import model.AbstractGenerator;

/**
 * This class implements the method defined in {@code Generator} to create a checkerboard pattern.
 */
public class CheckerBoard extends AbstractGenerator {
  private final int imageWidth;
  private final Pattern type;

  /**
   * The Builder method to invoke the builder class constructor to initialize all the input
   * variables.
   *
   * @return new CheckerBoardBuilder object.
   */
  public static CheckerBoardBuilder getBuilder() {
    return new CheckerBoardBuilder();
  }

  private CheckerBoard(int imageWidth) {
    this.imageWidth = imageWidth;
    this.type = Pattern.CHECKERBOARD;
  }

  @Override
  public BufferedImage generateImage() {
    int size = this.imageWidth / 8;
    int[][][] outputImage = new int[imageWidth][imageWidth][3];

    for (int i = 0; i < imageWidth; i++) {
      for (int j = 0; j < imageWidth; j++) {
        if ((i / size) % 2 == (j / size) % 2) {
          outputImage[j][i] = type.getKernel()[0];
        }
      }
    }
    return generateBufferedImage(outputImage, imageWidth, imageWidth);
  }

  /**
   * The Builder class to initialize {@code CheckerBoard} instance with a variety of possible input
   * combinations.
   */
  public static class CheckerBoardBuilder {
    private int imageWidth;

    /**
     * The constructor of the builder class to initialize all the variables with default values.
     */
    private CheckerBoardBuilder() {
      this.imageWidth = 800;
    }

    /**
     * Method to initialize the input width only.
     *
     * @param imageWidth the width to be generated with, as an int.
     * @return the CheckerBoardBuilder object.
     */
    public CheckerBoardBuilder imageWidth(int imageWidth) {
      this.imageWidth = imageWidth;
      return this;
    }

    /**
     * Method to initialize the size of one square only.
     *
     * @param squareSize square's one side's size only, as an int.
     * @return the CheckerBoardBuilder object.
     */
    public CheckerBoardBuilder squareSize(int squareSize) {
      this.imageWidth = squareSize * 8;
      return this;
    }

    /**
     * Method to call the {@code CheckerBoard} constructor with initialized input variables.
     *
     * @return the CheckerBoard object.
     */
    public CheckerBoard build() {
      return new CheckerBoard(imageWidth);
    }
  }
}