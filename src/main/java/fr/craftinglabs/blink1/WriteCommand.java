package fr.craftinglabs.blink1;

/**
 * Created by avernois on 21/07/14.
 */
public abstract class WriteCommand {
    public static final int REPORT_ID = 0x01;
    protected byte[] command = new byte[8];

    public  WriteCommand() {
        command[0] = REPORT_ID;
    }

    public byte[] asBytes() {
        return command;
    }
}
