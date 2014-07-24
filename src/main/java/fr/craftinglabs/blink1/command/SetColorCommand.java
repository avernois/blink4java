package fr.craftinglabs.blink1.command;

import fr.craftinglabs.blink1.RGBColor;

public class SetColorCommand extends ChangeColorCommand {

    private static final byte SET_COLOR = (byte) 'n';

	public SetColorCommand(RGBColor rgbColor) {
		super(rgbColor);
		command[1] = SET_COLOR;
	}
}
