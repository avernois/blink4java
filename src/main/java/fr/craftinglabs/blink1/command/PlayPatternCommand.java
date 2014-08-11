package fr.craftinglabs.blink1.command;

public class PlayPatternCommand extends BlinkCommand {

    private static final byte PLAY = 1;
    private static final int NO_REPEAT = 0;
    private static final int END_WITH_LAST_PATTERN_LINE = 0;
    private static final int START_AT_FIRST_PATTERN_LINE = 0;

    public PlayPatternCommand() {
        this(START_AT_FIRST_PATTERN_LINE, END_WITH_LAST_PATTERN_LINE, NO_REPEAT);
    }

    public PlayPatternCommand(int startingPoint, int endingPoint, int repeat) {
        command[1] = (byte) 'p';
        command[2] = (byte) PLAY;

        command[3] = (byte) startingPoint;
        command[4] = (byte) endingPoint;
        command[5] = (byte) repeat;
        
    }
}
