package fr.craftinglabs.blink1.command;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class BlinkCommandTypeTest {

    @Test public void 
    should_have_FADE_TO_type_being_c() {
        assertThat(BlinkCommandType.FADE_TO.asByte(), is((byte) 'c'));
    }
    
    @Test public void 
    should_have_SET_COLOR_type_being_n() {
        assertThat(BlinkCommandType.SET_COLOR.asByte(), is((byte) 'n'));
    }
    
    @Test public void 
    should_have_PAUSE_PATTERN_type_being_p() {
        assertThat(BlinkCommandType.PAUSE_PATTERN.asByte(), is((byte) 'p'));
    }
    
    @Test public void 
    should_have_PLAY_PATTERN_type_being_p() {
        assertThat(BlinkCommandType.PLAY_PATTERN.asByte(), is((byte) 'p'));
    }
   
    @Test public void 
    should_have_READ_COLOR_type_being_r() {
        assertThat(BlinkCommandType.READ_COLOR.asByte(), is((byte) 'r'));
    }
    
    @Test public void 
    should_have_READ_PATTERN_LINE_type_being_R() {
        assertThat(BlinkCommandType.READ_PATTERN_LINE.asByte(), is((byte) 'R'));
    }
    
    @Test public void 
    should_have_SAVE_PATTERN_type_being_W() {
        assertThat(BlinkCommandType.SAVE_PATTERN.asByte(), is((byte) 'W'));
    }
    
    @Test public void 
    should_have_SET_PATTERN_LINE_type_being_P() {
        assertThat(BlinkCommandType.SET_PATTERN_LINE.asByte(), is((byte) 'P'));
    }
}
