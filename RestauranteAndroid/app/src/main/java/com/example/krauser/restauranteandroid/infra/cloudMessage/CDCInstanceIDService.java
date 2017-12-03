package com.example.krauser.restauranteandroid.infra.cloudMessage;

import android.util.Log;

import com.example.krauser.restauranteandroid.infra.repositorio.TokenRepository;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by willi on 26/11/2017.
 */

public class CDCInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        TokenRepository.currentToken = token;
        if (token != null) {
            Log.i("token firebase", token);
            System.out.println(token);
        }
    }
}

