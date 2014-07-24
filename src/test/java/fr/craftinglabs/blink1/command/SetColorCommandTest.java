package fr.craftinglabs.blink1.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.craftinglabs.blink1.RGBColor;

public class SetColorCommandTest extends ChangeColorCommandTest {

    @Test public void
    should_have_command_action_set_to_n() {
        SetColorCommand command = new SetColorCommand(new RGBColor(0, 0, 0));

        assertEquals((byte) 'n', command.asBytes()[1]);
    }

    @Test public void
    should_have_fifth_byte_set_to_zero() {
        SetColorCommand command = new SetColorCommand(new RGBColor(0, 0, 0));

        assertEquals((byte) 0, command.asBytes()[5]);
    }

    @Test public void
    should_have_sixfth_byte_set_to_zero() {
        SetColorCommand command = new SetColorCommand(new RGBColor(0, 0, 0));

        assertEquals((byte) 0, command.asBytes()[6]);
    }
    
    @Test public void
    should_have_last_byte_set_to_zero() {
        SetColorCommand command = new SetColorCommand(new RGBColor(0, 0, 0));

        assertEquals((byte) 0, command.asBytes()[7]);
    }
    
    @Override
    protected ChangeColorCommand createCommand(RGBColor color) {
        return new SetColorCommand(color);
    }
}
