package com.example.infedisassigment.presenter.RetroClient;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.infedisassigment.presenter.BaseUrlClient.ApiClient;
import com.example.infedisassigment.presenter.Utilities.URLs;
import com.example.infedisassigment.presenter.interfaces.SubUrlInterface;


public class RetrofitApiUtils {
    public ProgressDialog pDialog;
    Context activity;

    public RetrofitApiUtils(Context activity) {
        this.activity = activity;
        this.pDialog = new ProgressDialog(activity);
    }

    public static SubUrlInterface getAPIService() {
        return ApiClient.getClient(URLs.BASE_URL).create(SubUrlInterface.class);

    }
}