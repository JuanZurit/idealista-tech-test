package com.juanzurita.presentation.ads

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.juanzurita.presentation.databinding.FragmentAdListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdListFragment : Fragment() {

    private val args: AdListFragmentArgs by navArgs()

    private val viewModel by viewModels<AdListViewModel>()

    private var _binding: FragmentAdListBinding? = null
    private val binding: FragmentAdListBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAdListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val isFavorite = args.isFavorite
        viewModel.test()
    }


}

