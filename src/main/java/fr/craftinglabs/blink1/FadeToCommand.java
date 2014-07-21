package fr.craftinglabs.blink1;

public class FadeToCommand extends ChangeLedColorCommand {

    public static final byte FADE_TO = (byte) 'c';

    public FadeToCommand(RGBColor rgbColor, int fadeTime, BlinkLeds led) {
    	super(rgbColor, led);
    	command[1] = FADE_TO;
        
        command[5] = lowByte(fadeTime / 10);
        command[6] = highByte(fadeTime / 10);        
	}

	private byte highByte(int fadeTime) {
        return (byte) (fadeTime & 0xff);
    }

    private byte lowByte(int fadeTime) {
        return (byte) (fadeTime >> 8);
    }
}
