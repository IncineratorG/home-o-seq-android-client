package com.touristskaya.homeoseqandroidclient.services.common;

import com.touristskaya.homeoseqandroidclient.stores.common.State;

public interface DaemonService extends Service {
    void start();
    void stop();
    State getState();
}
