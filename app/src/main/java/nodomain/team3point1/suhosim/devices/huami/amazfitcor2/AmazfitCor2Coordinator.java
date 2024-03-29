/*  Copyright (C) 2017-2019 Andreas Shimokawa, Daniele Gobbetti, João
    Paulo Barraca, Matthieu Baerts

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
package nodomain.team3point1.suhosim.devices.huami.amazfitcor2;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nodomain.team3point1.suhosim.R;
import nodomain.team3point1.suhosim.devices.InstallHandler;
import nodomain.team3point1.suhosim.devices.huami.HuamiCoordinator;
import nodomain.team3point1.suhosim.impl.GBDevice;
import nodomain.team3point1.suhosim.impl.GBDeviceCandidate;
import nodomain.team3point1.suhosim.model.DeviceType;

public class AmazfitCor2Coordinator extends HuamiCoordinator {
    private static final Logger LOG = LoggerFactory.getLogger(AmazfitCor2Coordinator.class);

    @Override
    public DeviceType getDeviceType() {
        return DeviceType.AMAZFITCOR2;
    }

    @NonNull
    @Override
    public DeviceType getSupportedType(GBDeviceCandidate candidate) {
        try {
            BluetoothDevice device = candidate.getDevice();
            String name = device.getName();
            if (name != null && (name.equalsIgnoreCase("Amazfit Band 2") || name.equalsIgnoreCase("Amazfit Cor 2"))) {
                return DeviceType.AMAZFITCOR2;
            }
        } catch (Exception ex) {
            LOG.error("unable to check device support", ex);
        }
        return DeviceType.UNKNOWN;
    }

    @Override
    public InstallHandler findInstallHandler(Uri uri, Context context) {
        AmazfitCor2FWInstallHandler handler = new AmazfitCor2FWInstallHandler(uri, context);
        return handler.isValid() ? handler : null;
    }

    @Override
    public boolean supportsHeartRateMeasurement(GBDevice device) {
        return true;
    }

    @Override
    public boolean supportsWeather() {
        return true;
    }

    @Override
    public boolean supportsMusicInfo() {
        return true;
    }

    @Override
    public boolean supportsUnicodeEmojis() {
        return true;
    }

    @Override
    public int[] getSupportedDeviceSpecificSettings(GBDevice device) {
        return new int[]{
                R.xml.devicesettings_amazfitcor,
                R.xml.devicesettings_liftwrist_display,
                R.xml.devicesettings_disconnectnotification,
                R.xml.devicesettings_pairingkey};
    }
}
