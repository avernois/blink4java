package fr.craftinglabs.blink1.command;

import fr.craftinglabs.blink1.RGBColor;

public abstract class ChangeColorCommand extends BlinkCommand {

    public ChangeColorCommand(BlinkCommandType type, RGBColor color) {
        super(type);
        command[2] = color.redAsByte();
        command[3] = color.greenAsByte();
        command[4] = color.blueAsByte();
    }
}
