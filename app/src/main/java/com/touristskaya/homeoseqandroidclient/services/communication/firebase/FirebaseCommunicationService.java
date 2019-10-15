package com.touristskaya.homeoseqandroidclient.services.communication.firebase;


import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.touristskaya.homeoseqandroidclient.services.communication.CommunicationServiceState;
import com.touristskaya.homeoseqandroidclient.services.communication.common.TransmissionService;
import com.touristskaya.homeoseqandroidclient.stores.common.State;

public class FirebaseCommunicationService implements TransmissionService {
    private static final String TAG = "tag";

    private static final String CLASS_NAME = "FirebaseCommunicationService";

    private static String mFirebaseUrl = "https://surveillance-136a9.firebaseio.com/";
    private static String mClientStateField = "TEST_FIELD";
    private static String mServerStateField = "TEST_FIELD";

    private Activity mFirebaseAndroidServiceLaunchingActivity;
    private FirebaseCommunicationAndroidService mFirebaseAndroidService;
    private FirebaseServiceBus mServiceBus;

    private CommunicationServiceState mState;


    public FirebaseCommunicationService(State state) {
        mState = (CommunicationServiceState) state;
        mServiceBus = FirebaseServiceBus.getInstance();
    }

    @Override
    public void sendSerializedString(String s) {
        String METHOD_NAME = ".sendSerializedString()";
        Log.d(TAG, CLASS_NAME + METHOD_NAME);
    }

    @Override
    public void start(Object params) {
        String METHOD_NAME = ".start()";
        Log.d(TAG, CLASS_NAME + METHOD_NAME);

        if (!(params instanceof Activity)) {
            Log.d(TAG, CLASS_NAME + METHOD_NAME + "->BAD_PARAMS");
            return;
        }

        mFirebaseAndroidServiceLaunchingActivity = (Activity) params;
        mFirebaseAndroidServiceLaunchingActivity.startService(
                new Intent(mFirebaseAndroidServiceLaunchingActivity.getBaseContext(),
                           FirebaseCommunicationAndroidService.class));
    }

    @Override
    public void stop() {
        String METHOD_NAME = ".stop()";
        Log.d(TAG, CLASS_NAME + METHOD_NAME);

        if (mFirebaseAndroidServiceLaunchingActivity == null) {
            Log.d(TAG, CLASS_NAME + METHOD_NAME + "->BAD_LAUNCHING_ACTIVITY");
            return;
        }

        mFirebaseAndroidServiceLaunchingActivity.stopService(
                new Intent(mFirebaseAndroidServiceLaunchingActivity.getBaseContext(),
                           FirebaseCommunicationAndroidService.class));
    }

    @Override
    public State getState() {
        return mState;
    }
}
