package fr.craftinglabs.blink1.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import fr.craftinglabs.blink1.RGBColor;

public class RGBColorMatcher {
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
