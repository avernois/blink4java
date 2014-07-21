package fr.craftinglabs.blink1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SetColorCommandTest extends ChangeLedColorCommandTest {

    @Test public void
    should_have_command_action_set_to_n() {
        SetColorCommand command = new SetColorCommand(new RGBColor(0, 0, 0), BlinkLeds.ALL_LEDS);

        assertEquals((byte) 'n', command.asBytes()[1]);
    }

    @Test public void
    should_have_led_set_to_ALL_LEDS_when_nothing_specified() {
        SetColorCommand command = new SetColorCommand(new RGBColor(0, 0, 0), BlinkLeds.ALL_LEDS);

        assertEquals(BlinkLeds.ALL_LEDS.asByte(), command.asBytes()[7]);
    }
    
    @Test public void
    should_have_correct_led_set_when_a_led_is_specified() {
        SetColorCommand command = new SetColorCommand(new RGBColor(0, 0, 0), BlinkLeds.LED_1);

        assertEquals(BlinkLeds.LED_1.asByte(), command.asBytes()[7]);
    }

    @Override
    protected ChangeLedColorCommand createCommand(RGBColor color, BlinkLeds led) {
        return new SetColorCommand(color, led);
    }
}
