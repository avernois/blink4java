package fr.craftinglabs.blink1.command;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.craftinglabs.blink1.BlinkLed;

public class ReadColorRequestTest extends BlinkCommandTest {

    private ReadColorRequest request;

    @Test public void 
    should_have_request_type_set_to_r() {
        assertEquals((byte) 'r', request.asBytes()[TYPE_INDEX]);
    }

    @Test public void 
    should_have_last_byte_be_set_for_ALL_LEDS_if_required_to() {
        request = new ReadColorRequest(BlinkLed.ALL_LEDS);

        assertEquals(BlinkLed.ALL_LEDS.asByte(), request.asBytes()[7]);
    }

    @Test public void 
    should_have_last_byte_be_set_for_LED_1_if_required_to() {
        request = new ReadColorRequest(BlinkLed.LED_1);

        assertEquals(BlinkLed.LED_1.asByte(), request.asBytes()[7]);
    }

    @Test public void 
    should_have_bytes_2_to_6_set_to_zero() {
        assertEquals(0, request.asBytes()[2]);
        assertEquals(0, request.asBytes()[3]);
        assertEquals(0, request.asBytes()[4]);
        assertEquals(0, request.asBytes()[5]);
        assertEquals(0, request.asBytes()[6]);
    }

    @Before
    public void setUp() {
        request = new ReadColorRequest(BlinkLed.ALL_LEDS);
    }

    @Override
    protected BlinkCommand createCommand() {
        return new ReadColorRequest(BlinkLed.ALL_LEDS);
    }
}
