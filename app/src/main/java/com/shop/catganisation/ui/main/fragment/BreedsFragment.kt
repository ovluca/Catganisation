package com.shop.catganisation.ui.main.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shop.catganisation.R
import com.shop.catganisation.databinding.BreedsFragmentBinding
import com.shop.catganisation.viewmodel.BreedsViewModel

class BreedsFragment : Fragment() {

    private var _binding: BreedsFragmentBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    lateinit var viewModel: BreedsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BreedsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BreedsViewModel::class.java)
        setupAdapter()
        setupSearch()
    }

    private fun setupSearch() {
        val textView = binding.countrySearch.findViewById<TextView>(R.id.search_src_text)
        textView.setTextColor(Color.WHITE)
        val searchIcon = binding.countrySearch.findViewById<ImageView>(R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.WHITE)
        val cancelIcon = binding.countrySearch.findViewById<ImageView>(R.id.search_close_btn)
        cancelIcon.setColorFilter(Color.WHITE)
        binding.countrySearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.breedsAdapter.filter.filter(newText)
                return false
            }

        })

    }

    private fun setupAdapter() {
        binding.breedsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.breedsRecyclerView.adapter = viewModel.breedsAdapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}