package fr.craftinglabs.blink1.command;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PausePatternCommandTest extends BlinkCommandTest {

    private static final int TYPE_INDEX = 1;

    @Test public void 
    should_have_type_set_to_p() {
        PausePatternCommand command = new PausePatternCommand();

        assertThat(command.asBytes()[TYPE_INDEX], is((byte) 'p'));
    }

    @Test public void 
    should_have_play_byte_set_to_1() {
        PausePatternCommand command = new PausePatternCommand();

        assertThat(command.asBytes()[2], is((byte) 0));
    }

    @Override
    protected BlinkCommand createCommand() {
        return new PausePatternCommand();
    }
}
