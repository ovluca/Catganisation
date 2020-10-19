package com.shop.catganisation.network

import com.shop.catganisation.model.Breed
import com.shop.catganisation.model.Image
import com.shop.catganisation.model.Login
import com.shop.catganisation.utils.Constants
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Ovidiu Florin Luca on 07/10/2020.
 */
interface CatsApi {
    @GET("/v1/breeds")
    fun getBreeds(): Observable<List<Breed>>

    @FormUrlEncoded
    @POST("/v1/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Observable<Login>

    @GET("/v1/breeds")
    fun getBreedsSync(@Header("x-api-key") apiKey: String): Call<List<Breed>>

    @GET("/v1/images/search")
    fun getBreedImage(@Query(Constants.KEY_BREED_ID) breedId: String): Observable<List<Image>>

    @GET("/v1/images/search")
    fun getBreedImageSync(@Query(Constants.KEY_BREED_ID) breedId: String): Call<List<Image>>
}