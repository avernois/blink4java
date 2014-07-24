package fr.craftinglabs.blink1;

import javax.usb.*;
import javax.usb.util.DefaultUsbControlIrp;

import fr.craftinglabs.blink1.command.ChangeColorCommand;

public class BlinkUsbDevice {
    private UsbDevice device;

    public BlinkUsbDevice(UsbDevice device) throws UsbException {
        this(device, device.getActiveUsbConfiguration().getUsbInterface((byte) 0x00));
    }

    public BlinkUsbDevice(UsbDevice device, UsbInterface iface) throws UsbException {
        this.device = device;

        forceClaim(iface);
    }

    public void sendCommand(ChangeColorCommand command) throws UsbException {
        UsbControlIrp irp = new DefaultUsbControlIrp(
                (byte) (UsbConst.REQUESTTYPE_TYPE_CLASS |
                        UsbConst.REQUESTTYPE_RECIPIENT_INTERFACE |
                        UsbConst.ENDPOINT_DIRECTION_OUT),
                UsbConst.REQUEST_SET_CONFIGURATION,
                (short) 3,
                (short) 0);

        irp.setData(command.asBytes());
        device.syncSubmit(irp);
    }

    private void forceClaim(UsbInterface iface) throws UsbClaimException, UsbException {
        iface.claim(new UsbInterfacePolicy() {
            public boolean forceClaim(UsbInterface arg0) {
                return true;
            }
        });
    }
}
