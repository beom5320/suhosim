/*  Copyright (C) 2015-2019 Andreas Shimokawa, Carsten Pfeiffer, Daniele
    Gobbetti, Johannes Tysiak, Normano64

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
package nodomain.team3point1.suhosim.externalevents;

import android.app.NotificationManager;
import android.app.NotificationManager.Policy;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.telephony.TelephonyManager;

import nodomain.team3point1.suhosim.GBApplication;
import nodomain.team3point1.suhosim.model.CallSpec;
import nodomain.team3point1.suhosim.util.Prefs;


public class PhoneCallReceiver extends BroadcastReceiver {

    private static int mLastState = TelephonyManager.CALL_STATE_IDLE;
    private static String mSavedNumber;
    private boolean mRestoreMutedCall = false;
    private int mLastRingerMode;

    @Override
    public void onReceive(Context context, Intent intent) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
        if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
            mSavedNumber = intent.getExtras().getString("android.intent.extra.PHONE_NUMBER");
        } else if(intent.getAction().equals("nodomain.team3point1.suhosim.MUTE_CALL")) {
            // Handle the mute request only if the phone is currently ringing
            if(mLastState != TelephonyManager.CALL_STATE_RINGING)
                return;

            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            mLastRingerMode = audioManager.getRingerMode();
            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            mRestoreMutedCall = true;
        } else {
            if (intent.hasExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)) {
                String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                int state = tm.getCallState();
                onCallStateChanged(context, state, number);
            }
        }
    }

    public void onCallStateChanged(Context context, int state, String number) {
        if (mLastState == state) {
            return;
        }

        int callCommand = CallSpec.CALL_UNDEFINED;
        switch (state) {
            case TelephonyManager.CALL_STATE_RINGING:
                mSavedNumber = number;
                callCommand = CallSpec.CALL_INCOMING;
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                if (mLastState == TelephonyManager.CALL_STATE_RINGING) {
                    callCommand = CallSpec.CALL_START;
                } else {
                    callCommand = CallSpec.CALL_OUTGOING;
                    mSavedNumber = number;
                }
                break;
            case TelephonyManager.CALL_STATE_IDLE:
                if (mLastState == TelephonyManager.CALL_STATE_RINGING) {
                    //missed call would be correct here
                    callCommand = CallSpec.CALL_END;
                } else {
                    callCommand = CallSpec.CALL_END;
                }
                if(mRestoreMutedCall) {
                    mRestoreMutedCall = false;
                    AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                    audioManager.setRingerMode(mLastRingerMode);
                }
                break;
        }
        if (callCommand != CallSpec.CALL_UNDEFINED) {
            Prefs prefs = GBApplication.getPrefs();
            if ("never".equals(prefs.getString("notification_mode_calls", "always"))) {
                return;
            }
            switch (GBApplication.getGrantedInterruptionFilter()) {
                case NotificationManager.INTERRUPTION_FILTER_ALL:
                    break;
                case NotificationManager.INTERRUPTION_FILTER_ALARMS:
                case NotificationManager.INTERRUPTION_FILTER_NONE:
                    return;
                case NotificationManager.INTERRUPTION_FILTER_PRIORITY:
                    if (GBApplication.isPriorityNumber(Policy.PRIORITY_CATEGORY_CALLS, mSavedNumber)) {
                        break;
                    }
                    // FIXME: Handle Repeat callers if it is enabled in Do Not Disturb
                    return;
            }
            CallSpec callSpec = new CallSpec();
            callSpec.number = mSavedNumber;
            callSpec.command = callCommand;
            GBApplication.deviceService().onSetCallState(callSpec);
        }
        mLastState = state;
    }
}
