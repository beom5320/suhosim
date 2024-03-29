/*  Copyright (C) 2016-2019 Andreas Shimokawa, Carsten Pfeiffer, Daniele
    Gobbetti, José Rebelo

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
package nodomain.team3point1.suhosim.devices.huami;

import android.annotation.TargetApi;
import android.app.Activity;
import android.bluetooth.le.ScanFilter;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.ParcelUuid;

import androidx.annotation.NonNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import de.greenrobot.dao.query.QueryBuilder;
import nodomain.team3point1.suhosim.GBApplication;
import nodomain.team3point1.suhosim.GBException;
import nodomain.team3point1.suhosim.R;
import nodomain.team3point1.suhosim.activities.SettingsActivity;
import nodomain.team3point1.suhosim.devices.AbstractDeviceCoordinator;
import nodomain.team3point1.suhosim.devices.SampleProvider;
import nodomain.team3point1.suhosim.devices.miband.DateTimeDisplay;
import nodomain.team3point1.suhosim.devices.miband.DoNotDisturb;
import nodomain.team3point1.suhosim.devices.miband.MiBand2SampleProvider;
import nodomain.team3point1.suhosim.devices.miband.MiBandConst;
import nodomain.team3point1.suhosim.devices.miband.MiBandPairingActivity;
import nodomain.team3point1.suhosim.devices.miband.MiBandService;
import nodomain.team3point1.suhosim.entities.AbstractActivitySample;
import nodomain.team3point1.suhosim.entities.DaoSession;
import nodomain.team3point1.suhosim.entities.Device;
import nodomain.team3point1.suhosim.entities.MiBandActivitySampleDao;
import nodomain.team3point1.suhosim.impl.GBDevice;
import nodomain.team3point1.suhosim.util.Prefs;

public abstract class HuamiCoordinator extends AbstractDeviceCoordinator {
    private static final Logger LOG = LoggerFactory.getLogger(HuamiCoordinator.class);

    @Override
    public Class<? extends Activity> getPairingActivity() {
        return MiBandPairingActivity.class;
    }

    @NonNull
    @Override
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Collection<? extends ScanFilter> createBLEScanFilters() {
        ParcelUuid mi2Service = new ParcelUuid(MiBandService.UUID_SERVICE_MIBAND2_SERVICE);
        ScanFilter filter = new ScanFilter.Builder().setServiceUuid(mi2Service).build();
        return Collections.singletonList(filter);
    }

    @Override
    protected void deleteDevice(@NonNull GBDevice gbDevice, @NonNull Device device, @NonNull DaoSession session) throws GBException {
        Long deviceId = device.getId();
        QueryBuilder<?> qb = session.getMiBandActivitySampleDao().queryBuilder();
        qb.where(MiBandActivitySampleDao.Properties.DeviceId.eq(deviceId)).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override
    public String getManufacturer() {
        return "Huami";
    }

    @Override
    public boolean supportsAppsManagement() {
        return false;
    }

    @Override
    public Class<? extends Activity> getAppsManagementActivity() {
        return null;
    }

    @Override
    public boolean supportsCalendarEvents() {
        return false;
    }

    @Override
    public boolean supportsRealtimeData() {
        return true;
    }

    @Override
    public int getAlarmSlotCount() {
        return 10;
    }

    @Override
    public boolean supportsActivityDataFetching() {
        return true;
    }

    @Override
    public boolean supportsActivityTracking() {
        return true;
    }

    @Override
    public int[] getSupportedDeviceSpecificSettings(GBDevice device) {
        return new int[]{R.xml.devicesettings_pairingkey};
    }

    @Override
    public SampleProvider<? extends AbstractActivitySample> getSampleProvider(GBDevice device, DaoSession session) {
        return new MiBand2SampleProvider(device, session);
    }

    public static DateTimeDisplay getDateDisplay(Context context, String deviceAddress) throws IllegalArgumentException {
        SharedPreferences sharedPrefs = GBApplication.getDeviceSpecificSharedPrefs(deviceAddress);
        String dateFormatTime = context.getString(R.string.p_dateformat_time);
        if (dateFormatTime.equals(sharedPrefs.getString(MiBandConst.PREF_MI2_DATEFORMAT, dateFormatTime))) {
            return DateTimeDisplay.TIME;
        }
        return DateTimeDisplay.DATE_TIME;
    }

    public static ActivateDisplayOnLift getActivateDisplayOnLiftWrist(Context context, String deviceAddress) {
        SharedPreferences prefs = GBApplication.getDeviceSpecificSharedPrefs(deviceAddress);

        String liftOff = context.getString(R.string.p_off);
        String liftOn = context.getString(R.string.p_on);
        String liftScheduled = context.getString(R.string.p_scheduled);

        String pref = prefs.getString(HuamiConst.PREF_ACTIVATE_DISPLAY_ON_LIFT, liftOff);

        if (liftOn.equals(pref)) {
            return ActivateDisplayOnLift.ON;
        } else if (liftScheduled.equals(pref)) {
            return ActivateDisplayOnLift.SCHEDULED;
        }

        return ActivateDisplayOnLift.OFF;
    }

    public static Date getDisplayOnLiftStart(String deviceAddress) {
        return getTimePreference(HuamiConst.PREF_DISPLAY_ON_LIFT_START, "00:00", deviceAddress);
    }

    public static Date getDisplayOnLiftEnd(String deviceAddress) {
        return getTimePreference(HuamiConst.PREF_DISPLAY_ON_LIFT_END, "00:00", deviceAddress);
    }

    public static DisconnectNotificationSetting getDisconnectNotificationSetting(Context context, String deviceAddress) {
        Prefs prefs = new Prefs(GBApplication.getDeviceSpecificSharedPrefs(deviceAddress));

        String liftOff = context.getString(R.string.p_off);
        String liftOn = context.getString(R.string.p_on);
        String liftScheduled = context.getString(R.string.p_scheduled);

        String pref = prefs.getString(HuamiConst.PREF_DISCONNECT_NOTIFICATION, liftOff);

        if (liftOn.equals(pref)) {
            return DisconnectNotificationSetting.ON;
        } else if (liftScheduled.equals(pref)) {
            return DisconnectNotificationSetting.SCHEDULED;
        }

        return DisconnectNotificationSetting.OFF;
    }

    public static Date getDisconnectNotificationStart(String deviceAddress) {
        return getTimePreference(HuamiConst.PREF_DISCONNECT_NOTIFICATION_START, "00:00", deviceAddress);
    }

    public static Date getDisconnectNotificationEnd(String deviceAddress) {
        return getTimePreference(HuamiConst.PREF_DISCONNECT_NOTIFICATION_END, "00:00", deviceAddress);
    }

    public static Set<String> getDisplayItems(String deviceAddress) {
        SharedPreferences prefs = GBApplication.getDeviceSpecificSharedPrefs(deviceAddress);
        return prefs.getStringSet(HuamiConst.PREF_DISPLAY_ITEMS, null);
    }

    public static boolean getUseCustomFont(String deviceAddress) {
        SharedPreferences prefs = GBApplication.getDeviceSpecificSharedPrefs(deviceAddress);
        return prefs.getBoolean(HuamiConst.PREF_USE_CUSTOM_FONT, false);
    }

    public static boolean getGoalNotification() {
        Prefs prefs = GBApplication.getPrefs();
        return prefs.getBoolean(MiBandConst.PREF_MI2_GOAL_NOTIFICATION, false);
    }

    public static boolean getRotateWristToSwitchInfo(String deviceAddress) {
        SharedPreferences prefs = GBApplication.getDeviceSpecificSharedPrefs(deviceAddress);
        return prefs.getBoolean(MiBandConst.PREF_MI2_ROTATE_WRIST_TO_SWITCH_INFO, false);
    }

    public static boolean getInactivityWarnings() {
        Prefs prefs = GBApplication.getPrefs();
        return prefs.getBoolean(MiBandConst.PREF_MI2_INACTIVITY_WARNINGS, false);
    }

    public static int getInactivityWarningsThreshold() {
        Prefs prefs = GBApplication.getPrefs();
        return prefs.getInt(MiBandConst.PREF_MI2_INACTIVITY_WARNINGS_THRESHOLD, 60);
    }

    public static boolean getInactivityWarningsDnd() {
        Prefs prefs = GBApplication.getPrefs();
        return prefs.getBoolean(MiBandConst.PREF_MI2_INACTIVITY_WARNINGS_DND, false);
    }

    public static Date getInactivityWarningsStart() {
        return getTimePreference(MiBandConst.PREF_MI2_INACTIVITY_WARNINGS_START, "06:00");
    }

    public static Date getInactivityWarningsEnd() {
        return getTimePreference(MiBandConst.PREF_MI2_INACTIVITY_WARNINGS_END, "22:00");
    }

    public static Date getInactivityWarningsDndStart() {
        return getTimePreference(MiBandConst.PREF_MI2_INACTIVITY_WARNINGS_DND_START, "12:00");
    }

    public static Date getInactivityWarningsDndEnd() {
        return getTimePreference(MiBandConst.PREF_MI2_INACTIVITY_WARNINGS_DND_END, "14:00");
    }

    public static Date getDoNotDisturbStart(String deviceAddress) {
        return getTimePreference(MiBandConst.PREF_DO_NOT_DISTURB_START, "01:00", deviceAddress);
    }

    public static Date getDoNotDisturbEnd(String deviceAddress) {
        return getTimePreference(MiBandConst.PREF_DO_NOT_DISTURB_END, "06:00", deviceAddress);
    }

    public static boolean getBandScreenUnlock(String deviceAddress) {
        Prefs prefs = new Prefs(GBApplication.getDeviceSpecificSharedPrefs(deviceAddress));
        return prefs.getBoolean(MiBandConst.PREF_SWIPE_UNLOCK, false);
    }

    public static boolean getExposeHRThirdParty(String deviceAddress) {
        Prefs prefs = new Prefs(GBApplication.getDeviceSpecificSharedPrefs(deviceAddress));
        return prefs.getBoolean(HuamiConst.PREF_EXPOSE_HR_THIRDPARTY, false);
    }

    protected static Date getTimePreference(String key, String defaultValue, String deviceAddress) {
        Prefs prefs;

        if (deviceAddress == null) {
            prefs = GBApplication.getPrefs();
        } else {
            prefs = new Prefs(GBApplication.getDeviceSpecificSharedPrefs(deviceAddress));
        }
        String time = prefs.getString(key, defaultValue);

        DateFormat df = new SimpleDateFormat("HH:mm");
        try {
            return df.parse(time);
        } catch (Exception e) {
            LOG.error("Unexpected exception in MiBand2Coordinator.getTime: " + e.getMessage());
        }

        return new Date();
    }

    protected static Date getTimePreference(String key, String defaultValue) {
        return getTimePreference(key, defaultValue, null);
    }

    public static MiBandConst.DistanceUnit getDistanceUnit() {
        Prefs prefs = GBApplication.getPrefs();
        String unit = prefs.getString(SettingsActivity.PREF_MEASUREMENT_SYSTEM, GBApplication.getContext().getString(R.string.p_unit_metric));
        if (unit.equals(GBApplication.getContext().getString(R.string.p_unit_metric))) {
            return MiBandConst.DistanceUnit.METRIC;
        } else {
            return MiBandConst.DistanceUnit.IMPERIAL;
        }
    }

    public static DoNotDisturb getDoNotDisturb(String deviceAddress) {
        SharedPreferences prefs = GBApplication.getDeviceSpecificSharedPrefs(deviceAddress);

        String pref = prefs.getString(MiBandConst.PREF_DO_NOT_DISTURB, MiBandConst.PREF_DO_NOT_DISTURB_OFF);

        if (MiBandConst.PREF_DO_NOT_DISTURB_AUTOMATIC.equals(pref)) {
            return DoNotDisturb.AUTOMATIC;
        } else if (MiBandConst.PREF_DO_NOT_DISTURB_SCHEDULED.equals(pref)) {
            return DoNotDisturb.SCHEDULED;
        }

        return DoNotDisturb.OFF;
    }

    @Override
    public boolean supportsScreenshots() {
        return false;
    }

    @Override
    public boolean supportsSmartWakeup(GBDevice device) {
        return false;
    }

    @Override
    public boolean supportsFindDevice() {
        return true;
    }
}
