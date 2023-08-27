package com.example.mvvmcountries.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmcountries.model.Country
import com.example.mvvmcountries.service.CountryDatabse
import kotlinx.coroutines.launch

class CountryViewModel(application: Application) : BaseViewModel(application) {

    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom(uuid : Int){

        launch {

            val dao = CountryDatabse(getApplication()).countryDao()
            val country = dao.getCountry(uuid)
            countryLiveData.value = country

        }

    }

}