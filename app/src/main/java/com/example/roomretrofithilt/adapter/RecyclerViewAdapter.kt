package com.example.roomretrofithilt.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.roomretrofithilt.R
import com.example.roomretrofithilt.databinding.RepoListRowBinding
import com.example.roomretrofithilt.model.RepositoriesList
import com.example.roomretrofithilt.model.RepositoryData

class RecyclerViewAdapter() : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
//    private var listData:List<RepositoryData>? =null
//    fun setListData(listData : List<RepositoryData>?){
//        this.listData=listData
//    }

    private val diffcalback=object : DiffUtil.ItemCallback<RepositoryData>(){
        override fun areItemsTheSame(oldItem: RepositoryData, newItem: RepositoryData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RepositoryData, newItem: RepositoryData): Boolean {
            return newItem == oldItem
        }

    }

    private var differ=AsyncListDiffer(this,diffcalback)
    var listData: List<RepositoryData>?
    get() = differ.currentList
    set(value) {differ.submitList(value)}

    class MyViewHolder(val view:RepoListRowBinding) : RecyclerView.ViewHolder(view.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RepoListRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item=listData?.get(position)
        holder.view.apply {
            tvName.text=item?.name
            tvDesc.text=item?.description
            imageAvatarUrl.load(item?.owner?.avatar_url){
                crossfade(true)
                crossfade(1000)
            }
        }
    }

    override fun getItemCount(): Int {
        if(listData==null) return 0
        return listData?.size!!
    }

}