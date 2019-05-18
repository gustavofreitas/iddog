package com.example.iddog.ui.signup

import android.content.Intent
import android.os.Bundle
import com.example.iddog.utils.validate
import com.example.iddog.utils.isValidEmail
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.iddog.R
import com.example.iddog.ui.image.ImageListActivity
import kotlinx.android.synthetic.main.activity_signup.*

class SignUpActivity : AppCompatActivity() {

    lateinit var model: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        model = ViewModelProviders.of(this).get(SignUpViewModel::class.java)
        model.checkIfUserIsLogged(onSuccess)
        signup_button.setOnClickListener {
            signUp()
        }
    }

    private val onSuccess = fun() {
        startActivity(Intent(this, ImageListActivity::class.java))
    }

    private val onError = fun(e: String?) {
        Toast.makeText(this, "Error on SignUp: $e", Toast.LENGTH_SHORT)
            .show()
        Log.e(SignUpActivity::class.java.toString(), e)
    }



    private fun signUp() {
        email_text.apply {
            validate(
                { s -> s.isValidEmail() },
                "Valid email address required"
            )
            if (error.isNullOrEmpty()) {
                model.signUp(this.text.toString(), onSuccess, onError)
            }
        }
    }
}

