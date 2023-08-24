package com.example.mvvmcountries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmcountries.model.Country

class FeedViewModel : ViewModel() {

    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData(){

        val c1 = Country("T","A","A","T","www.img.com","T")
        val c2 = Country("F","P","E","E","www.img.com","F")
        val c3 = Country("G","B","E","E","www.img.com","D")

        val countryList = arrayListOf<Country>(c1,c2,c3)

        countries.value = countryList
        countryError.value = false
        countryLoading.value = false

    }

}