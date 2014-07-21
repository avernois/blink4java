package fr.craftinglabs.blink1;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.usb.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BlinkUsbDeviceTest {

    private UsbDevice device;
    private UsbInterface iface;
    private ArgumentCaptor<UsbControlIrp> argumentCaptor;

    @Test public void
    should_send_command_with_right_request_type () throws UsbException {
        BlinkUsbDevice blink = new BlinkUsbDevice(device, iface);

        blink.sendCommand(new WriteCommand() {});

        verify(device).syncSubmit(argumentCaptor.capture());
        assertEquals((byte) (UsbConst.REQUESTTYPE_TYPE_CLASS |
                UsbConst.REQUESTTYPE_RECIPIENT_INTERFACE |
                UsbConst.ENDPOINT_DIRECTION_OUT), argumentCaptor.getValue().bmRequestType());
    }

    @Test public void
    should_send_command_with_request_set_to_SET_CONFIGURATION () throws UsbException {
        BlinkUsbDevice blink = new BlinkUsbDevice(device, iface);

        blink.sendCommand(new WriteCommand() {});

        verify(device).syncSubmit(argumentCaptor.capture());
        assertEquals((byte) (UsbConst.REQUEST_SET_CONFIGURATION), argumentCaptor.getValue().bRequest());
    }

    @Test public void
    should_send_command_with_wIndex_set_to_0 () throws UsbException {
        BlinkUsbDevice blink = new BlinkUsbDevice(device, iface);

        blink.sendCommand(new WriteCommand() {});

        verify(device).syncSubmit(argumentCaptor.capture());
        assertEquals((byte) 0x00, argumentCaptor.getValue().wIndex());
    }

    @Test public void
    should_send_command_with_wValue_set_to_3 () throws UsbException {
        BlinkUsbDevice blink = new BlinkUsbDevice(device, iface);

        blink.sendCommand(new WriteCommand() {});

        verify(device).syncSubmit(argumentCaptor.capture());
        assertEquals((byte) 0x03, argumentCaptor.getValue().wValue());
    }

    @Test public void
    should_send_data_required_by_the_command() throws UsbException {
        BlinkUsbDevice blink = new BlinkUsbDevice(device, iface);
        WriteCommand command = new WriteCommand() {};

        blink.sendCommand(command);

        verify(device).syncSubmit(argumentCaptor.capture());
        assertArrayEquals(command.asBytes(), argumentCaptor.getValue().getData());
    }

    @Before
    public void setUp() {
        device = mock(UsbDevice.class);
        iface = mock(UsbInterface.class);
        argumentCaptor = ArgumentCaptor.forClass(UsbControlIrp.class);
    }
}