package fr.craftinglabs.blink1.command;

public class PausePatternCommand extends BlinkCommand {

    public PausePatternCommand() {
        command[1] = (byte) 'p';
        command[2] = 0;
    }
}
