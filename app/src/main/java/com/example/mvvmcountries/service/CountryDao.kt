package com.example.mvvmcountries.service

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mvvmcountries.model.Country

@Dao
interface CountryDao {

    @Insert
    suspend fun insertAll(vararg countries : Country) : List<Long>

    // @Insert -> INSERT INTO
    // suspend -> coroutine, pause & resume
    // vararg -> multiple country objects
    // List<Long> -> primary keys

    @Query("SELECT * FROM Country")
    suspend fun getAllCountries() : List<Country>

    @Query("SELECT * FROM Country WHERE uuid = :countryID")
    suspend fun getCountry(countryID : Int) : Country

    @Query("DELETE FROM Country")
    suspend fun deleteAllCountries()

}