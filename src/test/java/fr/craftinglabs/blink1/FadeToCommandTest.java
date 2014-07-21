package fr.craftinglabs.blink1;

import org.junit.Test;
import static org.junit.Assert.*;

public class FadeToCommandTest extends WriteCommandTest {

    @Test public void
    should_have_command_action_set_to_c() {
        FadeToCommand command = new FadeToCommand(new RGBColor(0, 0, 0), 0);

        assertEquals((byte) 'c', command.asBytes()[1]);
    }

    @Test public void
    should_have_rgb_value_correctly_set() {
        FadeToCommand command = new FadeToCommand(new RGBColor(10, 100, 200), 0);

        assertEquals((byte) 10, command.asBytes()[2]);
        assertEquals((byte) 100, command.asBytes()[3]);
        assertEquals((byte) 200, command.asBytes()[4]);
    }

    @Test public void
    should_have_fade_time_correctly_split_in_two_fields() {

        FadeToCommand command = new FadeToCommand(new RGBColor(0, 0, 0), 3000);

        assertEquals((byte) (3000/10 >> 8), command.asBytes()[5]);
        assertEquals((byte) (3000/10 & 0xff), command.asBytes()[6]);
    }

    @Test public void
    should_have_led_set_to_ALL_LEDS_when_nothing_specified() {
        FadeToCommand command = new FadeToCommand(new RGBColor(0, 0, 0), 0);

        assertEquals(BlinkLeds.ALL_LEDS.asByte(), command.asBytes()[7]);
    }
    
    @Test public void
    should_have_allow_to_optionnaly_specify_led() {
    	FadeToCommand command = new FadeToCommand(new RGBColor(0, 0, 0), 0, BlinkLeds.LED_1);
    	
    	assertEquals(BlinkLeds.LED_1.asByte(), command.asBytes()[7]);
    }

    @Override
    protected WriteCommand createCommand() {
        return new FadeToCommand(new RGBColor(0, 0, 0), 0);
    }
}
