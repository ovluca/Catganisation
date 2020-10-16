package com.shop.catganisation.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shop.catganisation.model.BreedAndImage

/**
 * Created by Ovidiu Florin Luca on 14/10/2020.
 */

@Database(entities = [BreedAndImage::class], version = 1)
abstract class CatganisationDatabase : RoomDatabase() {
    abstract fun dataDao(): BreedDao

    companion object {

        @Volatile // All threads have immediate access to this property
        private var instance: CatganisationDatabase? = null

        private val LOCK = Any() // Makes sure no threads making the same thing at the same time

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CatganisationDatabase::class.java,
                DATABASE_NAME
            ).fallbackToDestructiveMigration().build()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private const val DATABASE_NAME = "cats.db"
    }
}