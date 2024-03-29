package nodomain.team3point1.suhosim.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.GregorianCalendar;

import nodomain.team3point1.suhosim.Logging;
import nodomain.team3point1.suhosim.devices.miband.MiBandDateConverter;
import nodomain.team3point1.suhosim.service.btle.BLETypeConversions;
import nodomain.team3point1.suhosim.util.DateTimeUtils;

/**
 * A simple class for trying out things, not actually testing something.
 */
public class Tryout extends TestBase {
    private static final Logger LOG = LoggerFactory.getLogger(Tryout.class);

    @Test
    public void blah() {
        int v = 1 << 7 | 1 << 2;
        byte b = (byte) v;
        LOG.info("v: " + v);
        Logging.logBytes(LOG, new byte[] { b });

        byte[] bs = new byte[] {(byte) 0xf0,0x28,0x00,0x00 };
        LOG.warn("uint32: " + BLETypeConversions.toUint32(bs));
        LOG.warn("uint16: " + BLETypeConversions.toUint16(bs));

    }

    @Test
    public void testCalendarBytes() {
        GregorianCalendar calendar = MiBandDateConverter.createCalendar();
        byte[] bytes = MiBandDateConverter.calendarToRawBytes(calendar,"fake");
        LOG.info("Calender: " + DateTimeUtils.formatDateTime(calendar.getTime()));
        Logging.logBytes(LOG, bytes);
    }


}
