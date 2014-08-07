package fr.craftinglabs.blink1;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
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
