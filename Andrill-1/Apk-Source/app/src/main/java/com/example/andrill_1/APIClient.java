package com.example.andrill_1;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.andrill_1.Utils.BASE_URL;

class APIClient {

    private Retrofit retrofit = null;
    private Context app;

    public APIClient(Context applicationContext) {
        app = applicationContext;
    }

    Retrofit getClient() {

        Gson gson = new GsonBuilder()

                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")

                .create();


        Retrofit retrofit = new Retrofit.Builder()

                .baseUrl(Utils.getBaseUrl(app))

                .addConverterFactory(GsonConverterFactory.create(gson))

                .build();


        return retrofit;
    }

}