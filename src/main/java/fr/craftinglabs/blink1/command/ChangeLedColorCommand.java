package fr.craftinglabs.blink1.command;

import fr.craftinglabs.blink1.BlinkLeds;
import fr.craftinglabs.blink1.RGBColor;

public abstract class ChangeLedColorCommand {
    public static final int REPORT_ID = 0x01;
    protected byte[] command = new byte[8];
    
    public  ChangeLedColorCommand(RGBColor color, BlinkLeds led) {
        command[0] = REPORT_ID;
        command[2] = color.redAsByte();
        command[3] = color.greenAsByte();
        command[4] = color.blueAsByte();
        
        command[7] = led.asByte();
    }

    public byte[] asBytes() {
        return command;
    }
}
