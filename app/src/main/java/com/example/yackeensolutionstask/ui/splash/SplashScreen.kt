package com.example.yackeensolutionstask.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.yackeensolutionstask.R
import com.example.yackeensolutionstask.ui.home.MainActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
    }


    override fun onStart() {
        super.onStart()
        logo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.start_fade_in))
        Handler().postDelayed({
            startActivity(Intent(this@SplashScreen, MainActivity::class.java))
            finish()
        }, 3000)
    }

    override fun onDestroy() {
        super.onDestroy()
        logo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.start_fade_out))
    }
}
