package model.images;

/**
 * This enum lists out different kernels for different filters. The kernels can be retrieved with
 * {@code getKernel()} below.
 */
public enum Filter {
  SHARPEN(new float[][]{
          {-0.125f, -0.125f, -0.125f, -0.125f, -0.125f},
          {-0.125f, +0.250f, +0.250f, +0.250f, -0.125f},
          {-0.125f, +0.250f, +1.000f, +0.250f, -0.125f},
          {-0.125f, +0.250f, +0.250f, +0.250f, -0.125f},
          {-0.125f, -0.125f, -0.125f, -0.125f, -0.125f}
  }),
  BLUR(new float[][]{
          {0.0625f, 0.1250f, 0.0625f},
          {0.1250f, 0.2500f, 0.1250f},
          {0.0625f, 0.1250f, 0.0625f}
  }),
  DITHER(new float[][]{
          {1.0000f, 1.0000f, 0.1875f},
          {1.0000f, 1.0000f, 0.3125f},
          {1.0000f, 0.4375f, 0.0625f}
  }),
  MOSAIC(new float[][]{
          {1.0000f}
  });
  private float[][] kernel;

  Filter(float[][] kernel) {
    this.kernel = kernel;
  }

  /**
   * Can be used to retrieve the kernel values.
   *
   * @return 2D kernel matrix, as 2D Integer array.
   */
  public float[][] getKernel() {
    return this.kernel;
  }
}