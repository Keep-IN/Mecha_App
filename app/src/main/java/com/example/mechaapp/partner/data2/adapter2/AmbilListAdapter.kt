package com.example.mechaapp.partner.data2.adapter2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mechaapp.databinding.AmbilPesananListBinding
import com.example.mechaapp.databinding.RiwayatTransaksiListBinding
import com.example.mechaapp.partner.data2.AmbilItemModel
import com.example.mechaapp.partner.data2.RiwayatItemModel2

class AmbilListAdapter : RecyclerView.Adapter<AmbilListAdapter.ViewHolder>() {
    private  var itemListener: ((AmbilItemModel) -> Unit)? = null
    private  val data: MutableList<AmbilItemModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AmbilPesananListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(data[position], itemListener)
    }

    override fun getItemCount(): Int = data.size

    fun submitList(list: List<AmbilItemModel>) {
        val initSize = itemCount
        data.clear()
        notifyItemRangeRemoved(0, initSize)
        data.addAll(list)
        notifyItemRangeInserted(0, data.size)
    }

    inner class ViewHolder(private val binding: AmbilPesananListBinding): RecyclerView.ViewHolder(binding.root) {
        fun setData(item: AmbilItemModel, listener: ((AmbilItemModel) -> Unit)?){
            binding.root.setOnClickListener{
                listener?.invoke(item)
            }
            with(binding){
                tvNamaPemesan.text = item.nama
                tvMerkKendaraan.text = item.merk
                tvJenisLayanan.text = item.jenis
                tvHargaLayanan.text = item.harga
            }
        }
    }
    fun setOnclickItem(listener: ((AmbilItemModel) -> Unit)?){
        this.itemListener = listener
    }
}