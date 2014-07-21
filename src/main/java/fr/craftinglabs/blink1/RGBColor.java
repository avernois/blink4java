package fr.craftinglabs.blink1;

public class RGBColor {
    private final int red;
    private final int green;
    private final int blue;

    public RGBColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int red() {
        return red;
    }

    public int green() {
        return green;
    }

    public int blue() {
        return blue;
    }

    public byte redAsByte() {
        return (byte) red;
    }

    public byte greenAsByte() {

        return (byte) green;
    }

    public byte blueAsByte() {
        return (byte) blue;
    }
}
