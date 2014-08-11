package fr.craftinglabs.blink1.command;

public class PausePatternCommand extends BlinkCommand {

    private static final byte PAUSE = 0;

    public PausePatternCommand() {
        super(BlinkCommandType.PAUSE_PATTERN);
        command[2] = PAUSE;
    }
}
