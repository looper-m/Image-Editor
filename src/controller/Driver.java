package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.patterns.CheckerBoard;
import model.Generator;
import model.images.FilterImage;
import model.images.Filter;
import model.flags.France;
import model.flags.Greece;
import model.patterns.Orientation;
import model.patterns.Rainbow;
import model.flags.Switzerland;
import model.images.TransformImage;
import model.images.Transform;

/**
 * This class represents the main controller. It holds one method, {@code main()}, for now.
 */
public class Driver {

  /**
   * The main method that is auto invoked. It generates and alters in a variety of ways by invoking
   * corresponding model classes.
   *
   * @param args None for now.
   * @throws IOException if there is an error reading the input file.
   */
  public static void main(String[] args) throws IOException {
    File sourceImage = new File("res/mojo.jpg");

    try {
      // Sharpen the image
      BufferedImage img = ImageIO.read(sourceImage);
      Generator sharpen = new FilterImage(img, Filter.SHARPEN);
      img = sharpen.generateImage();
      ImageIO.write(img, "bmp", new File("res/sharpen.bmp"));

      // Blur the image
      sourceImage = new File("res/mojo-bmp.bmp");
      img = ImageIO.read(sourceImage);
      Generator blur = new FilterImage(img, Filter.BLUR);
      img = blur.generateImage();
      ImageIO.write(img, "png", new File("res/blur.png"));

      // Set the image with sepia tone
      sourceImage = new File("res/mojo-png.png");
      img = ImageIO.read(sourceImage);
      Generator sepia = new TransformImage(img, Transform.SEPIA);
      img = sepia.generateImage();
      ImageIO.write(img, "png", new File("res/sepia.png"));

      // Set the image with greyscale tone
      img = ImageIO.read(sourceImage);
      Generator greyscale = new TransformImage(img, Transform.GREYSCALE);
      img = greyscale.generateImage();
      ImageIO.write(img, "png", new File("res/greyscale.png"));

      // Draw a vertical rainbow
      // Initialize with width/ height/ orientation. Defaults are 700/ 700/ Horizontal respectively
      Generator rainbowV =
              Rainbow.getBuilder().imageHeight(560).orientation(Orientation.VERTICAL).build();
      img = rainbowV.generateImage();
      ImageIO.write(img, "png", new File("res/rainbowVertical.png"));

      // Draw a horizontal rainbow
      // Initialize with width/ height/ orientation. Defaults are 700/ 700/ Horizontal respectively
      Generator rainbowH =
              Rainbow.getBuilder().imageWidth(560).orientation(Orientation.HORIZONTAL).build();
      img = rainbowH.generateImage();
      ImageIO.write(img, "png", new File("res/rainbowHorizontal.bmp"));

      // Generate a checkerboard pattern
      // Initialize with square size or image width. Defaults are 800 as image width
      Generator checkerboard = CheckerBoard.getBuilder().squareSize(80).build();
      img = checkerboard.generateImage();
      ImageIO.write(img, "png", new File("res/checkerboard.png"));

      // Draw the flag of France
      Generator france = new France(600);  // must be divisible by 3
      img = france.generateImage();
      ImageIO.write(img, "png", new File("res/france.png"));

      // Draw the flag of Greece
      Generator greece = new Greece(1000);  // divisible by 10
      img = greece.generateImage();
      ImageIO.write(img, "png", new File("res/greece.bmp"));

      // Draw the flag of Switzerland
      Generator swiss = new Switzerland(502);
      img = swiss.generateImage();
      ImageIO.write(img, "png", new File("res/swiss.jpg"));

    } catch (IOException e) {
      throw new IOException("Check the validity of the input file!");
    }
  }
}
