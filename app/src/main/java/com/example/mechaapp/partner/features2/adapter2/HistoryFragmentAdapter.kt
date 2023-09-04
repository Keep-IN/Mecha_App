package com.example.mechaapp.partner.features2.adapter2

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mechaapp.databinding.HistoryListView2Binding
import com.example.mechaapp.partner.features2.data2.RiwayatFragmentItemModel

class HistoryFragmentAdapter: RecyclerView.Adapter<HistoryFragmentAdapter.ViewHolder>() {
    private  var itemListener: ((RiwayatFragmentItemModel) -> Unit)? = null
    private  val data: MutableList<RiwayatFragmentItemModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            HistoryListView2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(data[position], itemListener)
    }

    override fun getItemCount(): Int = data.size

    fun submitList(list: List<RiwayatFragmentItemModel>) {
        val initSize = itemCount
        data.clear()
        notifyItemRangeRemoved(0, initSize)
        data.addAll(list)
        notifyItemRangeInserted(0, data.size)
    }

    inner class ViewHolder(private val binding: HistoryListView2Binding): RecyclerView.ViewHolder(binding.root) {
        fun setData(item: RiwayatFragmentItemModel, listener: ((RiwayatFragmentItemModel) -> Unit)?){
            when (item.status) {
                "Dijadwalkan" -> {
                    binding.cvStatusHistory.setCardBackgroundColor(Color.parseColor("#358F80"))
                }
                "Dibatalkan" -> {
                    binding.cvStatusHistory.setCardBackgroundColor(Color.parseColor("#E21F30"))
                }
                "Selesai" -> {
                    binding.cvStatusHistory.setCardBackgroundColor(Color.parseColor("#85B804"))
                }
            }
            binding.root.setOnClickListener{
                listener?.invoke(item)
            }
            with(binding){
                tvidpesanFragment.text = "Id pesanan : ${item.id}"
                tvlayananFragment.text = item.layanan
                tvpemesanFragment.text = item.pemesan
                tvtanggalFragment.text = item.date
                tvstatusFragment.text = item.status
            }
        }
    }
}