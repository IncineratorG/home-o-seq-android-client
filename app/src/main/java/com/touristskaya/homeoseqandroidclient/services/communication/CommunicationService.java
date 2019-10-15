package com.touristskaya.homeoseqandroidclient.services.communication;

import android.util.Log;

import com.touristskaya.homeoseqandroidclient.services.communication.common.TransmissionService;
import com.touristskaya.homeoseqandroidclient.services.communication.firebase.FirebaseCommunicationService;
import com.touristskaya.homeoseqandroidclient.stores.common.State;

public class CommunicationService implements TransmissionService {
    private static final String TAG = "tag";

    private static final String CLASS_NAME = "CommunicationService";

    private TransmissionService mCurrentService;


    public CommunicationService() {
        CommunicationServiceState state = new CommunicationServiceState();
        mCurrentService = new FirebaseCommunicationService(state);
    }

    @Override
    public void sendSerializedString(String s) {
        mCurrentService.sendSerializedString(s);
    }

    @Override
    public void start(Object params) {
        String METHOD_NAME = ".start()";
        Log.d(TAG, CLASS_NAME + METHOD_NAME);

        mCurrentService.start(params);
    }

    @Override
    public void stop() {
        mCurrentService.stop();
    }

    @Override
    public State getState() {
        return mCurrentService.getState();
    }
}
