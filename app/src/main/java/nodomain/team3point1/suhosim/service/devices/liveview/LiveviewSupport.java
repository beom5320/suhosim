/*  Copyright (C) 2016-2019 Andreas Shimokawa, Daniele Gobbetti, Sebastian
    Kranz

    This file is part of Gadgetbridge.

    Gadgetbridge is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Gadgetbridge is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>. */
package nodomain.team3point1.suhosim.service.devices.liveview;

import android.net.Uri;

import java.util.ArrayList;
import java.util.UUID;

import nodomain.team3point1.suhosim.model.Alarm;
import nodomain.team3point1.suhosim.model.CalendarEventSpec;
import nodomain.team3point1.suhosim.model.CallSpec;
import nodomain.team3point1.suhosim.model.MusicSpec;
import nodomain.team3point1.suhosim.model.MusicStateSpec;
import nodomain.team3point1.suhosim.model.NotificationSpec;
import nodomain.team3point1.suhosim.service.serial.AbstractSerialDeviceSupport;
import nodomain.team3point1.suhosim.service.serial.GBDeviceIoThread;
import nodomain.team3point1.suhosim.service.serial.GBDeviceProtocol;

public class LiveviewSupport extends AbstractSerialDeviceSupport {

    @Override
    public boolean connect() {
        getDeviceIOThread().start();
        return true;
    }

    @Override
    protected GBDeviceProtocol createDeviceProtocol() {
        return new LiveviewProtocol(getDevice());
    }

    @Override
    protected GBDeviceIoThread createDeviceIOThread() {
        return new LiveviewIoThread(getDevice(), getContext(), getDeviceProtocol(), LiveviewSupport.this, getBluetoothAdapter());
    }

    @Override
    public boolean useAutoConnect() {
        return false;
    }

    @Override
    public void onInstallApp(Uri uri) {
        //nothing to do ATM
    }

    @Override
    public void onAppConfiguration(UUID uuid, String config, Integer id) {
        //nothing to do ATM
    }

    @Override
    public void onHeartRateTest() {
        //nothing to do ATM
    }

    @Override
    public void onSetConstantVibration(int intensity) {
        //nothing to do ATM
    }

    @Override
    public void onSetHeartRateMeasurementInterval(int seconds) {

    }

    @Override
    public synchronized LiveviewIoThread getDeviceIOThread() {
        return (LiveviewIoThread) super.getDeviceIOThread();
    }

    @Override
    public void onNotification(NotificationSpec notificationSpec) {
        super.onNotification(notificationSpec);
    }

    @Override
    public void onSetCallState(CallSpec callSpec) {
        //nothing to do ATM
    }

    @Override
    public void onSetMusicState(MusicStateSpec musicStateSpec) {
        //nothing to do ATM
    }

    @Override
    public void onSetMusicInfo(MusicSpec musicSpec) {
        //nothing to do ATM
    }


    @Override
    public void onSetAlarms(ArrayList<? extends Alarm> alarms) {
        //nothing to do ATM
    }

    @Override
    public void onAddCalendarEvent(CalendarEventSpec calendarEventSpec) {
        //nothing to do ATM
    }

    @Override
    public void onDeleteCalendarEvent(byte type, long id) {
        //nothing to do ATM
    }

    @Override
    public void onTestNewFunction() {
        //nothing to do ATM
    }

    @Override
    public void onReadConfiguration(String config) {

    }
}
