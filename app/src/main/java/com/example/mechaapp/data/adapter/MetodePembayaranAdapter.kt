package com.example.mechaapp.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mechaapp.databinding.MetodeListViewBinding
import com.example.mechaapp.data.Model.MetodeItemModel

class MetodePembayaranAdapter: RecyclerView.Adapter<MetodePembayaranAdapter.ViewHolder>() {
    private  var itemListener: ((MetodeItemModel) -> Unit)? = null
    private  val data: MutableList<MetodeItemModel> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MetodeListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(data[position], itemListener)
    }

    override fun getItemCount(): Int = data.size

    fun submitList(list: List<MetodeItemModel>) {
        val initSize = itemCount
        data.clear()
        notifyItemRangeRemoved(0, initSize)
        data.addAll(list)
        notifyItemRangeInserted(0, data.size)
    }
    inner class ViewHolder(private val binding: MetodeListViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun setData(item: MetodeItemModel, listener: ((MetodeItemModel) -> Unit)?){
            binding.root.setOnClickListener{
                listener?.invoke(item)
            }
            with(binding){
                tvBank.text = item.namabank
                Glide
                    .with(binding.root.context)
                    .load(item.img)
                    .into(binding.ivBank)
            }
        }
    }
    fun setOnclickItem(listener: ((MetodeItemModel) -> Unit)?){
        this.itemListener = listener
    }
}
