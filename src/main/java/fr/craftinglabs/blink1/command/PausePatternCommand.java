package fr.craftinglabs.blink1.command;

public class PausePatternCommand extends BlinkCommand {

    private static final byte PAUSE = 0;

    public PausePatternCommand() {
        command[1] = (byte) 'p';
        command[2] = PAUSE;
    }
}
