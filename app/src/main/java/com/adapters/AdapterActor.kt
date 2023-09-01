package com.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.data.models.ResponseDetail
import com.data.models.ResponseTopTv
import com.data.models.ResponseTopTv.Data
import com.example.chiakomovie.databinding.SamplePopBinding
import com.example.chiakomovie.databinding.SampleTopBinding
import com.utils.BaseDiff
import javax.inject.Inject

class AdapterActor @Inject constructor():RecyclerView.Adapter<AdapterActor.ViewHolder>() {

    private lateinit var binding: SamplePopBinding
    private var movies= emptyList<String>()
    inner class ViewHolder():RecyclerView.ViewHolder(binding.root){
        fun bind(item:String){
            binding.apply {
                imgCover.load(item){
                    crossfade(true)
                    crossfade(2000)
                }


            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding= SamplePopBinding.inflate(LayoutInflater.from(parent.context),parent,false)
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

    fun setData(data:List<String>){
        val baseDiff=BaseDiff(movies,data)
        val diffUtils=DiffUtil.calculateDiff(baseDiff)
        movies=data
        diffUtils.dispatchUpdatesTo(this)
    }
}