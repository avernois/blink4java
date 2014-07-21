package fr.craftinglabs.blink1;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class BlinkTest {

    private BlinkUsbDevice device;
	private Blink blink;
	private ArgumentCaptor<ChangeLedColorCommand> commandCaptor;

	@Test public void
    shouldSendFadeCommand() throws Exception {
        FadeToCommand expectedCommand = new FadeToCommand(new RGBColor(0, 0, 0), 2000, BlinkLeds.ALL_LEDS);

        blink.fadeToColor(new RGBColor(0, 0, 0), 2000);

        verify(device).sendCommand(commandCaptor.capture());
        assertArrayEquals(expectedCommand.asBytes(), commandCaptor.getValue().asBytes());
    }

    @Test public void
    shouldSendFadeCommandOnSpecificLed() throws Exception {
    	FadeToCommand expectedCommand = new FadeToCommand(new RGBColor(0, 0, 0), 2000, BlinkLeds.LED_1);
    	
    	blink.fadeToColor(new RGBColor(0, 0, 0), 2000, BlinkLeds.LED_1);
    	
    	verify(device).sendCommand(commandCaptor.capture());
    	assertArrayEquals(expectedCommand.asBytes(), commandCaptor.getValue().asBytes());
    }
    
    @Test public void
    shouldSendSetColorCommand() throws Exception {
        ChangeLedColorCommand expectedCommand = new SetColorCommand(new RGBColor(0, 0, 0), BlinkLeds.ALL_LEDS);

        blink.setColor(new RGBColor(0, 0, 0));

        verify(device).sendCommand(commandCaptor.capture());
        assertArrayEquals(expectedCommand.asBytes(), commandCaptor.getValue().asBytes());
    }
    
    @Test public void
    shouldSendSetColorCommandOnSpecifiedLed() throws Exception {
        ChangeLedColorCommand expectedCommand = new SetColorCommand(new RGBColor(0, 0, 0), BlinkLeds.LED_1);

        blink.setColor(new RGBColor(0, 0, 0), BlinkLeds.LED_1);

        verify(device).sendCommand(commandCaptor.capture());
        assertArrayEquals(expectedCommand.asBytes(), commandCaptor.getValue().asBytes());
    }

    @Before
    public void setUp() {
    	commandCaptor = ArgumentCaptor.forClass(ChangeLedColorCommand.class);
    	device = mock(BlinkUsbDevice.class);
    	
    	blink = new Blink(device);
    }
}