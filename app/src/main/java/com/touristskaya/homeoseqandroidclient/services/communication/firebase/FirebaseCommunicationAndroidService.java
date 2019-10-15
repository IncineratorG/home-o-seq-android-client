package com.touristskaya.homeoseqandroidclient.services.communication.firebase;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.touristskaya.homeoseqandroidclient.R;
import com.touristskaya.homeoseqandroidclient.activities.MainActivity;

/**
 * TODO: Add a class header comment
 */
public class FirebaseCommunicationAndroidService extends Service {
    private static final String TAG = "tag";

    private static final String CLASS_NAME = "FirebaseCommunicationAndroidService";

    public static FirebaseCommunicationAndroidService mInstance = null;
    private DatabaseReference mFirebaseDatabase;
    private String TEST_FIELD = "TEST_FIELD";
    private ValueEventListener mValueEventListener;


    public FirebaseCommunicationAndroidService() {
        mValueEventListener = new ValueEventListener() {
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
        };
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        FirebaseDatabase.getInstance().goOnline();
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
        mFirebaseDatabase.child(TEST_FIELD).addValueEventListener(mValueEventListener);


        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            Intent notificationIntent = new Intent(FirebaseCommunicationAndroidService.this, MainActivity.class);
            notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            PendingIntent pendingIntent = PendingIntent.getActivity(FirebaseCommunicationAndroidService.this, 0,
                    notificationIntent, 0);

            Notification notification = new NotificationCompat.Builder(FirebaseCommunicationAndroidService.this)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("Surveillance")
                    .setAutoCancel(true)
//                .setPriority(NotificationCompat.PRIORITY_MIN)
                    .setContentText("Surveillance service is active")
                    .setContentIntent(pendingIntent).build();

            startForeground(1337, notification);
        } else {
            startMyOwnForeground();
        }


        String METHOD_NAME = ".onCreate()";
        Log.d(TAG, CLASS_NAME + METHOD_NAME);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String METHOD_NAME = ".onStartCommand()";
        Log.d(TAG, CLASS_NAME + METHOD_NAME);

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        String METHOD_NAME = ".onDestroy()";
        Log.d(TAG, CLASS_NAME + METHOD_NAME);

        mFirebaseDatabase.child(TEST_FIELD).removeEventListener(mValueEventListener);
        FirebaseDatabase.getInstance().goOffline();

        mInstance = null;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private void startMyOwnForeground(){
        String NOTIFICATION_CHANNEL_ID = "com.example.simpleapp";
        String channelName = "My Background Service";
        NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(chan);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        Notification notification = notificationBuilder.setOngoing(true)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("App is running in background")
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();
        startForeground(2, notification);
    }
}
