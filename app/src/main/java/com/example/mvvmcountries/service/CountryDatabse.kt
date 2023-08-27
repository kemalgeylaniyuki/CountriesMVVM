package com.example.mvvmcountries.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmcountries.model.Country

@Database(entities = arrayOf(Country::class), version = 1)
abstract class CountryDatabse : RoomDatabase() {

    abstract fun countryDao() : CountryDao

    //Singleton
    companion object {

        @Volatile private var instance : CountryDatabse? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context : Context) = Room.databaseBuilder(
            context.applicationContext, CountryDatabse::class.java, "countrydatabase"
        ).build()

    }

}