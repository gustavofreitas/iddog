package com.example.iddog.ui.image

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.iddog.R
import com.example.iddog.api.getPicasso
import com.squareup.picasso.Picasso
import com.google.android.flexbox.FlexboxLayoutManager



class ImageListAdapter(private val dogs: List<String>, private val zoomAnimator: (View, String) -> Unit): RecyclerView.Adapter<ImageListAdapter.ImageViewHolder>() {

    override fun getItemCount(): Int {
        return dogs.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.image_item, parent, false)

        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bindView(dogs[position], zoomAnimator)
    }

    class ImageViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        fun bindView(url: String, zoomAnimator: (View, String) -> Unit){
            val imageView = view.findViewById<ImageView>(R.id.dog_image)

            getPicasso(view.context).load(url).into(imageView)

            imageView.setOnClickListener {
                zoomAnimator(it, url)
            }

            val lp = imageView.layoutParams
            if (lp is FlexboxLayoutManager.LayoutParams) {
                lp.flexGrow = 1f
            }

        }
    }
}
