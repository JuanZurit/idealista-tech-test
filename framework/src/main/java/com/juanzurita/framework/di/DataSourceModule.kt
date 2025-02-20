package com.juanzurita.framework.di

import com.juanzurita.data.ads.datasources.AdsRemoteDataSource
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

}