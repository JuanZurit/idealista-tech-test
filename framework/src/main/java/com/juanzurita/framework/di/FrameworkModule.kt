package com.juanzurita.framework.di

import android.content.Context
import com.juanzurita.core.framework.datastore.SettingsPreferencesDatasource
import com.juanzurita.core.framework.managers.DefaultDispatcherProvider
import com.juanzurita.core.framework.managers.DispatcherProvider
import com.juanzurita.framework.BuildConfig
import com.juanzurita.framework.remote.ApiConstants
import com.juanzurita.framework.remote.ApiEndpoints
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FrameworkModule {
    @Singleton
    @Provides
    fun provideSettingsPreferencesDatasource(
        @ApplicationContext context: Context,
    ): SettingsPreferencesDatasource {
        return SettingsPreferencesDatasource(context)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = HttpLoggingInterceptor().run {
        if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        val moshi = Moshi.Builder()
        moshi.addLast(KotlinJsonAdapterFactory())
        return moshi.build()
    }

    @Singleton
    @Provides
    fun provideApiInstance(okHttpClient: OkHttpClient, moshi: Moshi): ApiEndpoints {
        val baseUrl =ApiConstants.BaseUrl.DEBUG
            //if (BuildConfig.DEBUG) ApiConstants.BaseUrl.DEBUG else ApiConstants.BaseUrl.PRODUCTION
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .build()
            .create(ApiEndpoints::class.java)
    }

    @Singleton
    @Provides
    fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()

    /*
    @Provides
    @Singleton
    fun provideFavoritesDB(@ApplicationContext app: Context) = Room.databaseBuilder(
        app,
        FavoriteDatabase::class.java,
        "favorites_db"
    ).build()


    @Provides
    @Singleton
    fun provideFavoriteDao(db: FavoriteDatabase): FavoriteDao {
        return db.favoriteDao()
    }
*/
}