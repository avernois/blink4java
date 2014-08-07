package fr.craftinglabs.blink1;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.usb.UsbException;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import fr.craftinglabs.blink1.command.BlinkCommand;
import fr.craftinglabs.blink1.command.FadeToCommand;
import fr.craftinglabs.blink1.command.PausePatternCommand;
import fr.craftinglabs.blink1.command.PlayPatternCommand;
import fr.craftinglabs.blink1.command.ReadColorRequest;
import fr.craftinglabs.blink1.command.SetColorCommand;

public class BlinkTest {

    private BlinkUsbDevice device;
	private Blink blink;
	private ArgumentCaptor<BlinkCommand> commandCaptor;

	@Test public void
    should_send_fade_command() throws Exception {
        FadeToCommand expectedCommand = new FadeToCommand(new RGBColor(0, 0, 0), 2000, BlinkLed.ALL_LEDS);

        blink.fadeToColor(new RGBColor(0, 0, 0), 2000);

        verify(device).sendCommand(commandCaptor.capture());
        assertArrayEquals(expectedCommand.asBytes(), commandCaptor.getValue().asBytes());
    }

    @Test public void
    should_send_fade_comman_o_specificLed() throws Exception {
    	FadeToCommand expectedCommand = new FadeToCommand(new RGBColor(0, 0, 0), 2000, BlinkLed.LED_1);
    	
    	blink.fadeToColor(new RGBColor(0, 0, 0), 2000, BlinkLed.LED_1);
    	
    	verify(device).sendCommand(commandCaptor.capture());
    	assertArrayEquals(expectedCommand.asBytes(), commandCaptor.getValue().asBytes());
    }
    
    @Test public void
    should_send_set_color_command() throws Exception {
        BlinkCommand expectedCommand = new SetColorCommand(new RGBColor(0, 0, 0));

        blink.setColor(new RGBColor(0, 0, 0));

        verify(device).sendCommand(commandCaptor.capture());
        assertArrayEquals(expectedCommand.asBytes(), commandCaptor.getValue().asBytes());
    }

    @Test public void
    should_send_play_pattern() throws Exception {
        BlinkCommand expectedCommand = new PlayPatternCommand();

        blink.playPattern();

        verify(device).sendCommand(commandCaptor.capture());
        assertArrayEquals(expectedCommand.asBytes(), commandCaptor.getValue().asBytes());
    }

    @Test public void
    should_send_pause_pattern() throws Exception {
        BlinkCommand expectedCommand = new PausePatternCommand();

        blink.pausePattern();

        verify(device).sendCommand(commandCaptor.capture());
        assertArrayEquals(expectedCommand.asBytes(), commandCaptor.getValue().asBytes());
    }

    @Test public void
    should_send_play_pattern_with_parameters() throws Exception {
        BlinkCommand expectedCommand = new PlayPatternCommand(4, 8, 3);

        blink.playPattern(4, 8, 3);

        verify(device).sendCommand(commandCaptor.capture());
        assertArrayEquals(expectedCommand.asBytes(), commandCaptor.getValue().asBytes());
    }
    
    @Test public void
    should_send_read_color_request_when_asked_for_current_color() throws Exception {
    	byte[] deviceResponse = new byte[] {0x01, 'r', (byte) 10, (byte) 100, (byte) 200, 0, 0, 0};
		when(device.readResponse()).thenReturn(deviceResponse);
        BlinkCommand expectedCommand = new ReadColorRequest(BlinkLed.LED_1);

        blink.readCurrentColor(BlinkLed.LED_1);

        verify(device).sendCommand(commandCaptor.capture());
        assertArrayEquals(expectedCommand.asBytes(), commandCaptor.getValue().asBytes());
    }
    
    @Test public void
    should_read_device_answer_when_asked_for_current_color() throws Exception {
    	byte[] deviceResponse = new byte[] {0x01, 'r', (byte) 10, (byte) 100, (byte) 200, 0, 0, 0};
		when(device.readResponse()).thenReturn(deviceResponse);
		
        blink.readCurrentColor(BlinkLed.LED_1);

        verify(device).readResponse();
    }
    
    @Test public void 
	should_return_color_answered_by_device_when_asked_for_current_color() throws UsbException {
    	byte[] deviceResponse = new byte[] {0x01, 'r', (byte) 10, (byte) 100, (byte) 200, 0, 0, 0};
		when(device.readResponse()).thenReturn(deviceResponse);
		
		RGBColor color = blink.readCurrentColor(BlinkLed.LED_1);
		
		assertThat(color, matches(new RGBColor(10, 100, 200)));
	}

    @Test public void 
	should_return_pattern_line_answered_by_device_when_asked_for_a_pattern_line() throws UsbException {
        int fadeTime = 3000;
        byte tl = (byte) ((fadeTime/10) & 0xff);
        byte th = (byte) (fadeTime/10 >> 8);
        int position = 1;
        byte[] deviceResponse = new byte[] {0x01, 'R', (byte) 10, (byte) 100, (byte) 200, th, tl, (byte)position};
        when(device.readResponse()).thenReturn(deviceResponse);

        PatternLine patternLine = blink.readPatternLineAt(1);

        assertThat(patternLine, matches(new PatternLine(new RGBColor(10, 100, 200), fadeTime, position)));
	}

	@Before
    public void setUp() {
    	commandCaptor = ArgumentCaptor.forClass(BlinkCommand.class);
    	device = mock(BlinkUsbDevice.class);
    	
    	blink = new Blink(device);
    }

	public static Matcher<PatternLine> matches(final PatternLine expected){

	    return new BaseMatcher<PatternLine>() {

	        protected PatternLine theExpected = expected;

	        public boolean matches(Object o) {
	            PatternLine actual = (PatternLine) o;
	            return expected.fadeTime() == actual.fadeTime() 
	                    && expected.color().red() == actual.color().red()
	                    && expected.color().green() == actual.color().green()
	                    && expected.color().blue() == actual.color().blue()
	                    && expected.position() == actual.position();
	        }

	        public void describeTo(Description description) {
	            description.appendText(theExpected.toString());
	        }
	    };
	}

    public static Matcher<RGBColor> matches(final RGBColor expected){

	    return new BaseMatcher<RGBColor>() {

	        protected RGBColor theExpected = expected;

	        public boolean matches(Object o) {
	        	RGBColor actual = (RGBColor) o;
	            return expected.red() == actual.red() 
	            		&& expected.green() == actual.green()
	            		&& expected.blue() == actual.blue();
	        }

	        public void describeTo(Description description) {
	            description.appendText(theExpected.toString());
	        }
	    };
	}

}

