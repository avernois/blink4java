package fr.craftinglabs.blink1.command;

import org.junit.Test;

import fr.craftinglabs.blink1.BlinkLed;
import fr.craftinglabs.blink1.RGBColor;
import fr.craftinglabs.blink1.command.ChangeLedColorCommand;
import fr.craftinglabs.blink1.command.SetColorCommand;
import static org.junit.Assert.assertEquals;

public class SetColorCommandTest extends ChangeLedColorCommandTest {

    @Test public void
    should_have_command_action_set_to_n() {
        SetColorCommand command = new SetColorCommand(new RGBColor(0, 0, 0), BlinkLed.ALL_LEDS);

        assertEquals((byte) 'n', command.asBytes()[1]);
    }

    @Test public void
    should_have_led_set_to_ALL_LEDS_when_nothing_specified() {
        SetColorCommand command = new SetColorCommand(new RGBColor(0, 0, 0), BlinkLed.ALL_LEDS);

        assertEquals(BlinkLed.ALL_LEDS.asByte(), command.asBytes()[7]);
    }
    
    @Test public void
    should_have_correct_led_set_when_a_led_is_specified() {
        SetColorCommand command = new SetColorCommand(new RGBColor(0, 0, 0), BlinkLed.LED_1);

        assertEquals(BlinkLed.LED_1.asByte(), command.asBytes()[7]);
    }

    @Override
    protected ChangeLedColorCommand createCommand(RGBColor color, BlinkLed led) {
        return new SetColorCommand(color, led);
    }
}
