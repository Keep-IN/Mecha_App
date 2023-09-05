package com.example.mechaapp.data.adapter

import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mechaapp.R
import com.example.mechaapp.data.Model.HargaItemModel
import com.example.mechaapp.data.Model.SpotlightItemModel
import com.example.mechaapp.databinding.LayananTambahanViewBinding
import com.example.mechaapp.databinding.SpotlightListViewBinding

class LayananTambahAdapter: RecyclerView.Adapter<LayananTambahAdapter.ViewHolder>()
{
    private var itemListener : ((HargaItemModel) -> Unit)? = null
    private val data: MutableList<HargaItemModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayananTambahanViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(data[position], itemListener)
    }

    override fun getItemCount(): Int = data.size
    fun submitList(list: List<HargaItemModel>) {
        val initSize = itemCount
        data.clear()
        notifyItemRangeRemoved(0, initSize)
        data.addAll(list)
        notifyItemRangeInserted(0, data.size)
    }

    inner class ViewHolder(private val binding: LayananTambahanViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(item: HargaItemModel, Listener: ((HargaItemModel) -> Unit)?) {

            binding.root.setOnClickListener {
                Listener?.invoke(item)
            }
             binding.tilDesc.editText?.setText(item.desc)
            binding.tilPrice.editText?.setText(item.price.toString())
        }
    }
}
