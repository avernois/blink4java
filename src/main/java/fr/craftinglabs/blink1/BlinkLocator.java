package fr.craftinglabs.blink1;

import javax.usb.UsbDevice;
import javax.usb.UsbDeviceDescriptor;
import javax.usb.UsbHub;

import java.util.ArrayList;
import java.util.List;

public class BlinkLocator {
    private static final short VENDOR_ID = 0x27b8;
    private static final short PRODUCT_ID = 0x01ed;

    @SuppressWarnings("unchecked")
	public static  List<Blink> findBlinks(UsbHub hub) {
        List<Blink> blinks = new ArrayList<Blink>();
        for (UsbDevice device : (List<UsbDevice>) hub.getAttachedUsbDevices()) {
            if (device.isUsbHub()) {
                blinks.addAll(findBlinks((UsbHub) device));
            } else {
                UsbDeviceDescriptor desc = device.getUsbDeviceDescriptor();
                if (desc.idVendor() == VENDOR_ID && desc.idProduct() == PRODUCT_ID)
                    blinks.add(new Blink(new BlinkUsbDevice(device)));
            }
        }

        return blinks;
    }
}