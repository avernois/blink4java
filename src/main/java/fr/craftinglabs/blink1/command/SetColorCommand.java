package fr.craftinglabs.blink1.command;

import fr.craftinglabs.blink1.BlinkLed;
import fr.craftinglabs.blink1.RGBColor;

public class SetColorCommand extends ChangeLedColorCommand {

    private static final byte SET_COLOR = (byte) 'n';

	public SetColorCommand(RGBColor rgbColor, BlinkLed led) {
		super(rgbColor, led);
		command[1] = SET_COLOR;
	}
}
