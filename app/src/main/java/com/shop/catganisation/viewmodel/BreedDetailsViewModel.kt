package com.shop.catganisation.viewmodel

import android.app.Application
import com.shop.catganisation.model.BreedAndImage
import javax.inject.Inject

/**
 * Created by Ovidiu Florin Luca on 09/10/2020.
 */
class BreedDetailsViewModel @Inject constructor(application: Application) : BaseViewModel(
    application
) {

    lateinit var breed: BreedAndImage

}