package com.example.krauser.restauranteandroid.service;

import android.content.Context;
import android.database.SQLException;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.krauser.restauranteandroid.infra.repositorio.TokenRepository;
import com.example.krauser.restauranteandroid.listener.OnTokenListener;
import com.example.krauser.restauranteandroid.util.Constants;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TokenService {

    private Context context;
    private TokenRepository repository;
    private String currentToken;
    private OnTokenListener listener;

    public TokenService(Context context){
        this.context = context;
        repository = new TokenRepository(context);
        currentToken = TokenRepository.currentToken;
        if(currentToken == null || currentToken.isEmpty())
            currentToken = FirebaseInstanceId.getInstance().getToken();
    }

    public void update() throws SQLException {
        String storedToken = repository.getStoredToken();
        if(currentToken == null || currentToken.isEmpty()){
            afterResponse("Nenhum token para ser atualizado.");
            return;
        }

        Map<String, String> params = new HashMap<>();
        params.put("oldToken", storedToken);
        params.put("newToken", currentToken);
        RequestQueue queue = Volley.newRequestQueue(context);
        String urlRequest = Constants.URL_API + "/token";
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                urlRequest,
                new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            afterResponse(response.getString("data"));
                        }catch(JSONException e){
                            afterResponse(e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        afterResponse(error.getMessage());
                    }
                }
        );
        queue.add(request);
    }

    public void setListener(OnTokenListener listener){
        this.listener = listener;
    }

    public void afterResponse(String msg) {
        if(listener != null)
            listener.tokenUpdated(msg);
        repository.update(currentToken);
    }
}