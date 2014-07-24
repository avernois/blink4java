package fr.craftinglabs.blink1.command;

import fr.craftinglabs.blink1.RGBColor;

public abstract class ChangeColorCommand {
    public static final int REPORT_ID = 0x01;
    protected byte[] command = new byte[8];
    
    public  ChangeColorCommand(RGBColor color) {
        command[0] = REPORT_ID;
        command[2] = color.redAsByte();
        command[3] = color.greenAsByte();
        command[4] = color.blueAsByte();
    }

    public byte[] asBytes() {
        return command;
    }
}
