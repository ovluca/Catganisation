package com.shop.catganisation.di.module

import com.shop.catganisation.di.worker.ChildWorkerFactory
import com.shop.catganisation.di.worker.WorkerKey
import com.shop.catganisation.network.SyncBreedAndImageWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface WorkerBindingModule {
    @Binds
    @IntoMap
    @WorkerKey(SyncBreedAndImageWorker::class)
    fun bindSyncBreedAndImageWorker(factory: SyncBreedAndImageWorker.Factory): ChildWorkerFactory
}


