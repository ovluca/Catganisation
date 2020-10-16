package com.shop.catganisation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.shop.catganisation.model.BreedAndImage
import com.shop.catganisation.repository.CatsRepository
import com.shop.catganisation.ui.main.adapter.BreedsAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BreedsViewModel @Inject constructor(application: Application) : BaseViewModel(application) {
    @Inject
    lateinit var repository: CatsRepository

    var breeds: ArrayList<BreedAndImage> = ArrayList()
    var breedsAdapter = BreedsAdapter(breeds)

    private lateinit var subscription: Disposable

    init {
        loadPosts()
    }

    private fun loadPosts() {
        subscription = repository.getBreeds()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> onRetrieveBreedsListSuccess(result) },
                { onRetrieveBreedsListError() }
            )
    }

    private fun onRetrieveBreedsListError() {
        Log.d("tag", "onRetrieveBreedsListError")
    }

    private fun onRetrieveBreedsListSuccess(breeds: List<BreedAndImage>) {
        this.breeds.addAll(breeds)
        breedsAdapter.notifyDataSetChanged()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}