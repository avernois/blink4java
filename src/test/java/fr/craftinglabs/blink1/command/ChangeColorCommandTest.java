package fr.craftinglabs.blink1.command;

import org.junit.Test;

import fr.craftinglabs.blink1.RGBColor;
import static org.junit.Assert.assertEquals;

public abstract class ChangeColorCommandTest extends BlinkCommandTest {

    @Test public void
    should_have_color_set_in_bytes_2_3_and_4() {
        BlinkCommand command = createCommand(new RGBColor(100, 200, 200));

        assertEquals((byte) 100, command.asBytes()[2]);
        assertEquals((byte) 200, command.asBytes()[3]);
        assertEquals((byte) 200, command.asBytes()[4]);
    }

    @Override
    protected BlinkCommand createCommand() {
        return createCommand(new RGBColor(10, 10, 10));
    }

    abstract protected BlinkCommand createCommand(RGBColor color);
}
