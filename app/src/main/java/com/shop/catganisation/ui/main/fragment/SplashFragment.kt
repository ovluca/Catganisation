package com.shop.catganisation.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.shop.catganisation.R
import com.shop.catganisation.databinding.SplashFragmentBinding
import com.shop.catganisation.viewmodel.SplashViewModel

class SplashFragment : Fragment() {

    private var _binding: SplashFragmentBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    lateinit var viewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SplashFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        viewModel.startSync {
            findNavController()
                .navigate(R.id.action_splashFragment_to_breedsFragment)
        }

        setupObserver();
    }

    private fun setupObserver() {
        WorkManager.getInstance(requireActivity().applicationContext)
            .getWorkInfoByIdLiveData(viewModel.syncBreedWithImage.id)
            .observe(viewLifecycleOwner, { workInfo: WorkInfo? ->
                if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                    findNavController()
                        .navigate(R.id.action_splashFragment_to_breedsFragment)
                }
            })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}