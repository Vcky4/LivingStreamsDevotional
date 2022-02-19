package com.ghor.livingstreamsdevotional.ui.archive

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ghor.livingstreamsdevotional.databinding.ArchiveListItemBinding
import com.ghor.livingstreamsdevotional.ui.events.EventData

class ArchiveAdapter: RecyclerView.Adapter<ArchiveAdapter.ArchiveViewHolder>() {

    val archive = mutableListOf<ArchiveData>()

    inner class ArchiveViewHolder(private val binding: ArchiveListItemBinding):
        RecyclerView.ViewHolder(binding.root){

            fun bindItem(items: ArchiveData){
                binding.topic.text = items.topic
                binding.scripture.text = items.scripture
                binding.date.text = items.date
            }

        val card = binding.card
        }

    fun setUpArchive(list: List<ArchiveData>){
        if (this.archive.isEmpty()){
            this.archive.addAll(list)
        }else if (archive.size < list.size){
            this.archive.add(list.last())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArchiveViewHolder {
        return ArchiveViewHolder(
            ArchiveListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ArchiveViewHolder, position: Int) {
        val item = archive[position]
        holder.bindItem(item)

        holder.card.setOnClickListener{
            onItemClickListener?.let { it(item) }
        }
    }

    private var onItemClickListener: ((ArchiveData) -> Unit)? = null

    fun setItemOnClickListener( listener: (ArchiveData) -> Unit){
        onItemClickListener = listener
    }
    override fun getItemCount(): Int {
        return archive.size
    }
}