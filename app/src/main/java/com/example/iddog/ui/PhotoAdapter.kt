package com.example.iddog.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.example.iddog.R

class PhotoAdapter(private val dogs: List<String>, private val zoomAnimator: (View, Int) -> Unit): RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    override fun getItemCount(): Int {
        return dogs.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.photo_recycler_item, parent, false)

        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bindView(dogs[position], zoomAnimator)
    }

    class PhotoViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bindView(url: String, zoomAnimator: (View, Int) -> Unit){
            view.findViewById<ImageButton>(R.id.dog_image).setOnClickListener {
                zoomAnimator(it, R.drawable.lucario)
            }
        }
    }



}
