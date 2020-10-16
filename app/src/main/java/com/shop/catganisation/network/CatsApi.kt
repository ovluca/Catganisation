package com.shop.catganisation.network

import com.shop.catganisation.model.Breed
import com.shop.catganisation.model.Image
import com.shop.catganisation.utils.Constants
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ovidiu Florin Luca on 07/10/2020.
 */
interface CatsApi  {
    @GET("/v1/breeds")
    fun getBreeds(): Observable<List<Breed>>

    @GET("/v1/breeds")
    fun getBreedsSync(): Call<List<Breed>>

    @GET("/v1/images/search")
    fun getBreedImage(@Query(Constants.KEY_BREED_ID) breedId: String): Observable<List<Image>>

    @GET("/v1/images/search")
    fun getBreedImageSync(@Query(Constants.KEY_BREED_ID) breedId: String): Call<List<Image>>
}