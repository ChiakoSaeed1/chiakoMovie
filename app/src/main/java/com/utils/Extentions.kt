package com.utils

import android.view.View
import android.widget.Adapter
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun ProgressBar.isShown(isShown:Boolean,container:View){
    if (isShown){
        this.visibility = View.VISIBLE
        container.visibility = View.GONE
    }else{
        this.visibility = View.GONE
        container.visibility = View.VISIBLE
    }
}

fun RecyclerView.isAdapter(layoutManager1: RecyclerView.LayoutManager,adapter1: RecyclerView.Adapter<*>){
    this.adapter=adapter1
    this.layoutManager=layoutManager1

}