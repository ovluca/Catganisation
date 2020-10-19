package com.shop.catganisation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.shop.catganisation.di.component.DaggerViewModelComponent
import com.shop.catganisation.di.component.ViewModelComponent
import com.shop.catganisation.di.module.DatabaseModule
import com.shop.catganisation.di.module.NetworkModule

/**
 * Created by Ovidiu Florin Luca on 07/10/2020.
 */

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    private val injector: ViewModelComponent = DaggerViewModelComponent
        .builder()
        .networkModule(NetworkModule)
        .databaseModule(DatabaseModule(application))
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is BreedsViewModel -> injector.inject(this)
            is BreedDetailsViewModel -> injector.inject(this)
            is SplashViewModel -> injector.inject(this)
            is LoginViewModel -> injector.inject(this)
        }
    }
}