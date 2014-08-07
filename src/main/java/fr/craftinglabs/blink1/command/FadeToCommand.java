package fr.craftinglabs.blink1.command;

import fr.craftinglabs.blink1.BlinkLed;
import fr.craftinglabs.blink1.RGBColor;

public class FadeToCommand extends ChangeColorCommand {

    public static final byte FADE_TO = (byte) 'c';

    public FadeToCommand(RGBColor rgbColor, int fadeTime, BlinkLed led) {
    	super(rgbColor);
    	command[1] = FADE_TO;
        
        command[5] = highByte(fadeTime / 10);
        command[6] = lowByte(fadeTime / 10);
        
        command[7] = led.asByte();
	}

	private byte lowByte(int fadeTime) {
        return (byte) (fadeTime & 0xff);
    }

    private byte highByte(int fadeTime) {
        return (byte) (fadeTime >> 8);
    }
}
