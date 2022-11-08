package com.example.roomretrofithilt.di

import android.app.Application
import android.content.Context
import com.example.roomretrofithilt.db.AppDao
import com.example.roomretrofithilt.db.AppDatabase
import com.example.roomretrofithilt.network.RetroService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun getAppDataBase(context: Application) : AppDatabase{//context olarak Application kullanıyoruz dikkat!
        return AppDatabase.getAppDBInstance(context)
    }
    @Provides
    @Singleton
    fun getAppDao(appDatabase: AppDatabase) :AppDao{
        return appDatabase.getAppDao()
    }


    val BASE_URL="https://api.github.com/search/"

    @Provides
    @Singleton
    fun getRetroServiceInstance(retrofit: Retrofit) : RetroService{
        return retrofit.create(RetroService::class.java)
    }


    @Provides
    @Singleton
    fun getRetroInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}