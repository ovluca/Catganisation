package com.shop.catganisation.di.module

import android.app.Application
import com.shop.catganisation.database.BreedDao
import com.shop.catganisation.database.CatganisationDatabase
import dagger.Module
import dagger.Provides
import dagger.Reusable

/**
 * Created by Ovidiu Florin Luca on 15/10/2020.
 */
@Module
class DatabaseModule constructor(var application: Application) {

    private lateinit var catganisationDatabase: CatganisationDatabase

    @Provides
    fun providesApplication(): Application {
        return application;
    }

    @Provides
    @Reusable
    fun providesDatabase(): CatganisationDatabase {
        catganisationDatabase = CatganisationDatabase.invoke(application)
        return catganisationDatabase
    }

    @Provides
    @Reusable
    fun provideBreedDao(): BreedDao {
        return catganisationDatabase.dataDao()
    }
}