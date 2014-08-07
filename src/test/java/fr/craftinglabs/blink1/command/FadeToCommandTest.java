package fr.craftinglabs.blink1.command;

import org.junit.Test;

import fr.craftinglabs.blink1.BlinkLed;
import fr.craftinglabs.blink1.RGBColor;
import fr.craftinglabs.blink1.command.FadeToCommand;
import static org.junit.Assert.*;

public class FadeToCommandTest extends ChangeColorCommandTest {

    @Test public void
    should_have_command_action_set_to_c() {
        FadeToCommand command = new FadeToCommand(new RGBColor(0, 0, 0), 0, BlinkLed.ALL_LEDS);

        assertEquals((byte) 'c', command.asBytes()[1]);
    }

    @Test public void
    should_have_rgb_value_correctly_set() {
        FadeToCommand command = new FadeToCommand(new RGBColor(10, 100, 200), 0, BlinkLed.ALL_LEDS);

        assertEquals((byte) 10, command.asBytes()[2]);
        assertEquals((byte) 100, command.asBytes()[3]);
        assertEquals((byte) 200, command.asBytes()[4]);
    }

    @Test public void
    should_have_fade_time_correctly_split_in_two_fields() {

        FadeToCommand command = new FadeToCommand(new RGBColor(0, 0, 0), 3000, BlinkLed.ALL_LEDS);

        assertEquals((byte) (3000/10 >> 8), command.asBytes()[5]);
        assertEquals((byte) (3000/10 & 0xff), command.asBytes()[6]);
    }

    @Test public void
    should_have_specified_led_correctly_set() {
        FadeToCommand command = new FadeToCommand(new RGBColor(0, 0, 0), 0, BlinkLed.LED_1);
        
        assertEquals(BlinkLed.LED_1.asByte(), command.asBytes()[7]);
    }

    @Override
    protected BlinkCommand createCommand(RGBColor color) {
        return new FadeToCommand(color, 0, BlinkLed.ALL_LEDS);
    }
}
