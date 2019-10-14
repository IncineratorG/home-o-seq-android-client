package com.touristskaya.homeoseqandroidclient.stores.common;

/**
 * TODO: Add a class header comment
 */
public interface Action {
    int getType();
    Object getPayload();
    void setPayload(Object payload);
}
