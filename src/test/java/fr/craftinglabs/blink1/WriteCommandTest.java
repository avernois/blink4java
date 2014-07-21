package fr.craftinglabs.blink1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by avernois on 21/07/14.
 */
public abstract class WriteCommandTest {

    @Test public void
    should_return_a_byte_array () {
        WriteCommand command = createCommand();

        assertEquals(8, command.asBytes().length);
    }

    @Test public void
    should_have_report_id_set_to_1() {
        WriteCommand command = createCommand();

        assertEquals(0x01, command.asBytes()[0]);
    }

    abstract protected WriteCommand createCommand();
}
