package fr.craftinglabs.blink1;

public class SetColorCommand extends ChangeLedColorCommand {

    private static final byte SET_COLOR = (byte) 'n';

	public SetColorCommand(RGBColor rgbColor, BlinkLeds led) {
		super(rgbColor, led);
		command[1] = SET_COLOR;
	}
}
