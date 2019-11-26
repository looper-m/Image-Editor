package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import javax.imageio.ImageIO;

import controller.command.BlurCommand;
import controller.command.CheckerBoardCommand;
import controller.command.DitherCommand;
import controller.command.FranceFlagCommand;
import controller.command.GreeceFlagCommand;
import controller.command.GreyscaleCommand;
import controller.command.MosaicCommand;
import controller.command.RainbowCommand;
import controller.command.SepiaCommand;
import controller.command.SharpenCommand;
import controller.command.SwitzerlandFlagCommand;

/**
 * This class represents the main controller. It holds one method, {@code main()}, for now.
 */
public class Controller {

  public void execute(Readable input) throws IOException {
    if (input == null) {
      throw new IllegalArgumentException("Bad input: null");
    }
    Scanner scanInput = new Scanner(input);
    BufferedImage inputImage = null;
    BufferedImage outputImage = null;

    Map<String, Function<String, GeneratorCommand>> knownCommands = new HashMap<>();
    knownCommands.put("blur", BlurCommand::new);
    knownCommands.put("sharpen", SharpenCommand::new);
    knownCommands.put("sepia", SepiaCommand::new);
    knownCommands.put("greyscale", GreyscaleCommand::new);
    knownCommands.put("mosaic", MosaicCommand::new);
    knownCommands.put("dither", DitherCommand::new);
    knownCommands.put("france", FranceFlagCommand::new);
    knownCommands.put("greece", GreeceFlagCommand::new);
    knownCommands.put("switzerland", SwitzerlandFlagCommand::new);
    knownCommands.put("checkerboard", CheckerBoardCommand::new);
    knownCommands.put("rainbow", RainbowCommand::new);

    while (scanInput.hasNext()) {
      String data = scanInput.nextLine();
      String[] splitData = data.split("\\s+", 2);
      String command = splitData[0];
      String argumentData = "";
      System.out.println(command + " " + argumentData);

      if (command.equalsIgnoreCase("q") ||
              command.equalsIgnoreCase("quit")) {
        return;
      } else if (command.equalsIgnoreCase("load")) {
        try {
          if(splitData.length < 2) {
            throw new IllegalArgumentException("No load path/file!");
          }
          argumentData = splitData[1].trim();
          inputImage = ImageIO.read(new File(argumentData));
        } catch (IOException e) {
          throw new IllegalArgumentException("Bad file or filepath!");
        }
      } else if (command.equalsIgnoreCase("save")) {
        if (outputImage == null) {
          throw new IllegalArgumentException("No output to write!");
        }
        if(splitData.length < 2) {
          throw new IllegalArgumentException("No load path/file!");
        }
        argumentData = splitData[1].trim();
        String imageFormat = argumentData.substring(argumentData.length() - 3);
        if (imageFormat.equals("jpg") || imageFormat.equals("png") || imageFormat.equals("bmp")) {
          ImageIO.write(outputImage, imageFormat, new File(argumentData));
        } else {
          throw new IllegalArgumentException(String.format("Bad saving format: %s", imageFormat));
        }
      } else {
        Function<String, GeneratorCommand> generatorCommand = knownCommands.getOrDefault(command,
                null);
        if (generatorCommand == null) {
          throw new IllegalArgumentException(String.format("Bad command: %s", command));
        }
        if(splitData.length == 2) {
          argumentData = splitData[1].trim().toLowerCase();
        }
        GeneratorCommand c = generatorCommand.apply(argumentData);
        outputImage = c.executeCommand(inputImage);
      }
    }
  }

  /**
   * The main method that is auto invoked. It generates and alters in a variety of ways by invoking
   * corresponding model classes.
   *
   * @param args None for now.
   * @throws IOException if there is an error reading the input file.
   */
  public static void main(String[] args) throws IOException {
//    File sourceImage = new File("res/mojo.jpg");
    new Controller().execute(new FileReader(args[0]));
    // Dithering the image
//    BufferedImage img1 = ImageIO.read(sourceImage);
//    GeneratorModel dither = new DitherFilter(img1, Filter.DITHER);
//    img1 = dither.generateImage();
//    ImageIO.write(img1, "jpg", new File("res/dither.jpg"));

//    System.out.println(Math.round(240/255));
//    return;

//    try {
//      // Sharpen the image
//      BufferedImage img = ImageIO.read(sourceImage);
//      System.out.println(img);
//      GeneratorModel sharpen = new FilterImage(img, Filter.SHARPEN);
//      img = sharpen.generateImage();
//      ImageIO.write(img, "bmp", new File("res/sharpen.bmp"));
//
//      // Blur the image
//      sourceImage = new File("res/mojo-bmp.bmp");
//      img = ImageIO.read(sourceImage);
//      GeneratorModel blur = new FilterImage(img, Filter.BLUR);
//      img = blur.generateImage();
//      ImageIO.write(img, "png", new File("res/blur.png"));
//
//      // Set the image with sepia tone
//      sourceImage = new File("res/mojo-png.png");
//      img = ImageIO.read(sourceImage);
//      GeneratorModel sepia = new TransformImage(img, Transform.SEPIA);
//      img = sepia.generateImage();
//      ImageIO.write(img, "png", new File("res/sepia.png"));
//
//      // Set the image with greyscale tone
//      img = ImageIO.read(sourceImage);
//      GeneratorModel greyscale = new TransformImage(img, Transform.GREYSCALE);
//      img = greyscale.generateImage();
//      ImageIO.write(img, "png", new File("res/greyscale.png"));

    // Draw a vertical rainbow
    // Initialize with width/ height/ orientation. Defaults are 700/ 700/ Horizontal
//      respectively
//      GeneratorModel rainbowV =
//              Rainbow.getBuilder().imageHeight(560).imageHeight(200).orientation(Orientation
//              .VERTICAL).build();
//      img = rainbowV.generateImage();
//      ImageIO.write(img, "png", new File("res/rainbowVertical.png"));
//
//      // Draw a horizontal rainbow
//      // Initialize with width/ height/ orientation. Defaults are 700/ 700/ Horizontal
////      respectively
//      GeneratorModel rainbowH =
//              Rainbow.getBuilder().imageWidth(560).orientation(Orientation.HORIZONTAL).build();
//      img = rainbowH.generateImage();
//      ImageIO.write(img, "png", new File("res/rainbowHorizontal.bmp"));
//
////      // Generate a checkerboard pattern
//      // Initialize with square size or image width. Defaults are 800 as image width
//      GeneratorModel checkerboard = CheckerBoard.getBuilder().squareSize(80).build();
//      img = checkerboard.generateImage();
//      ImageIO.write(img, "png", new File("res/checkerboard.png"));
//
//      // Draw the flag of France
//      GeneratorModel france = new France(600);  // must be divisible by 3
//      img = france.generateImage();
//      ImageIO.write(img, "png", new File("res/france.png"));
//
//      // Draw the flag of Greece
//      GeneratorModel greece = new Greece(1000);  // divisible by 10
//      img = greece.generateImage();
//      ImageIO.write(img, "png", new File("res/greece.bmp"));
//
//      // Draw the flag of Switzerland
//      GeneratorModel swiss = new Switzerland(502);
//      img = swiss.generateImage();
//      ImageIO.write(img, "png", new File("res/swiss.jpg"));

//    } catch (IOException e) {
//      throw new IOException("Check the validity of the input file!");
//    }
  }
}
