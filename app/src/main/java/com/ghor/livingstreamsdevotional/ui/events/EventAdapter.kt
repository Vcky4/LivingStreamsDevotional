package com.ghor.livingstreamsdevotional.ui.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ghor.livingstreamsdevotional.databinding.EventListItemBinding
import com.ghor.livingstreamsdevotional.ui.archive.ArchiveData

class EventAdapter: RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    private val eventList = mutableListOf<EventData>()

    inner class EventViewHolder(private val binding: EventListItemBinding):
        RecyclerView.ViewHolder(binding.root){

            fun bindEvents(event: EventData){
                binding.day.text = event.day
                binding.month.text = event.month
                binding.eventTime.text = event.time
                binding.eventTitle.text = event.title
                binding.eventVenue.text = event.venue
                binding.eventDescription.text = event.description
            }

        val card = binding.card
        }

    fun setUpEvents(event: List<EventData>){
        if (this.eventList.isEmpty()){
            this.eventList.addAll(event)
        }else if (eventList.size < event.size){
            this.eventList.add(event.last())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(
            EventListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = eventList[position]
        holder.bindEvents(event)

        holder.card.setOnClickListener {
            onItemClickListener?.let { it(event) }
        }

    }

    private var onItemClickListener: ((EventData) -> Unit)? = null

    fun setItemOnClickListener( listener: (EventData) -> Unit){
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return eventList.size
    }
}