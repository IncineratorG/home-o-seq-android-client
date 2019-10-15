package com.touristskaya.homeoseqandroidclient.services.communication.firebase;

import com.touristskaya.homeoseqandroidclient.common.types.reactive.Observable;

/**
 * TODO: Add a class header comment
 */
public class FirebaseServiceBus extends Observable {
    private static FirebaseServiceBus mInstance = null;


    private FirebaseServiceBus() {

    }

    public static synchronized FirebaseServiceBus getInstance() {
        if (mInstance != null)
            return mInstance;
        else {
            mInstance = new FirebaseServiceBus();
            return mInstance;
        }
    }

    public void setClientState(String s) {

    }
}
