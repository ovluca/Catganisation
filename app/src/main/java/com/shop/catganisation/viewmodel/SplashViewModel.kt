package com.shop.catganisation.viewmodel

import android.app.Application
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.shop.catganisation.network.SyncBreedAndImageWorker
import com.shop.catganisation.repository.CatsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Created by Ovidiu Florin Luca on 09/10/2020.
 */
class SplashViewModel @Inject constructor(application: Application) : BaseViewModel(
    application
) {
    @Inject
    lateinit var repository: CatsRepository

    val syncBreedWithImage: WorkRequest =
        OneTimeWorkRequestBuilder<SyncBreedAndImageWorker>()
            .build()

    fun startSync(hasData: () -> Unit) {

        repository.isBreedTableEmpty()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableMaybeObserver<Int?>() {
                override fun onSuccess(count: Int) {
                    if (count > 0) {
                        hasData()
                    } else {
                        WorkManager
                            .getInstance(getApplication())
                            .enqueue(syncBreedWithImage)
                    }
                }

                override fun onError(e: Throwable) {
                }

                override fun onComplete() {
                }
            })

    }


}