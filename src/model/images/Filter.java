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