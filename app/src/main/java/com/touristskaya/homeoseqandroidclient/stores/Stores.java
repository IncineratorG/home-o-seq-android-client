package com.touristskaya.homeoseqandroidclient.stores;

import com.touristskaya.homeoseqandroidclient.stores.common.Store;
import com.touristskaya.homeoseqandroidclient.stores.communication.CommunicationStore;

/**
 * TODO: Add a class header comment
 */
public class Stores {
    private static Stores mInstance = null;

    private CommunicationStore mCommunicationStore;

    public static final int CommunicationStore = 1;


    private Stores() {
        mCommunicationStore = new CommunicationStore();
    }

    public static synchronized Stores getInstance() {
        if (mInstance != null)
            return mInstance;
        else {
            mInstance = new Stores();
            return mInstance;
        }
    }

    public Store getStore(int type) {
        switch (type) {
            case CommunicationStore: {
                return mCommunicationStore;
            }
        }

        return null;
    }
}
