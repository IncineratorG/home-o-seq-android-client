package com.touristskaya.homeoseqandroidclient.services;

import com.touristskaya.homeoseqandroidclient.services.common.Service;
import com.touristskaya.homeoseqandroidclient.services.communication.CommunicationService;

/**
 * TODO: Add a class header comment
 */
public class Services {
    private static Services mInstance = null;

    private com.touristskaya.homeoseqandroidclient.services.communication.CommunicationService mCommunicationService;

    public static final int CommunicationService = 1;


    private Services() {
        mCommunicationService = new CommunicationService();
    }

    public static synchronized Services getInstance() {
        if (mInstance != null)
            return mInstance;
        else {
            mInstance = new Services();
            return mInstance;
        }
    }

    public Service getService(int type) {
        switch (type) {
            case CommunicationService: {
                return mCommunicationService;
            }
        }

        return null;
    }
}
