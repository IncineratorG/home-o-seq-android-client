package com.touristskaya.homeoseqandroidclient.stores.communication.actions;

import com.touristskaya.homeoseqandroidclient.stores.common.Action;
import com.touristskaya.homeoseqandroidclient.stores.communication.CommunicationActionsFactory;

/**
 * TODO: Add a class header comment
 */
public class StartCommunicationServiceAction implements Action {
    private Object mPayload;


    @Override
    public int getType() {
        return CommunicationActionsFactory.StartCommunicationService;
    }

    @Override
    public Object getPayload() {
        return mPayload;
    }

    @Override
    public void setPayload(Object payload) {
        mPayload = payload;
    }
}