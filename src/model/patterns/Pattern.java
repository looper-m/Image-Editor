package model.patterns;

/**
 * This enum lists out different color sets for different patterns. The sets can be retrieved with
 * {@code getKernel()} below.
 */
public enum Pattern {
  CHECKERBOARD(new int[][]{
          {255, 255, 255},
          {0, 0, 0}
  }),
  RAINBOW(new int[][]{
          {255, 0, 0},
          {255, 127, 0},
          {255, 255, 0},
          {0, 255, 0},
          {0, 0, 255},
          {46, 43, 95},
          {139, 0, 255}
  });

  private int[][] patternColor;

  Pattern(int[][] patternColor) {
    this.patternColor = patternColor;
  }

  public int[][] getKernel() {
    return this.patternColor;
  }
}