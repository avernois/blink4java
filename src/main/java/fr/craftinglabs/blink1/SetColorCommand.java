package fr.craftinglabs.blink1;

/**
 * Created by avernois on 21/07/14.
 */
public class SetColorCommand extends WriteCommand {

    private static final byte SET_COLOR = (byte) 'n';

	public SetColorCommand(RGBColor rgbColor) {
        command[1] = SET_COLOR;
        command[2] = rgbColor.redAsByte();
        command[3] = rgbColor.greenAsByte();
        command[4] = rgbColor.blueAsByte();
    }
}
