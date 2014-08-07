package fr.craftinglabs.blink1.command;

import fr.craftinglabs.blink1.PatternLine;

public class SetColorPatternLineCommand extends BlinkCommand {

    public SetColorPatternLineCommand(PatternLine patternLine) {
        command[1] = (byte) 'P';
        command[2] = patternLine.color().redAsByte();
        command[3] = patternLine.color().greenAsByte();
        command[4] = patternLine.color().blueAsByte();
        command[5] = (byte) ((patternLine.fadeTime() / 10) >> 8);
        command[6] = (byte) ((patternLine.fadeTime() / 10) & 0xff);
        command[7] = (byte) (patternLine.position());
    }
}
