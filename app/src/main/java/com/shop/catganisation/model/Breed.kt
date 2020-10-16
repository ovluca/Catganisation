package com.shop.catganisation.model

import com.squareup.moshi.Json

/**
 * Created by Ovidiu Florin Luca on 07/10/2020.
 */
data class Breed(
    val id: String,
    val name: String,
    @field:Json(name = "country_code") val countryCode: String,
    val description: String,
    val temperament: String,
    val origin: String,
    @field:Json(name = "wikipedia_url") val wikipediaUrl: String
)