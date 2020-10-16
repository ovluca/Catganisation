package com.shop.catganisation.network

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.shop.catganisation.di.worker.ChildWorkerFactory
import com.shop.catganisation.model.Breed
import com.shop.catganisation.model.BreedAndImage
import com.shop.catganisation.model.Image
import com.shop.catganisation.repository.CatsRepository
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject


/**
 * IMPORTANT NOTE!
 *
 * The [Context] need to be named with [appContext] and [WorkerParameters] with [params]
 * as long as these name are identical with [ChildWorkerFactory.create]'s method parameters
 *
 */
class SyncBreedAndImageWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted private val params: WorkerParameters,
    private val catsRepository: CatsRepository
) : Worker(appContext, params) {
    override fun doWork(): Result {
        try {
            val breeds: List<Breed> = catsRepository.getBreedsSync().execute().body()!!

            breeds.forEach { breed ->
                val images: List<Image> =
                    catsRepository.getBreedImageSync(breedId = breed.id).execute().body()!!


                val breedAndImage = BreedAndImage(
                    breed.id,
                    images[0].url,
                    breed.name,
                    breed.description,
                    breed.countryCode,
                    breed.origin,
                    breed.temperament,
                    breed.wikipediaUrl,
                )
                catsRepository.breedDao.insertBreed(breed = breedAndImage)
            }

            return Result.success()
        } catch (exception: Exception) {
            return Result.failure()
        }
    }

    @AssistedInject.Factory
    interface Factory : ChildWorkerFactory
}



