package fr.craftinglabs.blink1;

import javax.usb.*;
import javax.usb.util.DefaultUsbControlIrp;

import fr.craftinglabs.blink1.command.ChangeColorCommand;

public class BlinkUsbDevice {
    private final UsbInterface iface;
    private UsbDevice device;

    public BlinkUsbDevice(UsbDevice device) {
        this(device, device.getActiveUsbConfiguration().getUsbInterface((byte)0x00));
    }

    public BlinkUsbDevice(UsbDevice device, UsbInterface iface) {
        this.device = device;
        this.iface = iface;
    }

    public void sendCommand(ChangeColorCommand command) throws UsbException {
        iface.claim(new UsbInterfacePolicy() {
			public boolean forceClaim(UsbInterface arg0) {
				return true;
			}
		});

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
}
