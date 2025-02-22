package com.juanzurita.presentation.ads

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.juanzurita.core.adapter.CompositeAdapter
import com.juanzurita.core.adapter.DelegateAdapterItem
import com.juanzurita.core.util.extensions.diff
import com.juanzurita.core.util.extensions.gone
import com.juanzurita.core.util.extensions.visible
import com.juanzurita.presentation.R
import com.juanzurita.presentation.ads.adapter.AdItemAdapter
import com.juanzurita.presentation.ads_detail.AdsDetailActivity
import com.juanzurita.presentation.databinding.FragmentAdListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdListFragment : Fragment() {

    private val viewModel by viewModels<AdListViewModel>()

    private var _binding: FragmentAdListBinding? = null
    private val binding: FragmentAdListBinding get() = _binding!!

    private lateinit var recyclerAdapter: CompositeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAdListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        collectUiState()
    }

    private fun setUpRecyclerView() {
        binding.rvAds.apply {
            recyclerAdapter = CompositeAdapter.Builder()
                .add(AdItemAdapter({
                    context.startActivity(Intent(context,AdsDetailActivity::class.java))
                }) { propertyCode, isFavorite ->
                    viewModel.sendEvent(
                        AdListViewModel.AdListEvents.UpdateFavoriteStatus(
                            propertyCode,
                            isFavorite
                        )
                    )
                })
                .build()
            adapter = recyclerAdapter
        }
    }

    private fun collectUiState() {
        with(viewModel.uiState) {
            diff(viewLifecycleOwner, { uiState -> uiState.adapterList }) { adapterList ->
                handleData(adapterList)
            }

            diff(viewLifecycleOwner, { uiState -> uiState.noData }) { noData ->
                handleNoData(noData)
            }

            diff(viewLifecycleOwner, { uiState -> uiState.isLoading }) { isLoading ->
                handleIsLoading(isLoading)
            }
        }
    }

    private fun handleData(adapterList: List<DelegateAdapterItem>?) {
        recyclerAdapter.submitList(adapterList)
    }

    private fun handleNoData(noData: Boolean) {
        binding.noDataMessage.visible(noData)
        if (noData) binding.noDataMessage.text =
            getString(if (viewModel.isFavorite) R.string.no_favorite_data else R.string.no_data)
    }

    private fun handleIsLoading(isLoading: Boolean) {
        binding.customLoading.root.gone(gone = !isLoading)
    }

}

