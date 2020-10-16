package com.shop.catganisation.repository

import android.app.Application
import com.shop.catganisation.database.BreedDao
import com.shop.catganisation.database.CatganisationDatabase
import com.shop.catganisation.model.Breed
import com.shop.catganisation.model.BreedAndImage
import com.shop.catganisation.model.Image
import com.shop.catganisation.network.CatsApi
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
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

    fun getBreedsSync(): Call<List<Breed>> {
        return catsApi.getBreedsSync()
    }

    fun getBreedImageSync(breedId: String): Call<List<Image>> {
        return catsApi.getBreedImageSync(breedId)
    }

    fun isBreedTableEmpty(): Maybe<Int> {
        return breedDao.getBreedCount()
    }

}