package model.patterns;

import java.awt.image.BufferedImage;

import model.AbstractGeneratorModel;

/**
 * This class implements the method defined in {@code GeneratorModel} to create a vertical or horizontal
 * rainbow image.
 */
public class Rainbow extends AbstractGeneratorModel {
  private final int imageWidth;
  private final int imageHeight;
  private final Orientation orientation;
  private final Pattern type = Pattern.RAINBOW;

  /**
   * The Builder method to invoke the builder class constructor to initialize all the input
   * variables.
   *
   * @return new RainbowBuilder object.
   */
  public static RainbowBuilder getBuilder() {
    return new RainbowBuilder();
  }

  private Rainbow(int imageWidth, int imageHeight, Orientation orientation) {
    this.imageWidth = imageWidth;
    this.imageHeight = imageHeight;
    this.orientation = orientation;
  }

  @Override
  public BufferedImage generateImage() {
    int[][][] outputImage = new int[imageWidth][imageHeight][3];
    int k = 0;

    int param = imageHeight;
    if (orientation == Orientation.VERTICAL) {
      param = imageWidth;
    }
    for (int y = 0; y < imageHeight; y++) {
      for (int x = 0; x < imageWidth; x++) {
        outputImage[x][y] = type.getKernel()[(k / (param / 7)) % 7];
        if (orientation == Orientation.VERTICAL) {
          k++;
        }
      }
      if (orientation == Orientation.HORIZONTAL) {
        k++;
      }
    }
    return generateBufferedImage(outputImage, imageHeight, imageWidth);
  }

  /**
   * The Builder class to initialize {@code Rainbow} instance with a variety of possible input
   * combinations.
   */
  public static class RainbowBuilder {
    private int imageWidth;
    private int imageHeight;
    private Orientation orientation;

    /**
     * The constructor of the builder class to initialize all the variables with default values.
     */
    private RainbowBuilder() {
      this.imageWidth = 700;
      this.imageHeight = 700;
      this.orientation = Orientation.HORIZONTAL;
    }

    /**
     * Method to initialize the input width only.
     *
     * @param imageWidth the width to be generated with, as an int.
     * @return the RainbowBuilder object.
     */
    public RainbowBuilder imageWidth(int imageWidth) {
      this.imageWidth = imageWidth;
      return this;
    }

    /**
     * Method to initialize the input height only.
     *
     * @param imageHeight the height to be generated with, as an int.
     * @return the RainbowBuilder object.
     */
    public RainbowBuilder imageHeight(int imageHeight) {
      this.imageHeight = imageHeight;
      return this;
    }

    /**
     * Method to initialize the input orientation only.
     *
     * @param orientation the orientation to be generated with, as an Orientation type.
     * @return the RainbowBuilder object.
     */
    public RainbowBuilder orientation(Orientation orientation) {
      this.orientation = orientation;
      return this;
    }

    /**
     * Method to call the {@code Rainbow} constructor with initialized input variables.
     *
     * @return the Rainbow object.
     */
    public Rainbow build() {
      return new Rainbow(imageWidth, imageHeight, orientation);
    }
  }
}