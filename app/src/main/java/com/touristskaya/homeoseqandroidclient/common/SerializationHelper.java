package com.touristskaya.homeoseqandroidclient.common;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * TODO: Add a class header comment
 */
public class SerializationHelper {
    private static final String CLASS_NAME = "SerializationHelper";
    private static final String TAG = "TAG";

    public static String objectToString(Object object) {
        String METHOD_NAME = ".objectToString()";
        Log.i(TAG, CLASS_NAME + METHOD_NAME);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (oos == null)
            return null;

        try {
            oos.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return android.util.Base64.encodeToString(baos.toByteArray(), android.util.Base64.NO_WRAP);
    }

    public static Object objectFromString(String dataString) {
        String METHOD_NAME = ".objectFromString()";

        Object object = null;
        byte[] dataBytes;
        try {
            dataBytes = android.util.Base64.decode(dataString, android.util.Base64.NO_WRAP);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }

        try {
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(dataBytes));
            object = ois.readObject();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (object == null) {
            Log.i(TAG, CLASS_NAME + METHOD_NAME + "->MESSAGE_IS_NULL");
            return null;
        }

        return object;
    }
}
