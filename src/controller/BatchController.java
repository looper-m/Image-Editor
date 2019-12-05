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
 * This class represents the main controller. It invokes calls to various model classes depending on
 * the operation to be performed.
 */
public class BatchController {
  /**
   * This method can be invoked with an input {@code Readable} object containing commands to modify
   * or create images.
   *
   * @param input input command script, as a readable type
   * @throws IOException if there is an exception reading the file
   */
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
      if (splitData[0].equals("")) {
        continue;
      }

      String command = splitData[0];
      String argumentData = "";
      System.out.println(command + " " + argumentData);

      if (command.equalsIgnoreCase("q")
              || command.equalsIgnoreCase("quit")) {
        return;
      } else if (command.equalsIgnoreCase("load")) {
        try {
          if (splitData.length < 2) {
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
        if (splitData.length < 2) {
          throw new IllegalArgumentException("No save path/file!");
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
        if (splitData.length == 2) {
          argumentData = splitData[1].trim().toLowerCase();
        }
        GeneratorCommand c = generatorCommand.apply(argumentData);
        outputImage = c.executeCommand(inputImage);
      }
    }
  }
}
