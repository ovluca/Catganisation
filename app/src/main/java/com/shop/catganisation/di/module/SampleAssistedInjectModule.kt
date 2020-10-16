package com.shop.catganisation.di.module

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module

/**
 * Created by Ovidiu Florin Luca on 14/10/2020.
 */
@Module(includes = [AssistedInject_SampleAssistedInjectModule::class])
@AssistedModule
interface SampleAssistedInjectModule