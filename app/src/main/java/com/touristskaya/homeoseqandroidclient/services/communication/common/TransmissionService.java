package com.touristskaya.homeoseqandroidclient.services.communication.common;

import com.touristskaya.homeoseqandroidclient.services.common.DaemonService;

public interface TransmissionService extends DaemonService {
    void sendSerializedString(String s);
}
