package fr.craftinglabs.blink1;

import javax.usb.*;

import fr.craftinglabs.blink1.command.FadeToCommand;
import fr.craftinglabs.blink1.command.SetColorCommand;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

public class Blink {

    private final BlinkUsbDevice device;

    public Blink(BlinkUsbDevice device) {
        this.device = device;
    }

    public void fadeToColor(RGBColor color, int fadeTime) throws UnsupportedEncodingException, UsbException {
        this.fadeToColor(color, fadeTime, BlinkLeds.ALL_LEDS);
    }
    
    public void fadeToColor(RGBColor color, int fadeTime, BlinkLeds led1) throws UsbException {
    	device.sendCommand(new FadeToCommand(color, fadeTime, led1));
    }

    public void setColor(RGBColor rgbColor) throws UsbException {
        setColor(rgbColor, BlinkLeds.ALL_LEDS);
    }

    public void setColor(RGBColor rgbColor, BlinkLeds led) throws UsbException {
    	device.sendCommand(new SetColorCommand(rgbColor, led));
    }

    public static void main(String[] args) throws UsbException, UnsupportedEncodingException {
        List<Blink> blinks = BlinkLocator.findBlinks(UsbHostManager.getUsbServices().getRootUsbHub());

        Random random = new Random();

        for(Blink blink : blinks) {
            RGBColor color = new RGBColor(random.nextInt(256), random.nextInt(256), random.nextInt(256));

            System.out.println("Red : " + color.red() + ", green : " + color.green() + ", blue : " + color.blue());

            blink.fadeToColor(color, 3000, BlinkLeds.LED_1);
        }
    }
}
