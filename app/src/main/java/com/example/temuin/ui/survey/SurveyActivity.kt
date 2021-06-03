package com.example.temuin.ui.survey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.temuin.R
import com.example.temuin.databinding.ActivitySurveyBinding
import com.example.temuin.ui.survey.riasec.RiasecFragment

class SurveyActivity : AppCompatActivity() {

    private lateinit var surveyBinding: ActivitySurveyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        surveyBinding = ActivitySurveyBinding.inflate(layoutInflater)
        setContentView(surveyBinding.root)

        val mFragmentManager = supportFragmentManager
        val mHomeFragment = RiasecFragment()
        val fragment = mFragmentManager.findFragmentByTag(RiasecFragment::class.java.simpleName)

        if (fragment !is RiasecFragment){
            mFragmentManager
                .beginTransaction()
                .add(R.id.frame_container, mHomeFragment, RiasecFragment::class.java.simpleName)
                .commit()
        }
    }
}