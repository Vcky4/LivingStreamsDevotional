package com.ghor.livingstreamsdevotional.ui.adminnugget

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ghor.livingstreamsdevotional.R
import com.ghor.livingstreamsdevotional.databinding.NuggetListItemBinding
import com.ghor.livingstreamsdevotional.ui.nuggets.NuggetData

class AdminNuggetAdapter: RecyclerView.Adapter<AdminNuggetAdapter.NuggetViewHolder>() {

    private val nuggetList = mutableListOf<NuggetData>()

    inner class NuggetViewHolder(private val binding: NuggetListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindNuggets(nuggets: NuggetData) {
            binding.nuggetsT.text = nuggets.nugget
        }

        fun setPosition(position: Int) {
            if (position % 2 == 0) {
                binding.nuggetsT.setBackgroundResource(R.drawable.nugget_fill_left)
                binding.layout.setPadding(10, 10, 90, 10)
            } else {
                binding.nuggetsT.setBackgroundResource(R.drawable.nugget_fill_right)
                binding.layout.setPadding(90, 10, 10, 10)
            }
        }
        val card = binding.card
    }

    fun setUpNuggets(nuggets: List<NuggetData>) {
        when {
            this.nuggetList.isEmpty() -> {
                this.nuggetList.addAll(nuggets)
            }
            nuggetList.size < nuggets.size -> {
                this.nuggetList.add(nuggets.last())
            }
            nuggetList.size > nuggets.size -> {
                this.nuggetList.clear()
                this.nuggetList.addAll(nuggets)
            }
        }
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

        holder.card.setOnLongClickListener {
            onItemLongClickListener?.let { it(nugget) }
            return@setOnLongClickListener true
        }
    }

    private var onItemLongClickListener: ((NuggetData) -> Unit)? = null

    fun setItemOnLongClickListener( listener: (NuggetData) -> Unit){
        onItemLongClickListener = listener
    }

    override fun getItemCount(): Int {
        return nuggetList.size
    }
}