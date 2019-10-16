package com.touristskaya.homeoseqandroidclient.stores.communication.actions;

import com.touristskaya.homeoseqandroidclient.stores.communication.CommunicationActionsFactory;
import com.touristskaya.homeoseqlib.redux.Action;

/**
 * TODO: Add a class header comment
 */
public class BindCommunicationServiceStateAction implements Action {
    private Object mPayload;


    @Override
    public int getType() {
        return CommunicationActionsFactory.BindCommunicationServiceState;
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