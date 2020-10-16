package com.shop.catganisation.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shop.catganisation.model.BreedAndImage
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by Ovidiu Florin Luca on 14/10/2020.
 */
@Dao
interface BreedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBreed(breed: BreedAndImage)

    @Query("SELECT * from breeds")
    fun getBreeds(): Observable<List<BreedAndImage>>

    @Query("SELECT count(*) FROM breeds")
    fun getBreedCount(): Maybe<Int>
}