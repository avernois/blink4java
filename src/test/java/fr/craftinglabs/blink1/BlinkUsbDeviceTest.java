package fr.craftinglabs.blink1;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.usb.UsbConst;
import javax.usb.UsbControlIrp;
import javax.usb.UsbDevice;
import javax.usb.UsbException;
import javax.usb.UsbInterface;
import javax.usb.UsbInterfacePolicy;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import fr.craftinglabs.blink1.command.BlinkCommand;
import fr.craftinglabs.blink1.command.SavePatternCommand;

public class BlinkUsbDeviceTest {

    private UsbDevice device;
    private UsbInterface iface;
    private ArgumentCaptor<UsbControlIrp> argumentCaptor;

    @Test public void
    should_send_command_with_right_request_type () throws UsbException {
        BlinkUsbDevice blink = new BlinkUsbDevice(device, iface);

        blink.sendCommand(createCommand());

        verify(device).syncSubmit(argumentCaptor.capture());
        assertEquals((byte) (UsbConst.REQUESTTYPE_TYPE_CLASS |
                UsbConst.REQUESTTYPE_RECIPIENT_INTERFACE |
                UsbConst.ENDPOINT_DIRECTION_OUT), argumentCaptor.getValue().bmRequestType());
    }


    @Test public void
    should_send_command_with_request_set_to_SET_CONFIGURATION () throws UsbException {
        BlinkUsbDevice blink = new BlinkUsbDevice(device, iface);

        blink.sendCommand(createCommand());

        verify(device).syncSubmit(argumentCaptor.capture());
        assertEquals((byte) (UsbConst.REQUEST_SET_CONFIGURATION), argumentCaptor.getValue().bRequest());
    }

    @Test public void
    should_send_command_with_wIndex_set_to_0 () throws UsbException {
        BlinkUsbDevice blink = new BlinkUsbDevice(device, iface);

        blink.sendCommand(createCommand());

        verify(device).syncSubmit(argumentCaptor.capture());
        assertEquals((byte) 0x00, argumentCaptor.getValue().wIndex());
    }

    @Test public void
    should_send_command_with_wValue_set_to_3 () throws UsbException {
        BlinkUsbDevice blink = new BlinkUsbDevice(device, iface);

        blink.sendCommand(createCommand());

        verify(device).syncSubmit(argumentCaptor.capture());
        assertEquals((byte) 0x03, argumentCaptor.getValue().wValue());
    }

    @Test public void
    should_send_data_required_by_the_command() throws UsbException {
        BlinkUsbDevice blink = new BlinkUsbDevice(device, iface);
        BlinkCommand command = createCommand();

        blink.sendCommand(command);

        verify(device).syncSubmit(argumentCaptor.capture());
        assertArrayEquals(command.asBytes(), argumentCaptor.getValue().getData());
    }

    @Test public void
    should_not_claim_device_more_than_one_time() throws UsbException {
        BlinkUsbDevice blink = new BlinkUsbDevice(device, iface);
        
        blink.sendCommand(createCommand());
        blink.sendCommand(createCommand());

        verify(iface, times(1)).claim(any(UsbInterfacePolicy.class));
    }

    @Test public void 
	should_return_a_response() throws UsbException {
        BlinkUsbDevice blink = new BlinkUsbDevice(device, iface);
        
        byte[] response = blink.readResponse();

        assertEquals(8, response.length);
    }
    
    @Test public void 
    should_ask_device_for_the_response_with_holder_for_it() throws UsbException {
        BlinkUsbDevice blink = new BlinkUsbDevice(device, iface);

        blink.readResponse();

        verify(device).syncSubmit(argumentCaptor.capture());
        assertArrayEquals(new byte[8], argumentCaptor.getValue().getData());
    }

    @Test public void
    should_ask_device_for_the_response_with_right_request_type () throws UsbException {
        BlinkUsbDevice blink = new BlinkUsbDevice(device, iface);

        blink.readResponse();

        verify(device).syncSubmit(argumentCaptor.capture());
        assertEquals((byte) (UsbConst.REQUESTTYPE_TYPE_CLASS |
                UsbConst.REQUESTTYPE_RECIPIENT_INTERFACE |
                UsbConst.ENDPOINT_DIRECTION_IN), argumentCaptor.getValue().bmRequestType());
    }

    @Test public void
    should_ask_device_for_the_response_with_right_request() throws UsbException {
        BlinkUsbDevice blink = new BlinkUsbDevice(device, iface);

        blink.readResponse();

        verify(device).syncSubmit(argumentCaptor.capture());
        assertEquals((byte) (0x01), argumentCaptor.getValue().bRequest());
    }

    @Test public void
    should_ask_device_for_the_response_with_wIndex_set_to_0 () throws UsbException {
        BlinkUsbDevice blink = new BlinkUsbDevice(device, iface);

        blink.readResponse();

        verify(device).syncSubmit(argumentCaptor.capture());
        assertEquals((byte) 0x00, argumentCaptor.getValue().wIndex());
    }

    @Test public void
    should_ask_device_for_the_response_with_wValue_set_to_3 () throws UsbException {
        BlinkUsbDevice blink = new BlinkUsbDevice(device, iface);

        blink.readResponse();

        verify(device).syncSubmit(argumentCaptor.capture());
        assertEquals((byte) 0x03, argumentCaptor.getValue().wValue());
    }

    @Before
    public void setUp() {
        device = mock(UsbDevice.class);
        iface = mock(UsbInterface.class);
        argumentCaptor = ArgumentCaptor.forClass(UsbControlIrp.class);
    }

    private BlinkCommand createCommand() {
        return new SavePatternCommand();
    }
}