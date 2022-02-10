package com.ghor.livingstreamsdevotional.ui.nuggets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ghor.livingstreamsdevotional.databinding.NuggetListItemBinding

class NuggetAdapter : RecyclerView.Adapter<NuggetAdapter.NuggetViewHolder>() {

    private val nuggetList = mutableListOf<NuggetData>()

    inner class NuggetViewHolder(private val binding: NuggetListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindNuggets(nuggets: NuggetData) {
            binding.nuggetsT.text = nuggets.nugget
        }

        fun setPosition(position: Int){
            if (position.div(2) == 0){
                binding.layout.setPadding(90, 10, 10, 10)
            }else{
                binding.layout.setPadding(10, 10, 90, 10)

            }
        }
    }

    fun setUpNuggets(nuggets: List<NuggetData>){
        this.nuggetList.addAll(nuggets)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NuggetViewHolder {
        return NuggetViewHolder(
            NuggetListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NuggetViewHolder, position: Int) {
        val nugget = nuggetList[position]
        holder.bindNuggets(nugget)
        holder.position = position
    }

    override fun getItemCount(): Int {
        return nuggetList.size
    }
}