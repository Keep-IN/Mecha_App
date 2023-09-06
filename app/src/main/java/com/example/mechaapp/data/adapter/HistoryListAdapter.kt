package com.example.mechaapp.data.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mechaapp.data.Model.OrderModel
import com.example.mechaapp.databinding.HistoryListViewBinding

class HistoryListAdapter: RecyclerView.Adapter<HistoryListAdapter.ViewHolder>()
{
    private  var itemListener: ((OrderModel) -> Unit)? = null
    private  val data: MutableList<OrderModel> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            HistoryListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    inner class ViewHolder(private val binding: HistoryListViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun setData(item: OrderModel, listener: ((OrderModel) -> Unit)?){
            when (item.status) {
                "Dijadwalkan" -> {
                    binding.tvStatusLayanan.setTextColor(Color.parseColor("#358F80"))
                }
                "Dibatalkan" -> {
                    binding.tvStatusLayanan.setTextColor(Color.parseColor("#E21F30"))
                }
                "Selesai" -> {
                    binding.tvStatusLayanan.setTextColor(Color.parseColor("#85B804"))
                }
            }
            binding.root.setOnClickListener{
                listener?.invoke(item)
            }
            with(binding){
                tvIdLayanan.text = "Id pesanan : ${item.id}"
                tvLayananHistory.text = item.name_services
                tvTanggalLayanan.text = item.created_at
                tvStatusLayanan.text = item.status
                tvLayananTambal.text = item.sum
            }
        }
    }
    fun setOnclickItem(listener: ((OrderModel) -> Unit)?){
        this.itemListener = listener
    }
}