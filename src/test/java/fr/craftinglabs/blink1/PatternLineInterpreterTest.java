package fr.craftinglabs.blink1;

import static fr.craftinglabs.blink1.matchers.PatterLineMatcher.matches;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PatternLineInterpreterTest {

    @Test public void 
    should_interpret_pattern_line_from_raw_pattern_line_response() {
        PatternLineInterpreter interpreter = new PatternLineInterpreter();
        int fadeTime = 3000;
        byte tl = (byte) ((fadeTime/10) & 0xff);
        byte th = (byte) (fadeTime/10 >> 8);
        int position = 1;

        PatternLine pattern = interpreter.interpret(new byte[] {0x01, 'R', (byte) 10, (byte) 100, (byte) 200, th, tl, (byte)position});
     
        assertThat(pattern, matches(new PatternLine(new RGBColor(10,  100, 200), fadeTime, position)));
    }
}
