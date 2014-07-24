package fr.craftinglabs.blink1.command;

import org.junit.Test;

import fr.craftinglabs.blink1.RGBColor;
import fr.craftinglabs.blink1.command.ChangeColorCommand;
import static org.junit.Assert.assertEquals;

public abstract class ChangeColorCommandTest {

    @Test public void
    should_return_a_byte_array () {
        ChangeColorCommand command = createCommand(new RGBColor(100, 200, 200));

        assertEquals(8, command.asBytes().length);
    }

    @Test public void
    should_have_report_id_set_to_1() {
    	ChangeColorCommand command = createCommand(new RGBColor(100, 200, 200));

        assertEquals(0x01, command.asBytes()[0]);
    }
    
    @Test public void
    should_have_color_set_in_bytes_2_3_and_4() {
    	ChangeColorCommand command = createCommand(new RGBColor(100, 200, 200));
    	
    	assertEquals((byte) 100, command.asBytes()[2]);
    	assertEquals((byte) 200, command.asBytes()[3]);
    	assertEquals((byte) 200, command.asBytes()[4]);
    }

    abstract protected ChangeColorCommand createCommand(RGBColor color);
}
