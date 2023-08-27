package com.example.mvvmcountries.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.mvvmcountries.model.Country
import com.example.mvvmcountries.service.CountryAPIService
import com.example.mvvmcountries.service.CountryDatabse
import com.example.mvvmcountries.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(application : Application) : BaseViewModel(application) {

    private val countryAPIService = CountryAPIService()
    private val compositeDisposable = CompositeDisposable()
    private var customSharedPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L

    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData(){

        val updateTime = customSharedPreferences.getTime()

        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime){
            getDataFromSQLite()
        }
        else{
            getDataFromAPI()
        }

    }

    fun refreshFromAPI(){
        getDataFromAPI()
    }

    fun getDataFromSQLite(){

        countryLoading.value = true

        launch {
            val countries = CountryDatabse(getApplication()).countryDao().getAllCountries()
            showCountries(countries)
            Toast.makeText(getApplication(),"Countries From SQLite",Toast.LENGTH_LONG).show()
        }
    }

    fun getDataFromAPI(){

        countryLoading.value = true

        compositeDisposable.add(countryAPIService.getData()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                override fun onSuccess(t: List<Country>) {
                    storeInSQLite(t)
                }

                override fun onError(e: Throwable) {

                    countryLoading.value = false
                    countryError.value = true
                    e.printStackTrace()

                }

            })
        )

    }

    private fun showCountries(countryList : List<Country>){
        countries.value = countryList
        countryLoading.value = false
        countryError.value = false
    }

    private fun storeInSQLite(list : List<Country>){

        launch {
            val dao = CountryDatabse(getApplication()).countryDao()
            dao.deleteAllCountries()

            val listLong = dao.insertAll(*list.toTypedArray()) // list -> individual

            var i = 0
            while (i < list.size){
                list[i].uuid = listLong[i].toInt()
                i++
            }

            showCountries(list)
        }

        customSharedPreferences.savedTime(System.nanoTime())

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}