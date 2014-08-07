package fr.craftinglabs.blink1;

import static fr.craftinglabs.blink1.matchers.RGBColorMatcher.matches;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
public class PatternLineTest {
 
    @Test public void 
    should_have_rgb_color_correctly_set_from_constructor() {
        PatternLine pattern = new PatternLine(new RGBColor(10, 100, 200) , 1000 , 0 );
        
        assertThat(pattern.color(), matches(new RGBColor(10, 100, 200)));
    }

    @Test public void 
    should_have_fadeTime_correctly_set_from_constructor() {
        PatternLine pattern = new PatternLine(new RGBColor(10, 100, 200) , 1000 , 0 );

        assertThat(pattern.fadeTime(), is(1000));
    }

    @Test public void 
    should_have_position_correctly_set_from_constructor() {
        PatternLine pattern = new PatternLine(new RGBColor(10, 100, 200) , 1000 , 1 );

        assertThat(pattern.position(), is(1));
    }

}
