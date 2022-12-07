package com.example.android_lab4.API;

import com.example.android_lab4.Model.SpaceXShip;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IRetrofit {
    @GET("/v3/ships")
    Call<List<SpaceXShip>> getAllSpaceXShips();
}
