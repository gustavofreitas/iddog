package com.example.iddog.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.iddog.utils.validate
import com.example.iddog.utils.isValidEmail
import android.util.Log
import android.widget.Toast
import com.example.iddog.R
import kotlinx.android.synthetic.main.activity_main.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        signup_button.setOnClickListener {
            signUp()
        }
    }

    private val onSuccess = fun() {
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }

    private val onError = fun(e: String?) {
        Toast.makeText(this, "Error on SignUp: $e", Toast.LENGTH_SHORT)
            .show()
        Log.e(SignUpActivity::class.java.toString(), e)
    }

    private fun signUp() {
        email_text.validate(
            { s -> s.isValidEmail() },
            "Valid email address required"
        )

        if (email_text.error.isNullOrEmpty()) {
            val email: String = email_text.text.toString()
            SignUpViewModel().signUp(email, onSuccess, onError)
        }
    }
}

