package com.example.roomretrofithilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomretrofithilt.adapter.RecyclerViewAdapter
import com.example.roomretrofithilt.databinding.ActivityMainBinding
import com.example.roomretrofithilt.model.RepositoryData
import com.example.roomretrofithilt.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        initMainViewModel()
    }

    private fun initViewModel() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration =
                DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = RecyclerViewAdapter()
            adapter = recyclerViewAdapter
        }
    }

    private fun initMainViewModel() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getAllRepositoryList().observe(this, Observer {
            it?.let {can->it
                binding.recyclerView.visibility=View.VISIBLE
                recyclerViewAdapter.listData = can
            }
        })

        viewModel.progressBar.observe(this, Observer {
           it?.let {
               if (it) {
                   progressBar.visibility = View.VISIBLE
               } else {
                   progressBar.visibility = View.GONE
               }
           }
        })


    }
}