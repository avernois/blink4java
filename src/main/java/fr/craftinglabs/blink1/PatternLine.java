package fr.craftinglabs.blink1;

public class PatternLine {

    private RGBColor color;
    private int fadeTime;
    private int position;

    public PatternLine(RGBColor color, int fadeTime, int position) {
        this.color = color;
        this.fadeTime = fadeTime;
        this.position = position;
        }

    public RGBColor color() {
        return color;
    }

    public int fadeTime() {
        return fadeTime;
    }

    public int position() {
        return position;
    }
}
