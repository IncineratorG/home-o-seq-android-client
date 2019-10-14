package com.touristskaya.homeoseqandroidclient.services.communication;

import com.touristskaya.homeoseqandroidclient.services.communication.common.TransmissionService;
import com.touristskaya.homeoseqandroidclient.services.communication.firebase.FirebaseCommunicationService;
import com.touristskaya.homeoseqandroidclient.stores.common.State;

public class CommunicationService implements TransmissionService {
    private FirebaseCommunicationService mFirebaseCommunicationService;


    public CommunicationService() {
        CommunicationServiceState state = new CommunicationServiceState();

        mFirebaseCommunicationService = new FirebaseCommunicationService(state);
    }

    @Override
    public void sendSerializedString(String s) {
        mFirebaseCommunicationService.sendSerializedString(s);
    }

    @Override
    public void start() {
        mFirebaseCommunicationService.start();
    }

    @Override
    public void stop() {
        mFirebaseCommunicationService.stop();
    }

    @Override
    public State getState() {
        return mFirebaseCommunicationService.getState();
    }
}
