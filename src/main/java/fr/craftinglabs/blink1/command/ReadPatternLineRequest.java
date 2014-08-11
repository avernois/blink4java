package fr.craftinglabs.blink1.command;

public class ReadPatternLineRequest extends BlinkCommand {

    public ReadPatternLineRequest(int position) {
        super(BlinkCommandType.READ_PATTERN_LINE);
        command[2] = 0;
        command[3] = 0;
        command[4] = 0;
        command[5] = 0;
        command[6] = 0;
        command[7] = (byte) position;
    }

}
