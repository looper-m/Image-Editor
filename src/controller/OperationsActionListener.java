package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

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

public class OperationsActionListener implements ActionListener {
  private GUIController control;

  public OperationsActionListener(GUIController control) {
    this.control = control;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();

    // todo redundant code
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

    Function<String, GeneratorCommand> generatorCommand = knownCommands.getOrDefault(command,
            null);
    if (generatorCommand == null) {
      //todo throwing exception not enough, need dialog alert
    }
    String argumentData = "";
    GeneratorCommand c = generatorCommand.apply(argumentData);
    control.view.setImageOnCanvas(c.executeCommand(control.view.getImageOnCanvas()));
  }
}
