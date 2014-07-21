package fr.craftinglabs.blink1;

public class FadeToCommand extends WriteCommand {

    public static final byte FADE_TO = (byte) 'c';

    public FadeToCommand(RGBColor rgbColor, int fadeTime, BlinkLeds led) {
    	command[1] = FADE_TO;
        command[2] = rgbColor.redAsByte();
        command[3] = rgbColor.greenAsByte();
        command[4] = rgbColor.blueAsByte();
        command[5] = lowByte(fadeTime / 10);
        command[6] = highByte(fadeTime / 10);
        command[7] = led.asByte();
	}

	private byte highByte(int fadeTime) {
        return (byte) (fadeTime & 0xff);
    }

    private byte lowByte(int fadeTime) {
        return (byte) (fadeTime >> 8);
    }
}
