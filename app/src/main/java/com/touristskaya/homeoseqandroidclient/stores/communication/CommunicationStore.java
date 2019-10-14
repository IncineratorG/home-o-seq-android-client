package com.touristskaya.homeoseqandroidclient.stores.communication;

import android.app.Activity;
import android.util.Log;

import com.touristskaya.homeoseqandroidclient.services.Services;
import com.touristskaya.homeoseqandroidclient.services.communication.CommunicationService;
import com.touristskaya.homeoseqandroidclient.stores.common.Action;
import com.touristskaya.homeoseqandroidclient.stores.common.ActionsFactory;
import com.touristskaya.homeoseqandroidclient.stores.common.Payload;
import com.touristskaya.homeoseqandroidclient.stores.common.State;
import com.touristskaya.homeoseqandroidclient.stores.common.Store;

/**
 * TODO: Add a class header comment
 */
public class CommunicationStore extends Store {
    private static final String TAG = "tag";

    private CommunicationState mState;
    private CommunicationActionsFactory mActionsFactory;
    private CommunicationService mCommunicationService;


    public CommunicationStore() {
        mState = new CommunicationState();
        mActionsFactory = new CommunicationActionsFactory();
        mCommunicationService = (CommunicationService) Services.getInstance().getService(Services.CommunicationService);
    }

    @Override
    public State getState() {
        return mState;
    }

    @Override
    public ActionsFactory getActionFactory() {
        return mActionsFactory;
    }

    @Override
    protected void reduce(Action action) {
        switch (action.getType()) {
            case CommunicationActionsFactory.StartCommunicationService: {
                startCommunicationServiceReducer(action);
                break;
            }

            case CommunicationActionsFactory.StopCommunicationService: {
                stopCommunicationServiceReducer(action);
                break;
            }

            case CommunicationActionsFactory.BindCommunicationServiceState: {
                bindCommunicationServiceStateReducer(action);
                break;
            }
        }
    }

    @Override
    protected void effect(Action action) {

    }


    private void startCommunicationServiceReducer(Action action) {
        if (!(action.getPayload() instanceof Payload)) {
            Log.d(TAG, "CommunicationStore.startCommunicationServiceReducer()->BAD_PAYLOAD");
            return;
        }

        Payload payload = (Payload) action.getPayload();
        Activity activity = null;
        if (payload.get("activity") instanceof Activity) {
            activity = (Activity) payload.get("activity");
        } else {
            Log.d(TAG, "CommunicationStore.startCommunicationServiceReducer()->BAD_PAYLOAD_DATA");
            return;
        }

        mCommunicationService.start(activity);
    }

    private void stopCommunicationServiceReducer(Action action) {
        mCommunicationService.stop();
    }

    private void bindCommunicationServiceStateReducer(Action action) {

    }
}
