package com.weather.meghanasol.weatherapp.model;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubCalls implements Callback<List<Data>> {

    private final String TAG = this.getClass().getSimpleName();
    private GithubServices service;
    private GithubCallsCallback githubCallsCallback;
    private String name;

    public GithubCalls(GithubServices service, GithubCallsCallback githubCallsCallback,String name) {
        Log.w(TAG, "Calls");
        this.service = service;
        this.githubCallsCallback = githubCallsCallback;
        this.name = name;
        if (service != null) {
            Call<List<Data>> data = service.listRepos(name,"repos");
            if (data != null) {
                data.enqueue(this);
            } else {
                Log.w(TAG, "Object No creatd");
            }

        }

    }

    @Override
    public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
        githubCallsCallback.urlPinged(call.request().toString());
        Log.w(TAG, "Requested Url ::" + call.request());
        Log.w(TAG, "Response Successfull ::" + response.isSuccessful());
        Log.w(TAG, "Response Error Body ::" + response.errorBody());
        List<Data> data1 = response.body();
        for (Data da : data1) {
            githubCallsCallback.datarecieved(da);
        }
    }

    @Override
    public void onFailure(Call<List<Data>> call, Throwable t) {
        Log.w(TAG, "No Response" + call.request().body());
        githubCallsCallback.onError(t.getMessage().toString());
        t.printStackTrace();
    }
}
