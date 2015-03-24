package fr.craftinglabs.blink1;

import javax.usb.UsbClaimException;
import javax.usb.UsbConst;
import javax.usb.UsbControlIrp;
import javax.usb.UsbException;
import javax.usb.UsbInterface;
import javax.usb.UsbInterfacePolicy;
import javax.usb.util.DefaultUsbControlIrp;

import fr.craftinglabs.blink1.command.BlinkCommand;

public class BlinkUsbDevice {
    private SimpleUsbDevice simpleDevice;
    
    public BlinkUsbDevice(SimpleUsbDevice device) throws UsbClaimException, UsbException {
        this.simpleDevice = device;
        
        forceClaim(device.getActiveInterface((byte) 0x00));
    }    

    public void execute(BlinkCommand command) throws UsbException {
        UsbControlIrp irp = new DefaultUsbControlIrp(
                (byte) (UsbConst.REQUESTTYPE_TYPE_CLASS |
                        UsbConst.REQUESTTYPE_RECIPIENT_INTERFACE |
                        UsbConst.ENDPOINT_DIRECTION_OUT),
                UsbConst.REQUEST_SET_CONFIGURATION,
                (short) 3,
                (short) 0);

        irp.setData(command.asBytes());
        simpleDevice.syncSubmit(irp);
    }
    
    private void forceClaim(UsbInterface iface) throws UsbClaimException, UsbException {
        iface.claim(new UsbInterfacePolicy() {
            public boolean forceClaim(UsbInterface arg0) {
                return true;
            }
        });
    }

    byte[] query() throws UsbException {
        UsbControlIrp irp = new DefaultUsbControlIrp(
                (byte) (UsbConst.REQUESTTYPE_TYPE_CLASS |
                        UsbConst.REQUESTTYPE_RECIPIENT_INTERFACE |
                        UsbConst.ENDPOINT_DIRECTION_IN),
                (byte) 0x01,
                (short) 3,
                (short) 0);

        irp.setData(new byte[8]);
        simpleDevice.syncSubmit(irp);
        
        return irp.getData();
	}

    public <T> T query(RawResponseInterpreter<T> interpreter) throws UsbException {
        return interpreter.interpret(query());
    }
}
