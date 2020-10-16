package com.shop.catganisation.di.component

import com.shop.catganisation.App
import com.shop.catganisation.di.module.DatabaseModule
import com.shop.catganisation.di.module.NetworkModule
import com.shop.catganisation.di.module.WorkerBindingModule
import com.shop.catganisation.di.module.SampleAssistedInjectModule
import com.shop.catganisation.di.worker.SampleWorkerFactory
import com.shop.catganisation.ui.main.activity.MainActivity
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


/**
 * Created by Ovidiu Florin Luca on 14/10/2020.
 */

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        SampleAssistedInjectModule::class,
        WorkerBindingModule::class,
        NetworkModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    fun factory(): SampleWorkerFactory

    fun inject(activity: MainActivity)

}