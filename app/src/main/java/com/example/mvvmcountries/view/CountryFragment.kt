package com.example.mvvmcountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmcountries.R
import com.example.mvvmcountries.databinding.FragmentCountryBinding
import com.example.mvvmcountries.viewmodel.CountryViewModel

class CountryFragment : Fragment() {

    private var counrtyUUID = 0
    private var _binding : FragmentCountryBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : CountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_country, container, false)
        _binding = FragmentCountryBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom()

        observeLiveData()

        arguments?.let {
           counrtyUUID = CountryFragmentArgs.fromBundle(it).countryUUID
        }

    }

    private fun observeLiveData(){

        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
                binding.countryName.text = it.name
                binding.countryCapital.text = it.capital
                binding.countryRegion.text = it.region
                binding.countryCurrency.text = it.currency
                binding.countryLanguage.text = it.language
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}