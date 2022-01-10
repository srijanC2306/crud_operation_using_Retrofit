package com.srijan.retrofit2_crud.remote;

import com.srijan.retrofit2_crud.DataModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitApi {

    @POST("users")
    Call<DataModel> createPost(@Body DataModel dataModel) ;
}
