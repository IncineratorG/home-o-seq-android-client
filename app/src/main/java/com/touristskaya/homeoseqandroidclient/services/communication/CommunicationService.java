package com.touristskaya.homeoseqandroidclient.services.communication;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.touristskaya.homeoseqandroidclient.services.communication.common.TransmissionService;
import com.touristskaya.homeoseqandroidclient.services.communication.firebase.FirebaseCommunicationAndroidService;
import com.touristskaya.homeoseqandroidclient.services.communication.firebase.FirebaseCommunicationService;
import com.touristskaya.homeoseqandroidclient.stores.common.State;

import java.time.chrono.MinguoEra;

public class CommunicationService implements TransmissionService {
    private static final String TAG = "tag";

    private static final String CLASS_NAME = "CommunicationService";

//    private FirebaseCommunicationService mFirebaseCommunicationService;
//    private FirebaseCommunicationAndroidService mFirebaseAndroidService;

    private Activity mServiceLaunchingActivity;

    public CommunicationService() {
        CommunicationServiceState state = new CommunicationServiceState();

//        mFirebaseAndroidService = new FirebaseCommunicationAndroidService();

//        mFirebaseCommunicationService = new FirebaseCommunicationService(state);
    }

    @Override
    public void sendSerializedString(String s) {
//        mFirebaseCommunicationService.sendSerializedString(s);
    }

    @Override
    public void start(Object params) {
        String METHOD_NAME = ".start()";
        Log.d(TAG, CLASS_NAME + METHOD_NAME);

        if (!(params instanceof Activity)) {
            Log.d(TAG, CLASS_NAME + METHOD_NAME + "->BAD_PARAMS");
            return;
        }

        mServiceLaunchingActivity = (Activity) params;
        mServiceLaunchingActivity.startService(new Intent(mServiceLaunchingActivity.getBaseContext(), FirebaseCommunicationAndroidService.class));

//        mFirebaseCommunicationService.start(null);
    }

    @Override
    public void stop() {
        String METHOD_NAME = ".stop()";
        Log.d(TAG, CLASS_NAME + METHOD_NAME);

        if (mServiceLaunchingActivity == null) {
            Log.d(TAG, CLASS_NAME + METHOD_NAME + "->BAD_LAUNCHING_ACTIVITY");
            return;
        }

        mServiceLaunchingActivity.stopService(new Intent(mServiceLaunchingActivity.getBaseContext(), FirebaseCommunicationAndroidService.class));

//        mFirebaseCommunicationService.stop();
    }

    @Override
    public State getState() {
//        return mFirebaseCommunicationService.getState();
        return null;
    }
}
