package com.juanzurita.framework.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AdsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAds(ads: List<AdItemEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(competition: AdItemEntity)

    @Update
    suspend fun update(competition: AdItemEntity)

    @Delete
    suspend fun delete(competition: AdItemEntity)

    @Query("SELECT * FROM ad_item")
    fun selectAlertCompetition(): Flow<List<AdItemEntity>>

    @Query("SELECT * FROM ad_item WHERE propertyCode = :adId")
    suspend fun getAdById(adId: String): AdItemEntity?


    @Query("UPDATE ad_item SET isFavorite = :isFavorite WHERE propertyCode = :adId")
    suspend fun updateFavoriteStatus(adId: String, isFavorite: Int):Int

    @Transaction
    suspend fun insertAdsWithFavorite(ads: List<AdItemEntity>) {
        // Procesamos los ads antes de insertarlos
        val adsWithFavorites = ads.map { ad ->
            val currentAd = getAdById(ad.propertyCode)
            if (currentAd != null) {
                // Si ya existe, conservamos el valor de isFavorite
                ad.copy(isFavorite = currentAd.isFavorite)
            } else {
                // Si no existe, dejamos el isFavorite como está en los datos de la API
                ad
            }
        }
        // Insertamos los ads con el campo isFavorite intacto
        insertAds(adsWithFavorites)
    }
    @Transaction
    @Query("SELECT * FROM ad_item WHERE isFavorite = 1")
    fun selectFavoriteAds(): Flow<List<AdItemEntity>>

}