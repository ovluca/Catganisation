package com.shop.catganisation.model

/**
 * Created by Ovidiu Florin Luca on 10/10/2020.
 */
data class Image(
    val breeds: List<Breed>,
    val id: String,
    val url: String
) {
}