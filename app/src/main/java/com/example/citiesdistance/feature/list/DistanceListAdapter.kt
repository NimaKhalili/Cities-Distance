package com.example.citiesdistance.feature.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.citiesdistance.data.Distance
import com.example.citiesdistance.databinding.ItemDistanceListBinding

class DistanceListAdapter() :
    ListAdapter<Distance, DistanceListAdapter.MyViewHolder>(MyDiffUtil) {
    var onLongClick: ((Distance) -> Unit)? = null

    companion object MyDiffUtil : DiffUtil.ItemCallback<Distance>() {
        override fun areItemsTheSame(oldItem: Distance, newItem: Distance): Boolean =
            oldItem == newItem


        override fun areContentsTheSame(oldItem: Distance, newItem: Distance): Boolean =
            oldItem.id == newItem.id

    }

    inner class MyViewHolder(private val binding: ItemDistanceListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(distance: Distance?) {
            binding.textViewItemDistanceListBeginning.text = distance?.beginning
            binding.textViewItemDistanceListDestination.text = distance?.destination
            binding.textViewItemDistanceListDistance.text = "${distance?.distance} کیلومتر"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemDistanceListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val distance = getItem(position)
        holder.bind(distance)

        holder.itemView.setOnLongClickListener {
            onLongClick?.invoke(distance)
            return@setOnLongClickListener false
        }
    }
}