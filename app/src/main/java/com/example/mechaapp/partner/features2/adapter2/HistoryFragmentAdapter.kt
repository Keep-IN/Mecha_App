package com.example.mechaapp.partner.features2.adapter2

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mechaapp.data.Model.OrderModel
import com.example.mechaapp.databinding.HistoryListView2Binding
import com.example.mechaapp.partner.features2.data2.RiwayatFragmentItemModel

class HistoryFragmentAdapter: RecyclerView.Adapter<HistoryFragmentAdapter.ViewHolder>() {
    private  var itemListener: ((OrderModel) -> Unit)? = null
    private  val data: MutableList<OrderModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            HistoryListView2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    inner class ViewHolder(private val binding: HistoryListView2Binding): RecyclerView.ViewHolder(binding.root) {
        fun setData(item: OrderModel, listener: ((OrderModel) -> Unit)?){
            when (item.status) {
                "Diterima" -> {
                    binding.cvStatusHistory.setCardBackgroundColor(Color.parseColor("#1BCABB"))
                }
                "Dibatalkan" -> {
                    binding.cvStatusHistory.setCardBackgroundColor(Color.parseColor("#F3887E"))
                }
                "Selesai" -> {
                    binding.cvStatusHistory.setCardBackgroundColor(Color.parseColor("#67E785"))
                }
                "Menunggu" -> {
                    binding.cvStatusHistory.setCardBackgroundColor(Color.parseColor("#D1CA49"))
                }
            }
            binding.root.setOnClickListener{
                listener?.invoke(item)
            }
            with(binding){
                tvidpesanFragment.text = "Id pesanan : ${item.id_service}"
                tvlayananFragment.text = item.name_service
                tvpemesanFragment.text = item.name
                tvtanggalFragment.text = item.created_at
                tvstatusFragment.text = item.status
            }
        }
    }
    fun setOnclickItem(listener: ((OrderModel) -> Unit)?){
        this.itemListener = listener
    }
}