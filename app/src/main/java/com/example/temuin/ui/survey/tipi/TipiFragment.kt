package com.example.temuin.ui.survey.tipi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.temuin.R
import com.example.temuin.api.RetrofitClient
import com.example.temuin.data.QuestionsEntity
import com.example.temuin.data.model.SurveyRequest
import com.example.temuin.data.model.SurveyResponse
import com.example.temuin.databinding.FragmentTipiBinding
import com.example.temuin.ui.recommendation.RecommendationActivity
import com.example.temuin.ui.recommendation.RecommendationActivity.Companion.EXTRA_TYPE
import com.example.temuin.ui.survey.riasec.RiasecFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TipiFragment : Fragment() {

    private lateinit var fragmentTipiBinding: FragmentTipiBinding

    companion object{
        var EXTRA_ANSWER = "extra_answer"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

                fragmentTipiBinding.progBar.visibility = View.VISIBLE

                reqApi(listMerged)
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

    private fun reqApi(listValue: ArrayList<Int>){
        var communityType: String

        val surveyValue = SurveyRequest()
            surveyValue.input = listValue

            RetrofitClient.apiInstance
                .predictCommunity(surveyValue)
                .enqueue(object : Callback<SurveyResponse> {
                    override fun onResponse(
                        call: Call<SurveyResponse>,
                        response: Response<SurveyResponse>
                    ) {
                        if (response.isSuccessful){
                            val value = response.body()
                            communityType = value!!.type

                            val intent = Intent(activity, RecommendationActivity::class.java)
                            intent.putExtra(EXTRA_TYPE, communityType)
                            startActivity(intent)
                            activity?.finish()
                        }
                    }

                    override fun onFailure(call: Call<SurveyResponse>, t: Throwable) {
                        Log.d("Failure", t.message.toString())
                    }
                })
    }
}