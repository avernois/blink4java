package fr.craftinglabs.blink1;

import javax.usb.UsbControlIrp;
import javax.usb.UsbDevice;
import javax.usb.UsbDisconnectedException;
import javax.usb.UsbException;
import javax.usb.UsbInterface;

public class SimpleUsbDevice {
    UsbDevice device;

    public SimpleUsbDevice(UsbDevice device) {
        this.device = device;
    }

    void syncSubmit(UsbControlIrp irp) throws IllegalArgumentException, UsbDisconnectedException, UsbException {
        device.syncSubmit(irp);
    }

    UsbInterface getActiveInterface(byte number) {
        return device.getActiveUsbConfiguration().getUsbInterface(number);
    }
}
