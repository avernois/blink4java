package fr.craftinglabs.blink1.command;

import fr.craftinglabs.blink1.RGBColor;

public abstract class ChangeColorCommand extends BlinkCommand {
    public ChangeColorCommand(RGBColor color) {
        command[2] = color.redAsByte();
        command[3] = color.greenAsByte();
        command[4] = color.blueAsByte();
    }
}
