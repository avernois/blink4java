package fr.craftinglabs.blink1;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import fr.craftinglabs.blink1.command.ChangeColorCommand;
import fr.craftinglabs.blink1.command.FadeToCommand;
import fr.craftinglabs.blink1.command.SetColorCommand;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class BlinkTest {

    private BlinkUsbDevice device;
	private Blink blink;
	private ArgumentCaptor<ChangeColorCommand> commandCaptor;

	@Test public void
    shouldSendFadeCommand() throws Exception {
        FadeToCommand expectedCommand = new FadeToCommand(new RGBColor(0, 0, 0), 2000, BlinkLed.ALL_LEDS);

        blink.fadeToColor(new RGBColor(0, 0, 0), 2000);

        verify(device).sendCommand(commandCaptor.capture());
        assertArrayEquals(expectedCommand.asBytes(), commandCaptor.getValue().asBytes());
    }

    @Test public void
    shouldSendFadeCommandOnSpecificLed() throws Exception {
    	FadeToCommand expectedCommand = new FadeToCommand(new RGBColor(0, 0, 0), 2000, BlinkLed.LED_1);
    	
    	blink.fadeToColor(new RGBColor(0, 0, 0), 2000, BlinkLed.LED_1);
    	
    	verify(device).sendCommand(commandCaptor.capture());
    	assertArrayEquals(expectedCommand.asBytes(), commandCaptor.getValue().asBytes());
    }
    
    @Test public void
    shouldSendSetColorCommand() throws Exception {
        ChangeColorCommand expectedCommand = new SetColorCommand(new RGBColor(0, 0, 0));

        blink.setColor(new RGBColor(0, 0, 0));

        verify(device).sendCommand(commandCaptor.capture());
        assertArrayEquals(expectedCommand.asBytes(), commandCaptor.getValue().asBytes());
    }
    
    @Before
    public void setUp() {
    	commandCaptor = ArgumentCaptor.forClass(ChangeColorCommand.class);
    	device = mock(BlinkUsbDevice.class);
    	
    	blink = new Blink(device);
    }
}