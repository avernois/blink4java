package fr.craftinglabs.blink1.command;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import fr.craftinglabs.blink1.PatternLine;
import fr.craftinglabs.blink1.RGBColor;

public class SetPatternLineCommandTest extends BlinkCommandTest {

    @Test public void 
    should_have_type_set_to_P() {
        SetPatternLineCommand command = new SetPatternLineCommand(new PatternLine(new RGBColor(10, 100, 200), 3000, 1));
        
        assertThat(command.asBytes()[1], is((byte) 'P'));
    }
    
    @Test public void 
    should_have_color_set_in_bytes_2_3_and_4() {
        SetPatternLineCommand command = new SetPatternLineCommand(new PatternLine(new RGBColor(10, 100, 200), 3000, 1));
        
        assertThat(command.asBytes()[2], is((byte) 10));
        assertThat(command.asBytes()[3], is((byte) 100));
        assertThat(command.asBytes()[4], is((byte) 200));
    }
    
    @Test public void 
    should_have_fadeTime_split_in_byte_5_and_6() {
        int fadeTime = 3000;
        byte fadeTimeHighByte = (byte) (fadeTime / 10 >> 8);
        byte fadeTimeLowByte = (byte) (fadeTime / 10 & 0xff);
        SetPatternLineCommand command = new SetPatternLineCommand(new PatternLine(new RGBColor(10, 100, 200), fadeTime, 1));

        assertThat(command.asBytes()[5], is(fadeTimeHighByte));
        assertThat(command.asBytes()[6], is(fadeTimeLowByte));
    }

    @Test public void 
    should_have_position_in_byte_7() {
        SetPatternLineCommand command = new SetPatternLineCommand(new PatternLine(new RGBColor(10, 100, 200), 3000, 1));

        assertThat(command.asBytes()[7], is((byte) 1));
    }
    
    @Override
    protected BlinkCommand createCommand() {
        return new SetPatternLineCommand(new PatternLine(new RGBColor(0, 0, 0), 1000, 1000));
    }

}
