package com.mojitoproject.drinkviewer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MyApi {
    @FormUrlEncoded
    @POST("insert2.php")
    Call<ModelClass>insertData(
            @Field("Name")String Name,
            @Field("Description")String Description,
            @Field("Ingredients")String Ingredients,
            @Field("Vibe")String Vibe,

            @Field("tableName")String TableName
    );

    @FormUrlEncoded
    @POST("select2.php")
    Call<ArrayList<ModelClass>>fetchData(
            @Field("rest")String Rest
    );


}



















