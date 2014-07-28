package fr.craftinglabs.blink1.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public abstract class BlinkCommandTest {

	@Test public void 
	should_return_a_byte_array() {
	    BlinkCommand command = createCommand();
	
	    assertEquals(8, command.asBytes().length);
	}


	@Test public void 
	should_have_report_id_set_to_1() {
		BlinkCommand command = createCommand();
	
	    assertEquals(0x01, command.asBytes()[0]);
	}

	protected abstract BlinkCommand createCommand();
}