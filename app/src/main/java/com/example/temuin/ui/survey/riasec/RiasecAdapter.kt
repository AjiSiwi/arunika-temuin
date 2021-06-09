package com.example.temuin.ui.survey.riasec

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.temuin.R
import com.example.temuin.data.QuestionsEntity
import com.example.temuin.databinding.ItemQuestionRiasecBinding

class RiasecAdapter : RecyclerView.Adapter<RiasecAdapter.QuestionViewHolder>(){

    private var listQuestions = ArrayList<QuestionsEntity>()

    fun setQuestions(questions: List<QuestionsEntity>?){
        if (questions == null) return
        this.listQuestions.clear()
        this.listQuestions.addAll(questions)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val itemQuestionsRiasecBinding = ItemQuestionRiasecBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  QuestionViewHolder(itemQuestionsRiasecBinding)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val question = listQuestions[position]
        holder.bind(question)
    }

    override fun getItemCount(): Int = listQuestions.size

    class QuestionViewHolder(private val binding: ItemQuestionRiasecBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(question: QuestionsEntity){
            binding.tvQuestion.text = question.question
            binding.radioGroup.setOnCheckedChangeListener{_, checkedId ->
                var ans = 0
                when(checkedId){
                    R.id.rb_one -> {
                        ans = 1
                    }

                    R.id.rb_two -> {
                        ans = 2
                    }

                    R.id.rb_three -> {
                        ans = 3
                    }

                    R.id.rb_four -> {
                        ans = 4
                    }

                    R.id.rb_five -> {
                        ans = 5
                    }
                }
                changeValue(question, ans)
            }
        }

        private fun changeValue(question: QuestionsEntity, ans: Int) {
            question.answer = ans
        }
    }

}