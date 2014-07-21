package fr.craftinglabs.blink1;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by avernois on 20/07/14.
 */
public class RGBColorTest {
    @Test
    public void shouldHaveRedComponentProperlySet() {
        RGBColor color = new RGBColor(100, 0, 0);

        assertEquals(100, color.red());
    }

    @Test
    public void shouldHaveGreenComponentProperlySet() {
        RGBColor color = new RGBColor(0, 110, 0);

        assertEquals(110, color.green());
    }

    @Test
    public void shouldHaveBlueComponentProperlySet() {
        RGBColor color = new RGBColor(0, 0, 120);

        assertEquals(120, color.blue());
    }

    @Test public void
    shouldReturnRedComponentAsByte() {
        RGBColor color = new RGBColor(200, 0, 0);

        assertEquals((byte) -56, color.redAsByte());
    }

    @Test public void
    shouldReturnGreenComponentAsByte() {
        RGBColor color = new RGBColor(0, 210, 0);

        assertEquals((byte) -46, color.greenAsByte());
    }

    @Test public void
    shouldReturnBlueComponentAsByte() {
        RGBColor color = new RGBColor(0, 0, 220);

        assertEquals((byte) -36, color.blueAsByte());
    }
}
