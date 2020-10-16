package com.shop.catganisation

import android.app.Application
import androidx.work.Configuration
import com.shop.catganisation.di.module.DatabaseModule
import com.shop.catganisation.di.component.AppComponent
import com.shop.catganisation.di.component.DaggerAppComponent
import com.shop.catganisation.di.worker.SampleWorkerFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : Application(), Configuration.Provider, HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        appComponent = DaggerAppComponent.builder().databaseModule(DatabaseModule(this)).build()

        appComponent.inject(this)

        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        val factory: SampleWorkerFactory = appComponent.factory()
        return Configuration.Builder().setWorkerFactory(factory).build()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}