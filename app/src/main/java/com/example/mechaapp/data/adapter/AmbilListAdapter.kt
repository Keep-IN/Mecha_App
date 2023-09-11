package com.example.mechaapp.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mechaapp.databinding.AmbilPesananListBinding
import com.example.mechaapp.data.Model.AmbilItemModel
import com.example.mechaapp.data.Model.OrderModel

class AmbilListAdapter : RecyclerView.Adapter<AmbilListAdapter.ViewHolder>() {
    private  var itemListener: ((OrderModel) -> Unit)? = null
    private  val data: MutableList<OrderModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AmbilPesananListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(data[position], itemListener)
    }

    override fun getItemCount(): Int = data.size

    fun submitList(list: List<OrderModel>) {
        val initSize = itemCount
        data.clear()
        notifyItemRangeRemoved(0, initSize)
        data.addAll(list)
        notifyItemRangeInserted(0, data.size)
    }

    inner class ViewHolder(private val binding: AmbilPesananListBinding): RecyclerView.ViewHolder(binding.root) {
        fun setData(item: OrderModel, listener: ((OrderModel) -> Unit)?){
            binding.root.setOnClickListener{
                listener?.invoke(item)
            }
            with(binding){
                tvNamaPemesan.text = item.name
                tvJenisLayanan.text = item.name_service
                tvAddress.text = item.address
                tvHargaLayanan.text = item.sum
            }
            Glide
                .with(binding.root.context)
                .load(item.img_url)
                .into(binding.ivProfile)
        }
    }
    fun setOnclickItem(listener: ((OrderModel) -> Unit)?){
        this.itemListener = listener
    }
}