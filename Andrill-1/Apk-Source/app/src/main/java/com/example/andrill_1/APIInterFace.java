package com.example.andrill_1;

import com.example.andrill_1.models.BackModel;
import com.example.andrill_1.models.LoginResponse;
import com.example.andrill_1.models.UserModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by tyler on 12/27/18.
 */

public interface APIInterFace {
    @Headers("Content-Type:application/json")
    @POST("authenticate")
    Call<LoginResponse> login(@Body UserModel userModel);

    @Headers("Content-Type:application/json")
    @POST("back-end")
    Call<BackModel> backRequest(@Body BackModel userModel);


    @GET("/")
    Call<ResponseBody> groupList();


//
//    @POST("users/new")
//
//    Call<User> createUser(@Body User user);




}
