package fr.craftinglabs.blink1;

public class SetColorCommand extends WriteCommand {

    private static final byte SET_COLOR = (byte) 'n';

	public SetColorCommand(RGBColor rgbColor) {
        this(rgbColor, BlinkLeds.ALL_LEDS);
    }

	public SetColorCommand(RGBColor rgbColor, BlinkLeds led) {
		command[1] = SET_COLOR;
        command[2] = rgbColor.redAsByte();
        command[3] = rgbColor.greenAsByte();
        command[4] = rgbColor.blueAsByte();
        command[7] = led.asByte();
	}
}
