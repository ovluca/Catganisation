package com.shop.catganisation.repository

import android.app.Application
import com.shop.catganisation.database.BreedDao
import com.shop.catganisation.database.CatganisationDatabase
import com.shop.catganisation.model.Breed
import com.shop.catganisation.model.BreedAndImage
import com.shop.catganisation.model.Image
import com.shop.catganisation.model.Login
import com.shop.catganisation.network.CatsApi
import io.reactivex.Maybe
import io.reactivex.Observable
import retrofit2.Call
import javax.inject.Inject

/**
 * Created by Ovidiu Florin Luca on 08/10/2020.
 */
class CatsRepository @Inject constructor() {

    @Inject
    lateinit var catsApi: CatsApi

    @Inject
    lateinit var application: Application

    @Inject
    lateinit var database: CatganisationDatabase

    @Inject
    lateinit var breedDao: BreedDao


    fun getBreeds(): Observable<List<BreedAndImage>> {
        return breedDao.getBreeds()
    }

    fun login(username: String, password: String): Observable<Login> {
        return catsApi.login(username, password)
    }

    fun getBreedsSync(): Call<List<Breed>> {
        return catsApi.getBreedsSync("e51009b2-ce0a-471b-bf44-1f97ab68be0c")
    }

    fun getBreedImageSync(breedId: String): Call<List<Image>> {
        return catsApi.getBreedImageSync(breedId)
    }

    fun isBreedTableEmpty(): Maybe<Int> {
        return breedDao.getBreedCount()
    }

}