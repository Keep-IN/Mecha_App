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
        data.addAll(list.sortedByDescending { it.created_at })
        notifyItemRangeInserted(0, data.size)
    }

    inner class ViewHolder(private val binding: HistoryListViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun setData(item: OrderModel, listener: ((OrderModel) -> Unit)?){
            when (item.status) {
                "Diterima" -> {
                    binding.cvStatusLayanan.setCardBackgroundColor(Color.parseColor("#1BCABB"))
                }
                "Dibatalkan" -> {
                    binding.cvStatusLayanan.setCardBackgroundColor(Color.parseColor("#F3887E"))
                }
                "Selesai" -> {
                    binding.cvStatusLayanan.setCardBackgroundColor(Color.parseColor("#67E785"))
                }
                "Menunggu" -> {
                    binding.cvStatusLayanan.setCardBackgroundColor(Color.parseColor("#D1CA49"))
                }
            }
            binding.root.setOnClickListener{
                listener?.invoke(item)
            }
            with(binding){
                tvIdLayanan.text = "Id pesanan : ${item.id_service}"
                tvLayananHistory.text = item.name_service
                tvTanggalLayanan.text = item.created_at
                tvStatusLayanan.text = item.status
                tvLayananTambal.text = item.name
            }
        }
    }
    fun setOnclickItem(listener: ((OrderModel) -> Unit)?){
        this.itemListener = listener
    }
}