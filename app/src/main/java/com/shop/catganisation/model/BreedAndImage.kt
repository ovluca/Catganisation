package com.shop.catganisation.model

import android.os.Parcelable
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by Ovidiu Florin Luca on 14/10/2020.
 */
@Parcelize
@Entity(tableName = "breeds")
data class BreedAndImage(
    @PrimaryKey
    val id: String = "",
    val image: String = "",
    val name: String = "",
    val description: String = "",
    @ColumnInfo(name = "country_code")
    val countryCode: String = "",
    val origin: String = "",
    val temperament: String = "",
    @ColumnInfo(name = "wikipedia_url")
    val wikipediaUrl: String? = ""
) : Parcelable