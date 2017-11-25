package com.example.krauser.restauranteandroid.util;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by wschenkel on 11/25/17.
 */

public class Firebase extends FirebaseInstanceIdService{

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }

}
