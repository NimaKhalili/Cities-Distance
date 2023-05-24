package com.example.citiesdistance.feature.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.citiesdistance.data.Distance
import com.example.citiesdistance.databinding.ItemDistanceListBinding
import timber.log.Timber

class DistanceListAdapter : RecyclerView.Adapter<DistanceListAdapter.ViewHolder>() {
    var distanceList = ArrayList<Distance>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: ItemDistanceListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindDistance(distance: Distance) {
            Timber.i(distance.beginning)
            binding.textViewItemDistanceListBeginning.text = distance.beginning
            binding.textViewItemDistanceListDestination.text = distance.destination
            binding.textViewItemDistanceListDistance.text = "${distance.distance} کیلومتر"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemDistanceListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindDistance(distanceList[position])

    override fun getItemCount(): Int = distanceList.size
}