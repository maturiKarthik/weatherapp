package com.weather.meghanasol.weatherapp.model;

public interface GithubCallsCallback {

    void datarecieved(Data data);
    void onError(String message);
    void  urlPinged(String url);
}
