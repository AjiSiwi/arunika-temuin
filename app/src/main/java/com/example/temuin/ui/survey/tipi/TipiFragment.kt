package com.example.temuin.ui.survey.tipi

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.temuin.R
import com.example.temuin.data.QuestionsEntity
import com.example.temuin.databinding.FragmentRiasecBinding
import com.example.temuin.databinding.FragmentTipiBinding
import com.example.temuin.ui.survey.riasec.RiasecAdapter
import com.example.temuin.ui.survey.riasec.RiasecFragment
import com.example.temuin.ui.survey.riasec.RiasecViewModel

class TipiFragment : Fragment() {

    private lateinit var fragmentTipiBinding: FragmentTipiBinding

    companion object{
        var EXTRA_ANSWER = "extra_answer"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentTipiBinding = FragmentTipiBinding.inflate(layoutInflater, container, false)
        return fragmentTipiBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null && arguments != null) {
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[TipiViewModel::class.java]
            val questions = viewModel.getQuestions()
            val listRiasec = arguments?.getIntegerArrayList(EXTRA_ANSWER)
            val listMerged = ArrayList<Int>()

            val tipiAdapter = TipiAdapter()
            tipiAdapter.setQuestions(questions)

            with(fragmentTipiBinding.rvQuestions) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tipiAdapter
            }

            fragmentTipiBinding.btnBack.setOnClickListener{
                val mRiasecFragment = RiasecFragment()
                requireActivity().supportFragmentManager.beginTransaction().apply {
                    replace(R.id.frame_container, mRiasecFragment)
                    commit()
                }
            }

            fragmentTipiBinding.btnSubmit.setOnClickListener {
                val mList = addAnswer(questions)

                listMerged.addAll(listRiasec!!)
                listMerged.addAll(mList)

                Log.d(ContentValues.TAG, listMerged.toString())
            }

        }
    }

    private fun addAnswer(questions: List<QuestionsEntity>): ArrayList<Int> {
        val listAnswer : ArrayList<Int> = ArrayList()

        for (i in 0..questions.size-1){
            listAnswer.add(questions[i].answer)
        }

        return listAnswer
    }
}