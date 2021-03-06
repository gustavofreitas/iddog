package com.example.iddog.utils

import android.os.AsyncTask
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.EditText

fun EditText.validate(validator: (String) -> Boolean, message: String) {
    fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                afterTextChanged.invoke(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
        })
    }

    this.afterTextChanged {
        this.error = if (validator(it)) null else message
    }
    this.error = if (validator(this.text.toString())) null else message
}

fun String.isValidEmail(): Boolean
        = this.isNotEmpty() &&
        Patterns.EMAIL_ADDRESS.matcher(this).matches()

open class DoAsync(open val handler: () -> Unit) : AsyncTask<Void, Void, Void>() {
    override fun doInBackground(vararg params: Void?): Void? {
        handler()
        return null
    }
}

class DoAsyncWithCallback( override val handler: () -> Unit, val callback: () -> Unit) : DoAsync(handler) {

    override fun onPostExecute(result: Void?) {
        callback()
    }
}
