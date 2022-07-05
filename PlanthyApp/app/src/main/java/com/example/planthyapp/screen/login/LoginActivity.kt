package com.example.planthyapp.screen.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.planthyapp.databinding.ActivityLoginBinding
import com.example.planthyapp.screen.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        auth = Firebase.auth

        binding.btnLogin.setOnClickListener {
            setLogin(auth)
        }
    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser

        if(currentUser != null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setLogin(auth: FirebaseAuth){

        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else {
                Toast.makeText(this@LoginActivity, "Error Login", Toast.LENGTH_SHORT).show()
            }
        }
    }
}