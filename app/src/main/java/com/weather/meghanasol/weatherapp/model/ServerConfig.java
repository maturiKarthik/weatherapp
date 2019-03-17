package com.weather.meghanasol.weatherapp.model;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerConfig {
    private final String TAG = this.getClass().getSimpleName();
    private String baseUrl;
    private GithubServices services = null;

    public ServerConfig(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    private GithubServices initRetroFit(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(GithubServices.class);
    }

    public GithubServices getGithubInstance() {
        if (services == null) {
            Log.w(TAG, "Creating Github Object");
            return initRetroFit(baseUrl);
        }
        Log.w(TAG, " Github Object Exist");
        return services;
    }
}
