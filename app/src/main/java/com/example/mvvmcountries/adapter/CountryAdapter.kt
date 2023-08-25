package com.example.mvvmcountries.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmcountries.databinding.ItemCountryBinding
import com.example.mvvmcountries.model.Country
import com.example.mvvmcountries.view.FeedFragmentDirections

class CountryAdapter(var countryList: ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.CountryHolder>() {

    class CountryHolder(var binding : ItemCountryBinding) : RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val itemCountryBinding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CountryHolder(itemCountryBinding)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {

        holder.binding.name.text = countryList.get(position).name
        holder.binding.region.text = countryList[position].region

        holder.itemView.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(0)
            Navigation.findNavController(it).navigate(action)
        }

    }

    fun updateCountryList(newCountryList : List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

}