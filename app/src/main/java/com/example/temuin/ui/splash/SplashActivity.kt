package com.example.temuin.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.temuin.databinding.ActivitySplashBinding
import com.example.temuin.ui.login.LoginActivity
import com.example.temuin.ui.survey.SurveyActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, SurveyActivity::class.java))
            finish()
        }, 2000)
    }
}