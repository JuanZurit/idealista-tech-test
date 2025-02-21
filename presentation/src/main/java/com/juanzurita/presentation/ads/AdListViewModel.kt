package com.juanzurita.presentation.ads

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanzurita.core.adapter.DelegateAdapterItem
import com.juanzurita.core.util.Constants
import com.juanzurita.domain.ad_list.models.AdItem
import com.juanzurita.domain.ad_list.uses_cases.FetchAdsItemsUseCase
import com.juanzurita.domain.ad_list.uses_cases.UpdateFavoriteAdsItemsUseCase
import com.juanzurita.presentation.ads.adapter.models.AdItemPLO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdListViewModel @Inject constructor(
    val fetchAdsItemsUseCase: FetchAdsItemsUseCase,
    val updateFavoriteAdsItemsUseCase: UpdateFavoriteAdsItemsUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val isFavorite by lazy { savedStateHandle[Constants.Arguments.IS_FAVORITE] ?: false }

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchAdItemList()
    }


    private fun fetchAdItemList() {
        viewModelScope.launch {
            fetchAdsItemsUseCase(isFavorite).collect { adapterList ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        adapterList = adapterList.toMutableList().map { adapterItem -> adapterItem.toPLO() },
                        noData = adapterList.isEmpty()
                    )
                }
            }

        }
    }

    fun sendEvent(event: AdListEvents) {
        when (event) {
            is AdListEvents.UpdateFavoriteStatus -> {
                viewModelScope.launch {
                    if (event.propertyCode.isNullOrEmpty()) return@launch
                    updateFavoriteAdsItemsUseCase(event.propertyCode, event.isFavorite)
                }
            }
        }
    }

    sealed interface AdListEvents {
        data class UpdateFavoriteStatus(val propertyCode: String?, val isFavorite: Boolean) :
            AdListEvents
    }


    data class UiState(
        val isLoading: Boolean = true,
        val noData: Boolean = false,
        val adapterList: List<DelegateAdapterItem>? = null,
    )


    private fun AdItem.toPLO() = AdItemPLO(
        propertyCode = propertyCode,
        thumbnail = thumbnail,
        floor = floor,
        price = price,
        priceInfo = priceInfo,
        propertyType = propertyType,
        operation = operation,
        size = size,
        exterior = exterior,
        rooms = rooms,
        bathrooms = bathrooms,
        address = address,
        province = province,
        municipality = municipality,
        district = district,
        country = country,
        neighborhood = neighborhood,
        latitude = latitude,
        longitude = longitude,
        description = description,
        principalImage = multimedia?.images?.getOrNull(0)?.url,
        secondaryImage1 = multimedia?.images?.getOrNull(1)?.url,
        secondaryImage2 = multimedia?.images?.getOrNull(2)?.url,
        secondaryImage3 = multimedia?.images?.getOrNull(3)?.url,
        features = features,
        isFavorite=isFavorite
    )


}