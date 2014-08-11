package fr.craftinglabs.blink1.command;

public enum BlinkCommandType {
    FADE_TO((byte) 'c'),
    PAUSE_PATTERN((byte) 'p'),
    PLAY_PATTERN((byte) 'p'),
    READ_COLOR((byte) 'r'),
    READ_PATTERN_LINE((byte) 'R'),
    SAVE_PATTERN((byte) 'W'),
    SET_COLOR((byte) 'n'),
    SET_PATTERN_LINE((byte) 'P');

    byte command;

    private BlinkCommandType(byte command) {
        this.command = command;
    }

    public byte asByte() {
        return command;
    }
}
