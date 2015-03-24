package fr.craftinglabs.blink1;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.usb.UsbClaimException;
import javax.usb.UsbConst;
import javax.usb.UsbControlIrp;
import javax.usb.UsbDevice;
import javax.usb.UsbDisconnectedException;
import javax.usb.UsbException;
import javax.usb.UsbInterface;
import javax.usb.UsbInterfacePolicy;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import fr.craftinglabs.blink1.command.BlinkCommand;
import fr.craftinglabs.blink1.command.SavePatternCommand;
import fr.craftinglabs.blink1.matchers.RGBColorMatcher;

public class BlinkUsbDeviceTest {

    private UsbInterface iface;
    private ArgumentCaptor<UsbControlIrp> argumentCaptor;
    private SimpleUsbDevice device;
    
    
    @Test public void
    should_send_command_with_right_request_type () throws UsbException {
        BlinkUsbDevice blink = new BlinkUsbDevice(device);

        blink.execute(createCommand());

        verify(device).syncSubmit(argumentCaptor.capture());
        assertEquals((byte) (UsbConst.REQUESTTYPE_TYPE_CLASS |
                UsbConst.REQUESTTYPE_RECIPIENT_INTERFACE |
                UsbConst.ENDPOINT_DIRECTION_OUT), argumentCaptor.getValue().bmRequestType());
    }


    @Test public void
    should_send_command_with_request_set_to_SET_CONFIGURATION () throws UsbException {
        BlinkUsbDevice blink = new BlinkUsbDevice(device);

        blink.execute(createCommand());

        verify(device).syncSubmit(argumentCaptor.capture());
        assertEquals((byte) (UsbConst.REQUEST_SET_CONFIGURATION), argumentCaptor.getValue().bRequest());
    }

    @Test public void
    should_send_command_with_wIndex_set_to_0 () throws UsbException {
        BlinkUsbDevice blink = new BlinkUsbDevice(device);

        blink.execute(createCommand());

        verify(device).syncSubmit(argumentCaptor.capture());
        assertEquals((byte) 0x00, argumentCaptor.getValue().wIndex());
    }

    @Test public void
    should_send_command_with_wValue_set_to_3 () throws UsbException {
        BlinkUsbDevice blink = new BlinkUsbDevice(device);

        blink.execute(createCommand());

        verify(device).syncSubmit(argumentCaptor.capture());
        assertEquals((byte) 0x03, argumentCaptor.getValue().wValue());
    }

    @Test public void
    should_send_data_required_by_the_command() throws UsbException {
        BlinkUsbDevice blink = new BlinkUsbDevice(device);
        BlinkCommand command = createCommand();

        blink.execute(command);

        verify(device).syncSubmit(argumentCaptor.capture());
        assertArrayEquals(command.asBytes(), argumentCaptor.getValue().getData());
    }

    @Test public void
    should_not_claim_device_more_than_one_time() throws UsbException {
        BlinkUsbDevice blink = new BlinkUsbDevice(device);
        
        blink.execute(createCommand());
        blink.execute(createCommand());

        verify(iface, times(1)).claim(any(UsbInterfacePolicy.class));
    }

    @Test public void 
    should_return_interpreted_response_when_queryed_with_an_interpretor() throws UsbClaimException, UsbException {
        BlinkUsbDevice blink = new BlinkUsbDevice(new SimpleUsbDeviceThatRespondReadColor());
        
        RGBColor color = blink.query(new ReadCurrentColorInterpreter());
        
        assertThat(color, RGBColorMatcher.matches(new RGBColor(10, 100, 200)));
    }
       
    @Test public void
    should_ask_device_for_the_response_with_right_request_type () throws UsbException {
        BlinkUsbDevice blink = new BlinkUsbDevice(device);

        blink.query(new ReadCurrentColorInterpreter());

        verify(device).syncSubmit(argumentCaptor.capture());
        assertEquals((byte) (UsbConst.REQUESTTYPE_TYPE_CLASS |
                UsbConst.REQUESTTYPE_RECIPIENT_INTERFACE |
                UsbConst.ENDPOINT_DIRECTION_IN), argumentCaptor.getValue().bmRequestType());
    }

    @Test public void
    should_ask_device_for_the_response_with_right_request() throws UsbException {
        BlinkUsbDevice blink = new BlinkUsbDevice(device);

        blink.query(new ReadCurrentColorInterpreter());

        verify(device).syncSubmit(argumentCaptor.capture());
        assertEquals((byte) (0x01), argumentCaptor.getValue().bRequest());
    }

    @Test public void
    should_ask_device_for_the_response_with_wIndex_set_to_0 () throws UsbException {
        BlinkUsbDevice blink = new BlinkUsbDevice(device);

        blink.query(new ReadCurrentColorInterpreter());

        verify(device).syncSubmit(argumentCaptor.capture());
        assertEquals((byte) 0x00, argumentCaptor.getValue().wIndex());
    }

    @Test public void
    should_ask_device_for_the_response_with_wValue_set_to_3 () throws UsbException {
        BlinkUsbDevice blink = new BlinkUsbDevice(device);

        blink.query(new ReadCurrentColorInterpreter());

        verify(device).syncSubmit(argumentCaptor.capture());
        assertEquals((byte) 0x03, argumentCaptor.getValue().wValue());
    }

    @Before
    public void setUp() {
        iface = mock(UsbInterface.class);
        device = mock(SimpleUsbDevice.class);
        when(device.getActiveInterface((byte) 0)).thenReturn(iface);
        argumentCaptor = ArgumentCaptor.forClass(UsbControlIrp.class);
    }

    private BlinkCommand createCommand() {
        return new SavePatternCommand();
    }
    
    
    class DumbSimpleUsbDeviceThatSetDataInIRPWhenSyncSubmit extends SimpleUsbDevice {
        byte[] data;
        public DumbSimpleUsbDeviceThatSetDataInIRPWhenSyncSubmit(byte[] data) {
            super(null);
            this.data = data;
        }
        
        @Override
        UsbInterface getActiveInterface(byte number) {
            return iface;
        }
        
        @Override
        void syncSubmit(UsbControlIrp irp) throws IllegalArgumentException, UsbDisconnectedException, UsbException {
            irp.setData(data);
        }
    }
    
    class SimpleUsbDeviceThatRespondReadColor extends DumbSimpleUsbDeviceThatSetDataInIRPWhenSyncSubmit {
        public SimpleUsbDeviceThatRespondReadColor() {
            super(new byte[] {0x01, 'r', (byte) 10, (byte) 100, (byte) 200, 0, 0, 0});
        }
    }
}
