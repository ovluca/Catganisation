package com.shop.catganisation.di.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by Ovidiu Florin Luca on 14/10/2020.
 */
/**
 * If there is no worker found, return null to use the default behaviour of [WorkManager]
 * (create worker using refection)
 *
 * @see WorkerFactory.createWorkerWithDefaultFallback
 */
class SampleWorkerFactory @Inject constructor(
    private val workerFactories: Map<Class<out ListenableWorker>, @JvmSuppressWildcards Provider<ChildWorkerFactory>>
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        val foundEntry = workerFactories.entries
            .find { Class.forName(workerClassName).isAssignableFrom(it.key) }
            ?: return null
        return foundEntry.value.get().create(appContext, workerParameters)
    }
}