package fr.craftinglabs.blink1.command;

import fr.craftinglabs.blink1.RGBColor;

public class SetColorCommand extends ChangeColorCommand {

    public SetColorCommand(RGBColor rgbColor) {
        super(BlinkCommandType.SET_COLOR, rgbColor);
    }
}
