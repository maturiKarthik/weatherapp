package com.weather.meghanasol.weatherapp.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubServices {
    @GET("/users/{userName}/{what}")
    Call<List<Data>> listRepos(@Path("userName") String name,@Path("what") String what);
}
