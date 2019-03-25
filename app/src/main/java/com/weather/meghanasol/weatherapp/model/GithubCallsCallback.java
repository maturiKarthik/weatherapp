package com.weather.meghanasol.weatherapp.model;

import java.util.List;

public interface GithubCallsCallback {

    void datarecieved(List<Data> data);

    void onError(String message);

    void urlPinged(String url);
}
