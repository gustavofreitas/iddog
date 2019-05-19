package com.example.iddog.ui.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.iddog.R
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.fragment_image_list.*

class ImageListFragment : Fragment() {

    private lateinit var model: ImageListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_image_list, container, false)

        val bundle: Bundle? = arguments
        val category: String? = bundle?.getString("category")

        model = ViewModelProviders.of(this).get(ImageListViewModel::class.java)

        model.getList(category, onSuccess, onError)

        return view
    }

    private val onError = fun(e: String?) {
        Toast.makeText(this.context, "Error on SignUp: $e", Toast.LENGTH_SHORT)
            .show()
    }

    private val onSuccess = fun(imageUrls: List<String>){
        val flexBoxLayoutManager = FlexboxLayoutManager(this.context).apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
            alignItems = AlignItems.STRETCH
        }
        photo_recycler.layoutManager = flexBoxLayoutManager
        photo_recycler.adapter = ImageListAdapter(imageUrls, expanded_image)
    }

    companion object {
        fun newInstance(category: String) : ImageListFragment {
            val fragment = ImageListFragment()
            val bundle = Bundle()
            bundle.putString("category", category)
            fragment.arguments = bundle

            return fragment
        }
    }
}