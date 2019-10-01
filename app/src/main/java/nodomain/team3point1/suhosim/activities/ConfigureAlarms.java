/*  Copyright (C) 2015-2019 Andreas Shimokawa, Carsten Pfeiffer, Daniele
    Gobbetti, Lem Dulfo

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
package nodomain.team3point1.suhosim.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import nodomain.team3point1.suhosim.GBApplication;
import nodomain.team3point1.suhosim.R;
import nodomain.team3point1.suhosim.adapter.GBAlarmListAdapter;
import nodomain.team3point1.suhosim.database.DBHandler;
import nodomain.team3point1.suhosim.database.DBHelper;
import nodomain.team3point1.suhosim.devices.DeviceCoordinator;
import nodomain.team3point1.suhosim.entities.Alarm;
import nodomain.team3point1.suhosim.entities.DaoSession;
import nodomain.team3point1.suhosim.entities.Device;
import nodomain.team3point1.suhosim.entities.User;
import nodomain.team3point1.suhosim.impl.GBDevice;
import nodomain.team3point1.suhosim.util.AlarmUtils;
import nodomain.team3point1.suhosim.util.DeviceHelper;


public class ConfigureAlarms extends AbstractGBActivity {
    private static final Logger LOG = LoggerFactory.getLogger(ConfigureAlarms.class);

    private static final int REQ_CONFIGURE_ALARM = 1;

    private GBAlarmListAdapter mGBAlarmListAdapter;
    private boolean avoidSendAlarmsToDevice;
    private GBDevice gbDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_configure_alarms);

        gbDevice = getIntent().getParcelableExtra(GBDevice.EXTRA_DEVICE);

        mGBAlarmListAdapter = new GBAlarmListAdapter(this);

        RecyclerView alarmsRecyclerView = findViewById(R.id.alarm_list);
        alarmsRecyclerView.setHasFixedSize(true);
        alarmsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        alarmsRecyclerView.setAdapter(mGBAlarmListAdapter);
        updateAlarmsFromDB();
    }

    @Override
    protected void onPause() {
        if (!avoidSendAlarmsToDevice && gbDevice.isInitialized()) {
            sendAlarmsToDevice();
        }
        super.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CONFIGURE_ALARM) {
            avoidSendAlarmsToDevice = false;
            updateAlarmsFromDB();
        }
    }

    /**
     * Reads the available alarms from the database and updates the view afterwards.
     */
    private void updateAlarmsFromDB() {
        List<Alarm> alarms = DBHelper.getAlarms(getGbDevice());
        if (alarms.isEmpty()) {
            alarms = AlarmUtils.readAlarmsFromPrefs(getGbDevice());
            storeMigratedAlarms(alarms);
        }
        addMissingAlarms(alarms);

        mGBAlarmListAdapter.setAlarmList(alarms);
        mGBAlarmListAdapter.notifyDataSetChanged();
    }

    private void storeMigratedAlarms(List<Alarm> alarms) {
        for (Alarm alarm : alarms) {
            DBHelper.store(alarm);
        }
    }

    private void addMissingAlarms(List<Alarm> alarms) {
        DeviceCoordinator coordinator = DeviceHelper.getInstance().getCoordinator(getGbDevice());
        int supportedNumAlarms = coordinator.getAlarmSlotCount();
        if (supportedNumAlarms > alarms.size()) {
            try (DBHandler db = GBApplication.acquireDB()) {
                DaoSession daoSession = db.getDaoSession();
                Device device = DBHelper.getDevice(getGbDevice(), daoSession);
                User user = DBHelper.getUser(daoSession);
                for (int position = 0; position < supportedNumAlarms; position++) {
                    boolean found = false;
                    for (Alarm alarm : alarms) {
                        if (alarm.getPosition() == position) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        LOG.info("adding missing alarm at position " + position);
                        alarms.add(position, createDefaultAlarm(device, user, position));
                    }
                }
            } catch (Exception e) {
                LOG.error("Error accessing database", e);
            }
        }
    }

    private Alarm createDefaultAlarm(@NonNull Device device, @NonNull User user, int position) {
        return new Alarm(device.getId(), user.getId(), position, false, false,0, 6, 30);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // back button
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void configureAlarm(Alarm alarm) {
        avoidSendAlarmsToDevice = true;
        Intent startIntent = new Intent(getApplicationContext(), AlarmDetails.class);
        startIntent.putExtra(Alarm.EXTRA_ALARM, alarm);
        startIntent.putExtra(GBDevice.EXTRA_DEVICE, getGbDevice());
        startActivityForResult(startIntent, REQ_CONFIGURE_ALARM);
    }

    private GBDevice getGbDevice() {
        return gbDevice;
    }

    private void sendAlarmsToDevice() {
        GBApplication.deviceService().onSetAlarms(mGBAlarmListAdapter.getAlarmList());
    }
}
