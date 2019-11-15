package model.images;

/**
 * This enum lists out different kernels for different transformations. The kernels can be retrieved
 * with {@code getKernel()} below.
 */
public enum Transform {
  SEPIA(new float[][]{
          {0.393f, 0.769f, 0.189f},
          {0.349f, 0.686f, 0.168f},
          {0.272f, 0.534f, 0.131f}
  }),
  GREYSCALE(new float[][]{
          {0.2126f, 0.7152f, 0.0722f},
          {0.2126f, 0.7152f, 0.0722f},
          {0.2126f, 0.7152f, 0.0722f}
  });
  private float[][] kernel;

  Transform(float[][] kernel) {
    this.kernel = kernel;
  }

  public float[][] getKernel() {
    return this.kernel;
  }
}
