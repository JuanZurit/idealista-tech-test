package com.juanzurita.presentation.ads

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanzurita.core.util.Constants
import com.juanzurita.domain.ad_list.uses_cases.FetchAdsItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdListViewModel @Inject constructor(
    val fetchAdsItemsUseCase: FetchAdsItemsUseCase,
    savedStateHandle: SavedStateHandle
):ViewModel() {

    private val isFavorite by lazy { savedStateHandle[Constants.Arguments.IS_FAVORITE]?:false }

    fun test() {
        viewModelScope.launch {
            fetchAdsItemsUseCase().fold(
                ifLeft = {
                    // Handle error
                    Log.d("TAG", "test: $it")
                },
                ifRight = {
                    // Handle success
                    Log.d("TAG", "test: $it")
                }
            )
        }

    }


}