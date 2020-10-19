package com.shop.catganisation.model

import com.google.gson.annotations.SerializedName


/**
 * Created by Ovidiu Florin Luca on 07/10/2020.
 */
data class Breed(
    val id: String,
    val name: String,
    @SerializedName( "country_code") val countryCode: String,
    val description: String,
    val temperament: String,
    val origin: String,
    @SerializedName("wikipedia_url") val wikipediaUrl: String
)