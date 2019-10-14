package com.touristskaya.homeoseqandroidclient.services.communication.firebase;


import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.touristskaya.homeoseqandroidclient.services.communication.CommunicationServiceState;
import com.touristskaya.homeoseqandroidclient.services.communication.common.TransmissionService;
import com.touristskaya.homeoseqandroidclient.services.communication.tasks.FirebaseCommunicationServiceTask;
import com.touristskaya.homeoseqandroidclient.stores.common.State;

public class FirebaseCommunicationService implements TransmissionService {
    private static final String TAG = "tag";

    private static final String CLASS_NAME = "FirebaseCommunicationService";

    private static String mFirebaseUrl = "https://surveillance-136a9.firebaseio.com/";
    private static String mClientStateField = "TEST_FIELD";
    private static String mServerStateField = "TEST_FIELD";

    private CommunicationServiceState mState;

    private FirebaseCommunicationServiceTask mServiceTask;


    public FirebaseCommunicationService(State state) {
        mState = (CommunicationServiceState) state;
    }

    @Override
    public void sendSerializedString(String s) {

    }

    @Override
    public void start(Object params) {
        String METHOD_NAME = ".start()";

        Log.d(TAG, CLASS_NAME + METHOD_NAME);

        mServiceTask = new FirebaseCommunicationServiceTask();
        mServiceTask.execute();
    }

    @Override
    public void stop() {
        String METHOD_NAME = ".stop()";

        Log.d(TAG, CLASS_NAME + METHOD_NAME);

        mServiceTask.cancel(true);
    }

    @Override
    public State getState() {
        return mState;
    }
}
