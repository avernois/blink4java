package fr.craftinglabs.blink1;

import javax.usb.*;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

public class Blink {

    private final BlinkUsbDevice device;

    public Blink(BlinkUsbDevice device) {
        this.device = device;
    }

    public void fadeToColor(RGBColor color, int fadeTime) throws UnsupportedEncodingException, UsbException {
        device.sendCommand(new FadeToCommand(color, fadeTime));
    }

    public void setColor(RGBColor rgbColor) throws UsbException {
        device.sendCommand(new SetColorCommand(rgbColor));
    }

    public static void main(String[] args) throws UsbException, UnsupportedEncodingException {
        List<Blink> blinks = BlinkLocator.findBlinks(UsbHostManager.getUsbServices().getRootUsbHub());

        Random random = new Random();

        for(Blink blink : blinks) {
            RGBColor color = new RGBColor(random.nextInt(256), random.nextInt(256), random.nextInt(256));

            System.out.println("Red : " + color.red() + ", green : " + color.green() + ", blue : " + color.blue());

            blink.fadeToColor(color, 3000);
        }
    }
}
