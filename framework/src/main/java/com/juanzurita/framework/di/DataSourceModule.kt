package com.juanzurita.framework.di

import com.juanzurita.data.ads.datasources.AdsLocalDataSource
import com.juanzurita.data.ads.datasources.AdsRemoteDataSource
import com.juanzurita.framework.local.AdsLocalDataSourceImpl
import com.juanzurita.framework.remote.ads.AdsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(adsRemoteDataSource: AdsRemoteDataSourceImpl): AdsRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindLocalDataSource(adsLocalDataSource: AdsLocalDataSourceImpl): AdsLocalDataSource

}