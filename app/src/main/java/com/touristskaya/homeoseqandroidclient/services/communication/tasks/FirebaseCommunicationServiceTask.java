package com.touristskaya.homeoseqandroidclient.services.communication.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * TODO: Add a class header comment
 */
public class FirebaseCommunicationServiceTask extends AsyncTask<Object, Object, Object> {
    private static String TAG = "tag";

    private static String CLASS_NAME = "FirebaseCommunicationServiceTask";

    private DatabaseReference mFirebaseDatabase;


    public FirebaseCommunicationServiceTask() {
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    protected Object doInBackground(Object... objects) {
        String TEST_FIELD = "TEST_FIELD";
        mFirebaseDatabase.child(TEST_FIELD).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot == null || dataSnapshot.getValue() == null) {
                    Log.d(TAG, "DATA_IS_EMPTY");
                    return;
                }

                Log.d(TAG, "MESSAGE_NOT_NULL: " + dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return null;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();

        FirebaseDatabase.getInstance().goOffline();

        String METHOD_NAME = ".onCancelled()";
        Log.d(TAG, CLASS_NAME + METHOD_NAME);
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        FirebaseDatabase.getInstance().goOffline();

        String METHOD_NAME = ".onPostExecute()";
        Log.d(TAG, CLASS_NAME + METHOD_NAME);
    }
}
