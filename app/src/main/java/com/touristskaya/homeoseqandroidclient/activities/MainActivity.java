package com.touristskaya.homeoseqandroidclient.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.touristskaya.homeoseqandroidclient.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TAG";

    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView.setText("HELLO_WORLD_!");

        Log.d(TAG, "STARTED");


        String TEST_FIELD = "TEST_FIELD";

        DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        firebaseDatabase.child(TEST_FIELD).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot == null || dataSnapshot.getValue() == null) {
                    Log.d(TAG, "DATA_IS_EMPTY");
                    return;
                }

                Log.d(TAG, "MESSAGE_NOT_NULL");
                Log.d(TAG, "MESSAGE: " + dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        FirebaseDatabase.getInstance().goOffline();
    }
}
