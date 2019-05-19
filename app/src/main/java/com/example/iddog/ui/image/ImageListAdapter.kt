package com.example.iddog.ui.image

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.iddog.R
import com.google.android.flexbox.FlexboxLayoutManager
import com.squareup.picasso.Picasso

class ImageListAdapter(private val dogs: List<String>, private val expandedImageView: ImageView) :
    RecyclerView.Adapter<ImageListAdapter.ImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.image_item, parent, false)

        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dogs.count()
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bindView(dogs[position], expandedImageView)
    }

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView = view.findViewById<ImageView>(R.id.dog_image)

        fun bindView(url: String, expandedImageView: ImageView) {

            val picasso: Picasso = Picasso.get()

            picasso.load(url).into(imageView)

            imageView.setOnClickListener {
                expandedImageView.apply {
                    picasso.load(url).into(this)
                    visibility = View.VISIBLE
                    setOnClickListener {
                        it.visibility = View.INVISIBLE
                    }
                }
            }

            val lp = imageView.layoutParams
            if (lp is FlexboxLayoutManager.LayoutParams) {
                lp.flexGrow = 1f
            }

        }

    }
}
