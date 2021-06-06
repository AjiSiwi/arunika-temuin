package com.example.temuin.ui.recommendation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.temuin.databinding.ActivityRecommendationBinding

class RecommendationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRecommendationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecommendationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}