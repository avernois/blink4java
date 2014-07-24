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
