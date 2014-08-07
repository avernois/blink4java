blink1mk2
============

blink(1) mk2 is a very nice USB status light made by ThingM.
This project, called blink4java aim to build a java library to control the blink.


Technical details
-----------------
We use the usb4java implementation of the JSR 080 for communication with USB devices.
As usb4java rely on LibUsb, it should work on every platform (Linux, OSX, Windows).

Currently blink4java is only tested on Linux (ubuntu).

Usage
---
### BlinkLocator
``BlinkLocator`` is used to locate the blink connected to your system.
A call to ``BlinkLocator.findBlinks()`` will return a list of ``Blink``.

### Blink
Blink represent a physical blink(1) device. Just call methods on it :)


### Example
Here is the code to find all blinks and ask them to fade to white.
```java
	// find all blinks
	List<Blink> blinks = BlinkLocator.findBlinks(UsbHostManager.getUsbServices().getRootUsbHub());

	RGBColor whiteColor = new RGBColor(255, 255, 255);

	//Iterate over all blinks
	for(Blink blink : blinks) {
	    //ask the current blink to fade to white in 3s
        blink.fadeToColor(whiteColor, 3000);
    }
```

Rmq : you should not create an instance of Blink by yourself, let BlinkLocator do it for you.


Current status.
--------------
 Here are the command already implemented on ``Blink``
 
 * `setColor(RGBColor color)` set the color of the leds
 * `fadeToColor(RGBColor color, int fadeTime, BlinkLed led)` ask `led` to fade to the given `color` in `fadeTime` milliseconds
 * `fadeToColor(RGBColor color, int fadeTime)` is a shortcut to `fadeToColor(color, fadeTime, BlinkLed.ALL_LEDS)
 * `RGBColor readCurrentColor(BlinkLed led)` return the current color of the specified led. Note : even with a fadeTime=0, fadeToColor is not instantaneous and readCurrentColor may return an intermediate value if executed just after fadeToColor.
 * `setPatternLine(PatternLine line)` set the PatternLine
 * `PatternLine readPatternLineAt(int position)` return the patternLine at position
 * `playPattern()` play the current pattern in loop forever
 * `playPattern(int startingPoint, int endingPoint, int repeat)` play the pattern from startingPoint position to endingPoint position for repeat times
 * `pausePattern()` stop playing pattern. (note: blink1 will stay with the last color displayed)
 * `savePattern()` save the pattern in the flash memory so the pattern will survive to a power off. (warning: previous pattern will be unrecoverable!)