package com.juanzurita.framework.di

import com.juanzurita.data.ads.repository.AdsRepositoryImpl
import com.juanzurita.domain.ad_list.repository.AdsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAdsRepository(adsRepositoryImpl: AdsRepositoryImpl): AdsRepository

}