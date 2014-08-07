package fr.craftinglabs.blink1;

public enum BlinkLed {
    ALL_LEDS((byte) 0x00), LED_1((byte) 0x01), LED_2((byte) 0x02);

    private byte id;
    private BlinkLed(byte id) {
        this.id = id;
    }
    
    public byte asByte() {
        return this.id;
    }
}
