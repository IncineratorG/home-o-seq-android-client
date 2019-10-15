package com.touristskaya.homeoseqandroidclient.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.touristskaya.homeoseqandroidclient.R;
import com.touristskaya.homeoseqandroidclient.services.Services;
import com.touristskaya.homeoseqandroidclient.services.communication.CommunicationService;
import com.touristskaya.homeoseqandroidclient.services.communication.firebase.FirebaseCommunicationAndroidService;
import com.touristskaya.homeoseqandroidclient.stores.Stores;
import com.touristskaya.homeoseqandroidclient.stores.common.Action;
import com.touristskaya.homeoseqandroidclient.stores.common.Payload;
import com.touristskaya.homeoseqandroidclient.stores.communication.CommunicationActionsFactory;
import com.touristskaya.homeoseqandroidclient.stores.communication.CommunicationState;
import com.touristskaya.homeoseqandroidclient.stores.communication.CommunicationStore;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TAG";

    private TextView mTextView;
    private Button mButton;

    private CommunicationStore mCommunicationStore;
    private CommunicationState mCommunicationState;

    private int mCounter = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "STARTED");

        mTextView = findViewById(R.id.textView);
        mTextView.setText("HELLO_WORLD_!");

        mButton = findViewById(R.id.button);
//        mButton.setOnClickListener((view) -> {
//            if (mCounter % 2 > 0) {
//                String TEST_FIELD = "TEST_FIELD";
//                FirebaseDatabase.getInstance().goOnline();
//                DatabaseReference mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
//                mFirebaseDatabase.child(TEST_FIELD).addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        if (dataSnapshot == null || dataSnapshot.getValue() == null) {
//                            Log.d(TAG, "DATA_IS_EMPTY");
//                            return;
//                        }
//
//                        Log.d(TAG, "MESSAGE_NOT_NULL: " + dataSnapshot.getValue().toString());
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
//
//                Log.d(TAG, "LISTENING");
//            } else {
//                FirebaseDatabase.getInstance().goOffline();
//                Log.d(TAG, "GO_OFFLINE");
//            }
//
//            ++mCounter;
//        });
        mButton.setOnClickListener((view) -> {
            if (mCounter % 2 > 0) {
                Payload payload = new Payload();
                payload.set("activity", this);

                Action startCommunicationService = mCommunicationStore.getActionFactory().getAction(CommunicationActionsFactory.StartCommunicationService);
                startCommunicationService.setPayload(payload);

                mCommunicationStore.dispatch(startCommunicationService);
            } else {
                Action stopCommunicationService = mCommunicationStore.getActionFactory().getAction(CommunicationActionsFactory.StopCommunicationService);
                mCommunicationStore.dispatch(stopCommunicationService);
            }

            ++mCounter;
        });
//        mButton.setOnClickListener((view) -> {
//            if (mCounter % 2 > 0) {
//                CommunicationService communicationService = (CommunicationService) Services.getInstance().getService(Services.CommunicationService);
//                communicationService.sendSerializedString("ONE");
//            } else {
//                CommunicationService communicationService = (CommunicationService) Services.getInstance().getService(Services.CommunicationService);
//                communicationService.sendSerializedString("TWO");
//            }
//
//            ++mCounter;
//        });

        mCommunicationStore = (CommunicationStore) Stores.getInstance().getStore(Stores.CommunicationStore);
        mCommunicationState = (CommunicationState) mCommunicationStore.getState();


//        String TEST_FIELD = "TEST_FIELD";
//
//        DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference();
//        firebaseDatabase.child(TEST_FIELD).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot == null || dataSnapshot.getValue() == null) {
//                    Log.d(TAG, "DATA_IS_EMPTY");
//                    return;
//                }
//
//                Log.d(TAG, "MESSAGE_NOT_NULL");
//                Log.d(TAG, "MESSAGE: " + dataSnapshot.getValue().toString());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }

    @Override
    protected void onStop() {
        super.onStop();

//        FirebaseDatabase.getInstance().goOffline();
    }
}
