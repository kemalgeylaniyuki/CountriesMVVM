package com.example.mvvmcountries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmcountries.model.Country

class CountryViewModel : ViewModel() {

    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom(){
        val c1 = Country("T","A","A","T","www.img.com","T")
        countryLiveData.value = c1
    }

}