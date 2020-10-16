package com.shop.catganisation.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shop.catganisation.R
import com.shop.catganisation.databinding.RowBreedsBinding
import com.shop.catganisation.model.BreedAndImage
import com.shop.catganisation.utils.Constants
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Ovidiu Florin Luca on 08/10/2020.
 */
class BreedsAdapter(private val breeds: ArrayList<BreedAndImage>) :
    RecyclerView.Adapter<BreedsAdapter.ViewHolder>(), Filterable {
    var breedsFilterList = ArrayList<BreedAndImage>()

    init {
        breedsFilterList = breeds
    }

    class ViewHolder(private val binding: RowBreedsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(breed: BreedAndImage) {
            binding.breedTitleText.text = breed.name
            binding.breedDescriptionText.text = breed.description
            Glide.with(itemView.context)
                .load(breed.image)
                .into(binding.breedImage)

            binding.root.setOnClickListener { itemView ->
                itemView.findNavController()
                    .navigate(
                        R.id.action_breedsFragment_to_breedDetailsFragment,
                        bundleOf(Constants.KEY_BREED to breed)
                    )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowBreedsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val breed = breedsFilterList[position]
        holder.bind(breed)
    }

    override fun getItemCount(): Int {
        return breedsFilterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    breedsFilterList = breeds
                } else {
                    val resultList = ArrayList<BreedAndImage>()
                    for (row in breeds) {
                        if (row.origin.toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    breedsFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = breedsFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                breedsFilterList = results?.values as ArrayList<BreedAndImage>
                notifyDataSetChanged()
            }
        }
    }

}