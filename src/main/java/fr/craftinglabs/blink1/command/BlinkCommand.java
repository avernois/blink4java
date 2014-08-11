package fr.craftinglabs.blink1.command;

public class BlinkCommand {

    public static final int REPORT_ID = 0x01;
    protected byte[] command = new byte[8];

    public BlinkCommand(BlinkCommandType type) {
        command[0] = REPORT_ID;
        command[1] = type.asByte();
    }

    public byte[] asBytes() {
        return command;
    }
}
