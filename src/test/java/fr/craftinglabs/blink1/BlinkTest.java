package fr.craftinglabs.blink1;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class BlinkTest {

    private BlinkUsbDevice device;

	@Test public void
    shouldSendFadeCommand() throws Exception {
        Blink blink = new Blink(device);
        FadeToCommand expectedCommand = new FadeToCommand(new RGBColor(0, 0, 0), 2000);

        blink.fadeToColor(new RGBColor(0, 0, 0), 2000);

        ArgumentCaptor<FadeToCommand> commandCaptor = ArgumentCaptor.forClass(FadeToCommand.class);
        verify(device).sendCommand(commandCaptor.capture());
        assertArrayEquals(expectedCommand.asBytes(), commandCaptor.getValue().asBytes());
    }

    @Test public void
    shouldSendFadeCommandOnSpecificLed() throws Exception {
    	Blink blink = new Blink(device);
    	FadeToCommand expectedCommand = new FadeToCommand(new RGBColor(0, 0, 0), 2000, BlinkLeds.LED_1);
    	
    	blink.fadeToColor(new RGBColor(0, 0, 0), 2000, BlinkLeds.LED_1);
    	
    	ArgumentCaptor<FadeToCommand> commandCaptor = ArgumentCaptor.forClass(FadeToCommand.class);
    	verify(device).sendCommand(commandCaptor.capture());
    	assertArrayEquals(expectedCommand.asBytes(), commandCaptor.getValue().asBytes());
    }
    
    @Test public void
    shouldSendSetColorCommand() throws Exception {
        Blink blink = new Blink(device);
        WriteCommand expectedCommand = new SetColorCommand(new RGBColor(0, 0, 0));

        blink.setColor(new RGBColor(0, 0, 0));

        ArgumentCaptor<WriteCommand> commandCaptor = ArgumentCaptor.forClass(WriteCommand.class);
        verify(device).sendCommand(commandCaptor.capture());
        assertArrayEquals(expectedCommand.asBytes(), commandCaptor.getValue().asBytes());
    }

    @Before
    public void setUp() {
    	device = mock(BlinkUsbDevice.class);
    	
    }
    
    
}