package fr.craftinglabs.blink1.command;


public class ReadColorPatternLineRequest extends BlinkCommand {

	public ReadColorPatternLineRequest(int position) {
		command[1] = (byte) 'R';
		command[2] = 0;
		command[3] = 0;
		command[4] = 0;
		command[5] = 0;
		command[6] = 0;
		command[7] = (byte) position;
	}

}
