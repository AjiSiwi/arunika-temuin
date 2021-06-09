package com.example.temuin.ui.recommendation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.temuin.databinding.ActivityRecommendationBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecommendationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRecommendationBinding
    private lateinit var recommendationAdapter: RecommendationAdapter
    private lateinit var recommendationViewModel: RecommendationViewModel

    companion object{
        var EXTRA_TYPE = "extra_type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecommendationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showLoading(true)

        val type = intent.getStringExtra(EXTRA_TYPE)


        recommendationAdapter = RecommendationAdapter()
        recommendationAdapter.notifyDataSetChanged()
        recommendationViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(RecommendationViewModel::class.java)

        binding.apply {
            rvRecommendation.layoutManager = LinearLayoutManager(this@RecommendationActivity)
            rvRecommendation.setHasFixedSize(true)
            rvRecommendation.adapter = recommendationAdapter
        }

        CoroutineScope(Dispatchers.IO).launch {
            recommendationViewModel.setCommunity(type!!)
        }

        recommendationViewModel.getCommunity().observe(this, {
            if (it != null){

                recommendationAdapter.setList(it)
                binding.tvPersonality.text = type
                showLoading(false)
            }
        })

    }

    private fun showLoading(state: Boolean){
        if (state){
            with(binding){
                progBar.visibility = View.VISIBLE
                tvPersonality.visibility = View.GONE
                tvTitlePersonality.visibility = View.GONE
                tvTitleCommunity.visibility = View.GONE
                rvRecommendation.visibility = View.GONE
            }
        }else{
            with(binding){
                progBar.visibility = View.GONE
                tvPersonality.visibility = View.VISIBLE
                tvTitlePersonality.visibility = View.VISIBLE
                tvTitleCommunity.visibility = View.VISIBLE
                rvRecommendation.visibility = View.VISIBLE
            }
        }
    }

}