package fr.craftinglabs.blink1.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import fr.craftinglabs.blink1.PatternLine;

public class PatterLineMatcher {
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
}
