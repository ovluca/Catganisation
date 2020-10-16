package com.shop.catganisation.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.shop.catganisation.databinding.BreedDetailsFragmentBinding
import com.shop.catganisation.utils.Constants
import com.shop.catganisation.viewmodel.BreedDetailsViewModel
import javax.inject.Inject

/**
 * Created by Ovidiu Florin Luca on 09/10/2020.
 */
class BreedDetailsFragment : Fragment() {

    private var _binding: BreedDetailsFragmentBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: BreedDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BreedDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BreedDetailsViewModel::class.java)
        viewModel.breed = arguments?.getParcelable(Constants.KEY_BREED)!!

        setupViewItems()
    }

    private fun setupViewItems() {
        binding.breedName.text = viewModel.breed.name
        binding.breedCountryCode.text = viewModel.breed.countryCode
        binding.breedTemperament.text = viewModel.breed.temperament
        binding.breedDescription.text = viewModel.breed.description
        binding.breedWikipediaUrl.text = viewModel.breed.wikipediaUrl

        Glide.with(context)  //2
            .load(viewModel.breed.image) //3
            .into(binding.breedImage) //8
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}