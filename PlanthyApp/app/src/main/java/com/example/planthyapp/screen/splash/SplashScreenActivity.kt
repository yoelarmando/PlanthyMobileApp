package com.example.planthyapp.screen.splash

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.planthyapp.databinding.ActivitySplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val delayTimeScreen = 4000

        supportActionBar?.hide()

        setAnimation()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashScreenActivity, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }, delayTimeScreen.toLong())
    }



    @SuppressLint("Recycle")
    private fun setAnimation(){
        val welcome = ObjectAnimator.ofFloat(binding.textView, View.ALPHA, 1f).setDuration(500)
        val title = ObjectAnimator.ofFloat(binding.textView2, View.ALPHA, 1f).setDuration(500)
        val subtitle = ObjectAnimator.ofFloat(binding.textView3, View.ALPHA, 1f).setDuration(500)
        val copyright = ObjectAnimator.ofFloat(binding.textView4, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(
                welcome,
                title,
                subtitle,
                copyright
            )
            startDelay = 500
        }.start()
    }
}