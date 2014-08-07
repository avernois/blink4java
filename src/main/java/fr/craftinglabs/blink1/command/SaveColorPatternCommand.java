package fr.craftinglabs.blink1.command;

public class SaveColorPatternCommand extends BlinkCommand {

    public SaveColorPatternCommand() {
        command[1] = 'W';
        command[2] = (byte) 0xBE;
        command[3] = (byte) 0xEF;
        command[4] = (byte) 0xCA;
        command[5] = (byte) 0xFE;
        command[6] = (byte) 0;
        command[7] = (byte) 0;
    }
}
