package com.example.iddog.ui.image

import androidx.lifecycle.ViewModel
import com.example.iddog.data.Repository
import com.example.iddog.data.api.FeedApiData
import com.example.iddog.model.Feed
import com.example.iddog.utils.DoAsync
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ImageListViewModel : ViewModel() {

    fun getList(
        category: String?,
        onSuccess: (List<String>) -> Unit,
        onError: (String?) -> Unit
    ) {

        DoAsync {
            FeedApiData().get(category!!)
                .subscribeOn(Schedulers.io())
                .takeLast(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ onSuccess(it.list) }, { onError(it.message) })
        }.execute()

    }

}