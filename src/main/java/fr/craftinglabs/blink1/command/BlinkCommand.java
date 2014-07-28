package fr.craftinglabs.blink1.command;

public class BlinkCommand {

	public static final int REPORT_ID = 0x01;
	protected byte[] command = new byte[8];

	public BlinkCommand() {
		command[0] = REPORT_ID;
	}

	public byte[] asBytes() {
	    return command;
	}

}