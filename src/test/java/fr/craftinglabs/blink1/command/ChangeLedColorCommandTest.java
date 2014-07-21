package fr.craftinglabs.blink1.command;

import org.junit.Test;

import fr.craftinglabs.blink1.BlinkLeds;
import fr.craftinglabs.blink1.RGBColor;
import fr.craftinglabs.blink1.command.ChangeLedColorCommand;
import static org.junit.Assert.assertEquals;

public abstract class ChangeLedColorCommandTest {

    @Test public void
    should_return_a_byte_array () {
        ChangeLedColorCommand command = createCommand(new RGBColor(100, 200, 200), BlinkLeds.ALL_LEDS);

        assertEquals(8, command.asBytes().length);
    }

    @Test public void
    should_have_report_id_set_to_1() {
    	ChangeLedColorCommand command = createCommand(new RGBColor(100, 200, 200), BlinkLeds.ALL_LEDS);

        assertEquals(0x01, command.asBytes()[0]);
    }
    
    @Test public void
    should_have_color_set_in_fields_2_3_and_4() {
    	ChangeLedColorCommand command = createCommand(new RGBColor(100, 200, 200), BlinkLeds.ALL_LEDS);
    	
    	assertEquals((byte) 100, command.asBytes()[2]);
    	assertEquals((byte) 200, command.asBytes()[3]);
    	assertEquals((byte) 200, command.asBytes()[4]);
    }

    abstract protected ChangeLedColorCommand createCommand(RGBColor color, BlinkLeds led);
}
