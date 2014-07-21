package fr.craftinglabs.blink1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SetColorCommandTest extends WriteCommandTest {

    @Test public void
    should_have_command_action_set_to_n() {
        SetColorCommand command = new SetColorCommand(new RGBColor(0, 0, 0));

        assertEquals((byte) 'n', command.asBytes()[1]);
    }

    @Test public void
    should_have_rgb_value_correctly_set() {
        SetColorCommand command = new SetColorCommand(new RGBColor(10, 100, 200));

        assertEquals((byte) 10, command.asBytes()[2]);
        assertEquals((byte) 100, command.asBytes()[3]);
        assertEquals((byte) 200, command.asBytes()[4]);
    }


    @Test public void
    should_have_led_set_to_0_when_nothing_specified() {
        SetColorCommand command = new SetColorCommand(new RGBColor(0, 0, 0));

        assertEquals(0, command.asBytes()[7]);
    }

    @Override
    protected WriteCommand createCommand() {
        return new SetColorCommand(new RGBColor(0, 0, 0));
    }
}
