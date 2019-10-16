package com.touristskaya.homeoseqandroidclient.services.common;

import com.touristskaya.homeoseqlib.redux.State;

public interface DaemonService extends Service {
    void start(Object params);
    void stop();
    State getState();
}
