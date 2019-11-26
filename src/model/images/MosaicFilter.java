package model.images;

import javafx.util.Pair;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * This class extends {@code FilterImage} class to provide somewhat variable implementation of the
 * {@code generateImage} function for mosaic-ing an input image based on argument seed value.
 */
public class MosaicFilter extends FilterImage {
  private HashMap<Pair<Integer, Integer>, ArrayList<Pair<Integer, Integer>>> clusters;

  /**
   * This constructor creates the image pixel array to apply the filter on. The filter is received
   * as an enum variable through the second parameter.
   *
   * @param image the input image, as BufferedImage type.
   * @param type  the type of filter to use- MOSAIC(dummy).
   * @param seeds the seeds for the mosaic-ing operation.
   */
  public MosaicFilter(BufferedImage image, Filter type, int seeds) {
    super(image, type);
    if (seeds <= 0) {
      throw new IllegalArgumentException(String.format("Bad seed value: %d", seeds));
    }
    Random r = new Random();
    clusters = new HashMap<>();

    for (int i = 0; i < seeds; i++) {
      while (true) {
        int randX = r.nextInt(imageWidth + 1);
        int randY = r.nextInt(imageHeight + 1);

        Pair<Integer, Integer> seed = new Pair<>(randX, randY);
        if (!clusters.containsKey(seed)) {
          clusters.put(seed, new ArrayList<>());
          break;
        }

      }
    }
  }

  private double computeDistance(int x1, int y1, int x2, int y2) {
    return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
  }

  private Pair<Integer, Integer> getClosestSeed(int x, int y) {
    double maxDist = Double.MAX_VALUE;
    Pair<Integer, Integer> closestSeed = null;
    for (Pair<Integer, Integer> seed : clusters.keySet()) {
      double dist = computeDistance(seed.getKey(), seed.getValue(), x, y);
      if (dist < maxDist) {
        closestSeed = new Pair<>(seed.getKey(), seed.getValue());
        maxDist = dist;
      }
    }
    return closestSeed;
  }

  @Override
  public BufferedImage generateImage() {
    HashMap<Pair<Integer, Integer>, ArrayList<Integer>> seedAveragePixelMap = new HashMap<>();
    //Computing all the clusters
    for (int y = 0; y < imageHeight; y++) {
      for (int x = 0; x < imageWidth; x++) {
        clusters.get(getClosestSeed(x, y)).add(new Pair<>(x, y));
      }
    }

    for (Map.Entry<Pair<Integer, Integer>, ArrayList<Pair<Integer, Integer>>> entry :
            clusters.entrySet()) {
      ArrayList<Pair<Integer, Integer>> pixels = entry.getValue();
      List<Integer> redValues = new ArrayList<>();
      List<Integer> greenValues = new ArrayList<>();
      List<Integer> blueValues = new ArrayList<>();

      for (Pair<Integer, Integer> pixel : pixels) {
        redValues.add(pixelArray[pixel.getKey()][pixel.getValue()][0]);
        greenValues.add(pixelArray[pixel.getKey()][pixel.getValue()][1]);
        blueValues.add(pixelArray[pixel.getKey()][pixel.getValue()][2]);
      }
      int avg_red = (int) redValues.stream().mapToInt(i -> i).average().orElse(0);
      int avg_green = (int) greenValues.stream().mapToInt(i -> i).average().orElse(0);
      int avg_blue = (int) blueValues.stream().mapToInt(i -> i).average().orElse(0);
      seedAveragePixelMap.put(entry.getKey(), new ArrayList<Integer>() {{
          add(avg_red);
          add(avg_green);
          add(avg_blue);
        }});
    }

    for (int y = 0; y < imageHeight; y++) {
      for (int x = 0; x < imageWidth; x++) {
        ArrayList<Integer> rgb = seedAveragePixelMap.get(getClosestSeed(x, y));
        pixelArray[x][y][0] = rgb.get(0);
        pixelArray[x][y][1] = rgb.get(1);
        pixelArray[x][y][2] = rgb.get(2);
      }
    }
    return generateBufferedImage(pixelArray, imageHeight, imageWidth);
  }
}