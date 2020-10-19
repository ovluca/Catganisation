package com.shop.catganisation.viewmodel

import android.app.Application
import com.shop.catganisation.repository.CatsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Ovidiu Florin Luca on 09/10/2020.
 */
class LoginViewModel @Inject constructor(application: Application) : BaseViewModel(
    application
) {
    @Inject
    lateinit var repository: CatsRepository
    private lateinit var subscription: Disposable


    fun login(username: String, password: String, response: (isError: Boolean) -> Unit) {
         subscription = repository.login(username, password)
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(
                 { response(false) },
                 { response(true) }
             )

//        response(false)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}