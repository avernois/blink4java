package fr.craftinglabs.blink1;

import static fr.craftinglabs.blink1.matchers.RGBColorMatcher.matches;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ReadCurrentColorIntrepreterTest {
    @Test public void 
    should_interpret_the_color_from_raw_read_current_color_response() {
        ReadCurrentColorInterpreter interpreter = new ReadCurrentColorInterpreter();
        
        RGBColor actual = interpreter.interpret(new byte[] {0x01, 'r', (byte) 10, (byte) 100, (byte) 200, 0, 0, 0});
        
        assertThat(actual, matches(new RGBColor(10, 100, 200)));
    }
}
