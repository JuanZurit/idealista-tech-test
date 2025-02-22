package com.juanzurita.presentation.ads_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanzurita.core.util.Constants.URL.Companion.NO_PHOTO
import com.juanzurita.domain.ad_list.models.AdDetail
import com.juanzurita.domain.ad_list.uses_cases.FetchAdsDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdsDetailViewModel @Inject constructor(
    val fetchAdsDetailUseCase: FetchAdsDetailUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        fetchAdsDetail()
    }

    private fun fetchAdsDetail() {
        viewModelScope.launch {
            fetchAdsDetailUseCase().fold(
                ifLeft = {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            noData = true,
                            adDetail = null,
                            carouselImages = arrayListOf(NO_PHOTO)
                        )
                    }
                },
                ifRight = { adItem ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            noData = false,
                            adDetail = adItem,
                            carouselImages = adItem.multimedia?.images?.filter { imageWrapper -> !imageWrapper.url.isNullOrEmpty() }
                                ?.map { imageWrapper -> imageWrapper.url!! }.orEmpty()
                        )
                    }
                }
            )
        }
    }


    data class UiState(
        val isLoading: Boolean = true,
        val noData: Boolean = false,
        val adDetail: AdDetail? = null,
        val carouselImages: List<String> = emptyList()
    )

}