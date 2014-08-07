package fr.craftinglabs.blink1.command;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ReadColorPatternLineRequestTest extends BlinkCommandTest {

	@Test public void 
	should_have_type_set_to_R() {
		BlinkCommand command = new ReadColorPatternLineRequest(0);
		
		assertThat(command.asBytes()[1], is((byte) 'R'));
	}
	
	@Test public void 
	should_have_last_byte_set_to_requested_pattern_position() {
		BlinkCommand command = new ReadColorPatternLineRequest(1);
		
		assertThat(command.asBytes()[7], is((byte) 1));
	}
	
	@Test public void 
	should_have_bytes_2_to_6_set_to_zero() {
		BlinkCommand request = new ReadColorPatternLineRequest(1);
		
		assertEquals(0, request.asBytes()[2]);
		assertEquals(0, request.asBytes()[3]);
		assertEquals(0, request.asBytes()[4]);
		assertEquals(0, request.asBytes()[5]);
		assertEquals(0, request.asBytes()[6]);
	}
	
	@Override
	protected BlinkCommand createCommand() {
		return new ReadColorPatternLineRequest(0);
	}
}
