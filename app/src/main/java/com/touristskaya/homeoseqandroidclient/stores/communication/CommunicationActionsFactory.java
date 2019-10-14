package com.touristskaya.homeoseqandroidclient.stores.communication;

import com.touristskaya.homeoseqandroidclient.stores.common.Action;
import com.touristskaya.homeoseqandroidclient.stores.common.ActionsFactory;
import com.touristskaya.homeoseqandroidclient.stores.communication.actions.BindCommunicationServiceStateAction;
import com.touristskaya.homeoseqandroidclient.stores.communication.actions.StartCommunicationServiceAction;
import com.touristskaya.homeoseqandroidclient.stores.communication.actions.StopCommunicationServiceAction;

/**
 * TODO: Add a class header comment
 */
public class CommunicationActionsFactory implements ActionsFactory {
    public static final int StartCommunicationService = 1;
    public static final int StopCommunicationService = 2;
    public static final int BindCommunicationServiceState = 3;


    @Override
    public Action getAction(int type) {
        switch (type) {
            case StartCommunicationService: {
                return new StartCommunicationServiceAction();
            }

            case StopCommunicationService: {
                return new StopCommunicationServiceAction();
            }

            case BindCommunicationServiceState: {
                return new BindCommunicationServiceStateAction();
            }
        }

        return null;
    }
}
