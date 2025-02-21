package com.juanzurita.domain.ad_list.uses_cases

import com.juanzurita.domain.ad_list.repository.AdsRepository
import javax.inject.Inject

class UpdateFavoriteAdsItemsUseCase @Inject constructor(
    private val adsRepository: AdsRepository
) {
    suspend operator fun invoke(propertyCode:String,isFavorite:Boolean) = adsRepository.updateFavoriteAdsList(propertyCode,isFavorite)
}