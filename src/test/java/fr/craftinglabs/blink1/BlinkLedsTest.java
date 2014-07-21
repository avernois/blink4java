package fr.craftinglabs.blink1;

import org.junit.Test;

import static org.junit.Assert.*;

public class BlinkLedsTest {

	@Test public void 
	should_have_ALL_LEDS_0x00() {
		assertEquals(0x00, BlinkLeds.ALL_LEDS.asByte());
	}
	
	@Test public void 
	should_have_LED_1_0x01() {
		assertEquals(0x01, BlinkLeds.LED_1.asByte());
	}

	@Test public void 
	should_have_LED_2_0x02() {
		assertEquals(0x02, BlinkLeds.LED_2.asByte());
	}

}
