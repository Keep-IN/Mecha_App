package com.example.mechaapp.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mechaapp.databinding.SpotlightListViewBinding
import com.example.mechaapp.data.model.SpotlightItemModel

class SpotlightListAdapter : RecyclerView.Adapter<SpotlightListAdapter.ViewHolder>()
{
    private var itemListener: ((SpotlightItemModel) -> Unit)? = null
    private val data: MutableList<SpotlightItemModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SpotlightListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(data[position], itemListener)
    }

    override fun getItemCount(): Int = data.size

    fun submitList(list: List<SpotlightItemModel>) {
        val initSize = itemCount
        data.clear()
        notifyItemRangeRemoved(0, initSize)
        data.addAll(list)
        notifyItemRangeInserted(0, data.size)
    }

    inner class ViewHolder(private val binding: SpotlightListViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(item: SpotlightItemModel, Listener: ((SpotlightItemModel) -> Unit)?) {

            binding.root.setOnClickListener {
                Listener?.invoke(item)
            }
            Glide
                .with(binding.root.context)
                .load(item.img)
                .into(binding.impiwSpot)
        }

    }
}