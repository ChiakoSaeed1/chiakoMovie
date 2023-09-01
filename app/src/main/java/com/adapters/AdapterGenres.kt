package com.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.data.models.ResponseGenres
import com.data.models.ResponseGenres.ResponseGenersItem
import com.data.models.ResponseTopTv
import com.data.models.ResponseTopTv.Data
import com.example.chiakomovie.databinding.SampleGenresBinding
import com.example.chiakomovie.databinding.SampleTopBinding
import com.utils.BaseDiff
import javax.inject.Inject

class AdapterGenres @Inject constructor():RecyclerView.Adapter<AdapterGenres.ViewHolder>() {

    private lateinit var binding:SampleGenresBinding
    private var movies= emptyList<ResponseGenersItem>()
    inner class ViewHolder():RecyclerView.ViewHolder(binding.root){
        fun bind(item: ResponseGenersItem){
            binding.apply {
              txtGenres.text=item.name

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding= SampleGenresBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder()
    }

    override fun getItemCount()= movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun setData(data:List<ResponseGenersItem>){
        val baseDiff=BaseDiff(movies,data)
        val diffUtils=DiffUtil.calculateDiff(baseDiff)
        movies=data
        diffUtils.dispatchUpdatesTo(this)
    }
}