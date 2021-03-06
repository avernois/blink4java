package fr.craftinglabs.blink1;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

import javax.usb.UsbException;
import javax.usb.UsbHostManager;

import fr.craftinglabs.blink1.command.FadeToCommand;
import fr.craftinglabs.blink1.command.PausePatternCommand;
import fr.craftinglabs.blink1.command.PlayPatternCommand;
import fr.craftinglabs.blink1.command.ReadPatternLineRequest;
import fr.craftinglabs.blink1.command.ReadColorRequest;
import fr.craftinglabs.blink1.command.SavePatternCommand;
import fr.craftinglabs.blink1.command.SetColorCommand;
import fr.craftinglabs.blink1.command.SetPatternLineCommand;

public class Blink {

    private final BlinkUsbDevice device;

    public Blink(BlinkUsbDevice device) {
        this.device = device;
    }

    public void fadeToColor(RGBColor color, int fadeTime) throws UnsupportedEncodingException, UsbException {
        this.fadeToColor(color, fadeTime, BlinkLed.ALL_LEDS);
    }

    public void fadeToColor(RGBColor color, int fadeTime, BlinkLed led1) throws UsbException {
    	device.sendCommand(new FadeToCommand(color, fadeTime, led1));
    }

    public void setColor(RGBColor rgbColor) throws UsbException {
        device.sendCommand(new SetColorCommand(rgbColor));
    }

    public RGBColor readCurrentColor(BlinkLed led) throws UsbException {
    	device.sendCommand(new ReadColorRequest(led));
    	byte[] response = device.readResponse();
    	
    	return extractColor(response);
    }

    public void setPatternLine(PatternLine line) throws UsbException {
        device.sendCommand(new SetPatternLineCommand(line));
    }

    public PatternLine readPatternLineAt(int position) throws UsbException {
        device.sendCommand(new ReadPatternLineRequest(position));
        byte[] response = device.readResponse();

        int fadeTime = (convertToPositiveInt(response[5])*256 + convertToPositiveInt(response[6])) * 10;
        return new PatternLine(extractColor(response), fadeTime, convertToPositiveInt(response[7]));
    }

    public void playPattern() throws UsbException {
        device.sendCommand(new PlayPatternCommand());
    }

    public void playPattern(int startingPoint, int endingPoint, int repeat) throws UsbException {
        device.sendCommand(new PlayPatternCommand(startingPoint, endingPoint, repeat));
    }

    public void pausePattern() throws UsbException {
        device.sendCommand(new PausePatternCommand());
    }

    public void savePattern() throws UsbException {
        device.sendCommand(new SavePatternCommand());
    }

    private RGBColor extractColor(byte[] response) {
        int red = convertToPositiveInt(response[2]);
        int green = convertToPositiveInt(response[3]);
        int blue = convertToPositiveInt(response[4]);
        return new RGBColor(red, green, blue);
    }

    private int convertToPositiveInt(byte byt) {
        return byt >= 0 ? byt : byt + 256;
    }

    public static void main(String[] args) throws UsbException, UnsupportedEncodingException {
        List<Blink> blinks = BlinkLocator.findBlinks(UsbHostManager.getUsbServices().getRootUsbHub());

        Random random = new Random();

        for(Blink blink : blinks) {
            RGBColor color = new RGBColor(random.nextInt(256), random.nextInt(256), random.nextInt(256));

            System.out.println("Red : " + color.red() + ", green : " + color.green() + ", blue : " + color.blue());

            blink.setColor(color);
            //blink.fadeToColor(color, 0000, BlinkLed.LED_1);
            color = blink.readCurrentColor(BlinkLed.LED_1);

            System.out.println("read : Red : " + color.red() + ", green : " + color.green() + ", blue : " + color.blue());
        }
    }
}
 