package com.example.mechaapp.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mechaapp.data.Model.DetailMontirModel
import com.example.mechaapp.data.Model.MetodeItemModel
import com.example.mechaapp.databinding.MetodeListViewBinding
import com.example.mechaapp.databinding.PesananmontirViewBinding

class DetailMontirAdapter: RecyclerView.Adapter<DetailMontirAdapter.ViewHolder>() {
    private  var itemListener: ((DetailMontirModel) -> Unit)? = null
    private  val data: MutableList<DetailMontirModel> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailMontirAdapter.ViewHolder {
        return ViewHolder(
            PesananmontirViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun onBindViewHolder(holder: DetailMontirAdapter.ViewHolder, position: Int) {
        holder.setData(data[position], itemListener)
    }
    override fun getItemCount(): Int = data.size

    fun submitList(list: List<DetailMontirModel>) {
        val initSize = itemCount
        data.clear()
        notifyItemRangeRemoved(0, initSize)
        data.addAll(list)
        notifyItemRangeInserted(0, data.size)
    }
    inner class ViewHolder(private val binding: PesananmontirViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun setData(item: DetailMontirModel, listener: ((DetailMontirModel) -> Unit)?){
            binding.root.setOnClickListener{
                listener?.invoke(item)
            }
            with(binding){
                tvJuduldetail.text = item.juduldetail
                tvJudulHarga.text = item.judulharga
            }
        }
    }
}