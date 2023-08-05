package com.example.mechaapp.partner.features2.adapter2

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mechaapp.databinding.FragmentHome2Binding
import com.example.mechaapp.databinding.RiwayatTransaksiListBinding
import com.example.mechaapp.partner.features2.data2.RiwayatItemModel2

class HistoryListAdapter2 : RecyclerView.Adapter<HistoryListAdapter2.ViewHolder>() {
    private  var itemListener: ((RiwayatItemModel2) -> Unit)? = null
    private  val data: MutableList<RiwayatItemModel2> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RiwayatTransaksiListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(data[position], itemListener)
    }

    override fun getItemCount(): Int = data.size

    fun submitList(list: List<RiwayatItemModel2>) {
        val initSize = itemCount
        data.clear()
        notifyItemRangeRemoved(0, initSize)
        data.addAll(list)
        notifyItemRangeInserted(0, data.size)
    }

    inner class ViewHolder(private val binding: RiwayatTransaksiListBinding): RecyclerView.ViewHolder(binding.root) {
        fun setData(item: RiwayatItemModel2, listener: ((RiwayatItemModel2) -> Unit)?){
            binding.root.setOnClickListener{
                listener?.invoke(item)
            }
            with(binding){
                tvJudulStatus.text = item.judul
                tvIsiStatus.text = item.isistatus
                tvTanggalOrderan.text = item.tanggalorderan
                tvHargaOrderan.text = item.hargaorderan
            }
        }
    }
    fun setOnclickItem(listener: ((RiwayatItemModel2) -> Unit)?){
        this.itemListener = listener
    }
}
