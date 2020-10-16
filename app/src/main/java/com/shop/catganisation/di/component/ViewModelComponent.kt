package com.shop.catganisation.di.component

import com.shop.catganisation.di.module.DatabaseModule
import com.shop.catganisation.di.module.NetworkModule
import com.shop.catganisation.viewmodel.BreedDetailsViewModel
import com.shop.catganisation.viewmodel.BreedsViewModel
import com.shop.catganisation.viewmodel.SplashViewModel
import dagger.Component
import javax.inject.Singleton


/**
 * Created by Ovidiu Florin Luca on 08/10/2020.
 */
@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class])
interface ViewModelComponent {

    fun inject(breedsViewModel: BreedsViewModel)
    fun inject(breedDetailsViewModel: BreedDetailsViewModel)
    fun inject(splashViewModel: SplashViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelComponent

        fun networkModule(networkModule: NetworkModule): Builder
        fun databaseModule(databaseModule: DatabaseModule): Builder
    }

}