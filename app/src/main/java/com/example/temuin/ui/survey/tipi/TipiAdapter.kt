package com.example.temuin.ui.survey.tipi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.temuin.R
import com.example.temuin.data.QuestionsEntity
import com.example.temuin.databinding.ItemQuestionTipiBinding

class TipiAdapter : RecyclerView.Adapter<TipiAdapter.QuestionViewHolder>() {

    private var listQuestions = ArrayList<QuestionsEntity>()

    fun setQuestions(questions: List<QuestionsEntity>?){
        if (questions == null)  return
        this.listQuestions.clear()
        this.listQuestions.addAll(questions)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val itemQuestionTipiBinding = ItemQuestionTipiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuestionViewHolder(itemQuestionTipiBinding)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val question = listQuestions[position]
        holder.bind(question)
    }

    override fun getItemCount(): Int = listQuestions.size

    class QuestionViewHolder(private val binding: ItemQuestionTipiBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(questions: QuestionsEntity){
            with(binding){
                tvQuestion.text = questions.question
            }

            binding.radioGroup.setOnCheckedChangeListener{group, checkedId ->
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

                    R.id.rb_six -> {
                        ans = 6
                    }

                    R.id.rb_seven -> {
                        ans = 7
                    }
                }
                changeValue(questions, ans)
            }
        }

        private fun changeValue(questions: QuestionsEntity, ans: Int) {
            questions.answer = ans
        }
    }

}