package com.example.temuin.ui.recommendation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.temuin.data.CommunityEntity
import com.example.temuin.databinding.ItemRecommendationBinding

class RecommendationAdapter : RecyclerView.Adapter<RecommendationAdapter.CommunityViewHolder>() {

    private val listCommunity = ArrayList<CommunityEntity>()

    fun setList(community: ArrayList<CommunityEntity>){
        listCommunity.clear()
        listCommunity.addAll(community)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityViewHolder {
        val itemRecommendationBinding = ItemRecommendationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommunityViewHolder(itemRecommendationBinding)
    }

    override fun onBindViewHolder(holder: CommunityViewHolder, position: Int) {
        val community = listCommunity[position]
        holder.bind(community)
    }

    override fun getItemCount(): Int = listCommunity.size

    class CommunityViewHolder(private val binding: ItemRecommendationBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(community: CommunityEntity){
            binding.apply {
                Glide.with(itemView)
                    .load(community.image)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imgCover)

                tvCommunityName.text = community.name
                tvCommunityDesc.text = community.description
            }
        }
    }

}