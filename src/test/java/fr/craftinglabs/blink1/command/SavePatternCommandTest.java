package fr.craftinglabs.blink1.command;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class SavePatternCommandTest extends BlinkCommandTest{

    @Test public void 
    should_have_type_set_to_W() {
        BlinkCommand command = new SavePatternCommand();

        assertThat(command.asBytes()[1], is((byte) 'W'));
    }

    @Test public void 
    should_have_beef_and_cafe() {
        BlinkCommand command = new SavePatternCommand();

        assertThat(command.asBytes()[2], is((byte) 0xBE));
        assertThat(command.asBytes()[3], is((byte) 0xEF));
        assertThat(command.asBytes()[4], is((byte) 0xCA));
        assertThat(command.asBytes()[5], is((byte) 0xFE));
    }

    @Test public void 
    should_have_all_other_byte_set_to_0() {
        BlinkCommand command = new SavePatternCommand();
        
        assertThat(command.asBytes()[6], is((byte) 0));
        assertThat(command.asBytes()[7], is((byte) 0));
    }

    @Override
    protected BlinkCommand createCommand() {
        return new SavePatternCommand();
    }

}
