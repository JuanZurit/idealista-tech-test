package com.juanzurita.domain.ad_list.uses_cases

import com.juanzurita.domain.ad_list.repository.AdsRepository
import javax.inject.Inject

class FetchAdsItemsUseCase @Inject constructor(
    private val adsRepository: AdsRepository
) {
    suspend operator fun invoke(isFavorite:Boolean) = adsRepository.fetchAdsList(isFavorite)
}