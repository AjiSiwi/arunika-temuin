package com.example.temuin.ui.survey.tipi

import androidx.lifecycle.ViewModel
import com.example.temuin.data.QuestionsEntity
import com.example.temuin.utils.DataQuestions

class TipiViewModel : ViewModel(){

    fun getQuestions(): List<QuestionsEntity> = DataQuestions.generateTipi()
}