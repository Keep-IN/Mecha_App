package com.example.mechaapp.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mechaapp.data.Model.DataPrice
import com.example.mechaapp.data.Model.PriceModel
import com.example.mechaapp.databinding.LayananTambahanViewBinding

class LayananTambahAdapter: RecyclerView.Adapter<LayananTambahAdapter.ViewHolder>()
{
    private var itemListener : ((PriceModel) -> Unit)? = null
    private val data: MutableList<PriceModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayananTambahanViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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


    inner class ViewHolder(private val binding: LayananTambahanViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(item: PriceModel, Listener: ((PriceModel) -> Unit)?) {

            binding.root.setOnClickListener {
                Listener?.invoke(item)
            }
            val etPrice = binding.tilPrice.editText
            val etDesc = binding.tilDesc.editText
            if (item.price.isNotEmpty() && item.description_service.isNotEmpty()){
                binding.tilDesc.editText?.isEnabled = false
                binding.tilPrice.editText?.isEnabled = false
                binding.cvSimpanHarga.visibility = View.GONE
            } else {
                binding.tilDesc.editText?.isEnabled = true
                binding.tilPrice.editText?.isEnabled = true
                binding.cvSimpanHarga.visibility = View.VISIBLE
            }
            binding.tilDesc.editText?.setText(item.description_service)
            binding.tilPrice.editText?.setText(item.price)
            binding.cvSimpanHarga.setOnClickListener {
                binding.cvSimpanHarga.visibility = View.GONE
                binding.tilDesc.editText?.isEnabled = false
                binding.tilPrice.editText?.isEnabled = false
                DataPrice.priceList.add(0,
                    PriceModel(binding.tilPrice.editText?.text.toString(), "", binding.tilDesc.editText?.text.toString())
                )
            }

        }
    }
}
