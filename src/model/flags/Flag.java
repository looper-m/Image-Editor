package model.flags;

/**
 * This enum lists out all the color sets for each nation's flag. They can be retrieved with {@code
 * getKernel()} below.
 */
public enum Flag {
  FRENCH(new int[][]{
          {239, 65, 53},
          {255, 255, 255},
          {0, 85, 164}
  }),
  SWISS(new int[][]{
          {255, 0, 0},
          {255, 255, 255}
  }),
  GREEK(new int[][]{
          {13, 94, 175},
          {255, 255, 255}
  });

  private int[][] flagColor;

  Flag(int[][] flagColor) {
    this.flagColor = flagColor;
  }

  /**
   * Can be used to retrieve each country's color sets.
   *
   * @return 2D color matrix, as 2D Integer array.
   */
  public int[][] getKernel() {
    return this.flagColor;
  }
}