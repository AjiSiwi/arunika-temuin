package com.example.temuin.ui.survey.riasec

import androidx.lifecycle.ViewModel
import com.example.temuin.data.QuestionsEntity
import com.example.temuin.utils.DataQuestions

class RiasecViewModel : ViewModel() {

    fun getQuestions(): List<QuestionsEntity> = DataQuestions.generateRiasec()
}