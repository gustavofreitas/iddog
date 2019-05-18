package com.example.iddog.ui.image

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.iddog.R
import com.example.iddog.data.api.getPicasso
import com.google.android.flexbox.FlexboxLayoutManager



class ImageListAdapter(private val dogs: List<String>, internal val zoomedImagePlaceholder: View): RecyclerView.Adapter<ImageListAdapter.ImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.image_item, parent, false)

        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dogs.count()
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bindView(dogs[position], zoomedImagePlaceholder)
    }

    class ImageViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        fun bindView(url: String, zoom: View){
            val imageView = view.findViewById<ImageView>(R.id.dog_image)

            getPicasso(view.context).load(url).into(imageView)

            imageView.setOnClickListener {
                getPicasso(zoom.context).load(url).into(zoom as ImageView)
                zoom.visibility = View.VISIBLE
                zoom.setOnClickListener {
                    it.visibility = View.INVISIBLE
                }
            }

            val lp = imageView.layoutParams
            if (lp is FlexboxLayoutManager.LayoutParams) {
                lp.flexGrow = 1f
            }

        }
    }
}
