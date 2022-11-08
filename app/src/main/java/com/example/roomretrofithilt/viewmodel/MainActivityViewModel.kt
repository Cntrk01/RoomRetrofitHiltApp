package com.example.roomretrofithilt.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.roomretrofithilt.model.RepositoryData
import com.example.roomretrofithilt.network.RetroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
@Inject constructor(private val repository: RetroRepository) : ViewModel() {

    var progressBar:MutableLiveData<Boolean> = MutableLiveData( true)

    fun getAllRepositoryList(): LiveData<List<RepositoryData>> {
        return repository.getAllRecords()
    }

    fun makeApiCall() {
        repository.makeApiCall("ny")
    }


}