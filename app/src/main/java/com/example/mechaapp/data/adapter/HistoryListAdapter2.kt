package com.example.mechaapp.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mechaapp.data.Model.PriceModel
import com.example.mechaapp.databinding.RiwayatTransaksiListBinding
import com.example.mechaapp.data.Model.RiwayatItemModel2

class HistoryListAdapter2 : RecyclerView.Adapter<HistoryListAdapter2.ViewHolder>() {
    private  var itemListener: ((PriceModel) -> Unit)? = null
    private  val data: MutableList<PriceModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RiwayatTransaksiListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(data[position], itemListener)
    }

    override fun getItemCount(): Int = data.size

    fun submitList(list: List<PriceModel>) {
        val initSize = itemCount
        data.clear()
        notifyItemRangeRemoved(0, initSize)
        data.addAll(list)
        notifyItemRangeInserted(0, data.size)
    }

    inner class ViewHolder(private val binding: RiwayatTransaksiListBinding): RecyclerView.ViewHolder(binding.root) {
        fun setData(item: PriceModel, listener: ((PriceModel) -> Unit)?){
            binding.root.setOnClickListener{
                listener?.invoke(item)
            }
            with(binding){
                tvJudulStatus.text = item.description_service
                tvHargaOrderan.text = "Rp ${item.price}"
            }
        }
    }
    fun setOnclickItem(listener: ((PriceModel) -> Unit)?){
        this.itemListener = listener
    }
}
