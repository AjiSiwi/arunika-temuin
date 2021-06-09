package com.example.temuin.ui.survey.riasec

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.temuin.R
import com.example.temuin.data.QuestionsEntity
import com.example.temuin.databinding.FragmentRiasecBinding
import com.example.temuin.ui.survey.tipi.TipiFragment

class RiasecFragment : Fragment(){

    private lateinit var fragmentRiasecBinding: FragmentRiasecBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentRiasecBinding = FragmentRiasecBinding.inflate(layoutInflater, container, false)
        return fragmentRiasecBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[RiasecViewModel::class.java]
            val questions = viewModel.getQuestions()

            val riasecAdapter = RiasecAdapter()
            riasecAdapter.setQuestions(questions)

            with(fragmentRiasecBinding.rvQuestions){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = riasecAdapter
            }

            fragmentRiasecBinding.btnNext.setOnClickListener{
                val mTipiFragment = TipiFragment()
                val mlist = addAnswer(questions)
                val mBundle = Bundle()

                mBundle.putIntegerArrayList(TipiFragment.EXTRA_ANSWER, mlist)
                mTipiFragment.arguments = mBundle

                requireActivity().supportFragmentManager.beginTransaction().apply {
                    replace(R.id.frame_container, mTipiFragment, TipiFragment::class.java.simpleName)
                    commit()
                }
            }
        }
    }

    private fun addAnswer(questions: List<QuestionsEntity>): ArrayList<Int> {
        val listAnswer : ArrayList<Int> = ArrayList()

        for (element in questions){
            listAnswer.add(element.answer)
        }
        return listAnswer
    }
}