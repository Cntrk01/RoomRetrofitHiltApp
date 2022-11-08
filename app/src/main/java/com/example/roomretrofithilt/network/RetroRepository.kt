package com.example.roomretrofithilt.network

import androidx.lifecycle.LiveData
import com.example.roomretrofithilt.db.AppDao
import com.example.roomretrofithilt.model.RepositoriesList
import com.example.roomretrofithilt.model.RepositoryData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetroRepository @Inject
constructor(private val retroServiceInterface: RetroService,
    private val appDao: AppDao){

    fun getAllRecords() : LiveData<List<RepositoryData>>{ //return etmeliyiz !
        return appDao.getAllRecords()
    }

    fun insertRecord(repositoryData: RepositoryData){ //return etmemize gerek yok çünkü : ssss gibi birşey yapmadık ama yukarda yaptık
        appDao.insertRecords(repositoryData)
    }

    fun makeApiCall(query:String?){
        val call: Call<RepositoriesList> = retroServiceInterface.getDataFromApi(query!!)
        call?.enqueue(object : Callback<RepositoriesList>{
            override fun onResponse(
                call: Call<RepositoriesList>,
                response: Response<RepositoriesList>
            ) {
                if(response.isSuccessful){
                    appDao.deleteAllRecords() //önce bütünverileri sil
                    response.body()?.items?.forEach { //RepositoryData burda body aldıgımız icin RepositoryList oluyor hatasız dönüyor
                        insertRecord(it)
                    }
                }
            }
            override fun onFailure(call: Call<RepositoriesList>, t: Throwable) {

            }

        })
    }
}