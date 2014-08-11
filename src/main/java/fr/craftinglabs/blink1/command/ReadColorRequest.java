package fr.craftinglabs.blink1.command;

import fr.craftinglabs.blink1.BlinkLed;

public class ReadColorRequest extends BlinkCommand {

    public ReadColorRequest(BlinkLed led) {
        super(BlinkCommandType.READ_COLOR);

        command[2] = 0x00;
        command[3] = 0x00;
        command[4] = 0x00;
        command[5] = 0x00;
        command[6] = 0x00;
        command[7] = led.asByte();
    }

    public byte[] asBytes() {
        return command;
    }
}
