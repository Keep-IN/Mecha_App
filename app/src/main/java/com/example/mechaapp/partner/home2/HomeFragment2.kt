package com.example.mechaapp.partner.home2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechaapp.data.Api.OrderAPI
import com.example.mechaapp.databinding.FragmentHome2Binding
import com.example.mechaapp.data.adapter.HistoryListAdapter2
import com.example.mechaapp.partner.features2.ambilpesan.AmbilPesan
import com.example.mechaapp.data.Model.DataRiwayatMontir
import com.example.mechaapp.data.Model.DataToken
import com.example.mechaapp.data.Model.DataUser
import com.example.mechaapp.data.Model.HistoryGetResponse
import com.example.mechaapp.data.Model.TagihanResponse

class HomeFragment2 : Fragment(), HomeContract2 {
    private lateinit var binding: FragmentHome2Binding
    private lateinit var presenter: HomePresenter2
    private val adapterHistory: HistoryListAdapter2 by lazy { HistoryListAdapter2() }
    private var sum: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHome2Binding.inflate(layoutInflater, container, false)
        binding.cvambilPesan.setOnClickListener {
            startActivity(Intent(activity, AmbilPesan::class.java ))
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = HomePresenter2(this, OrderAPI()).apply {
            onAttach(this@HomeFragment2)
        }
        val layoutManager = LinearLayoutManager(activity)
        adapterHistory.submitList(DataRiwayatMontir.riwayathomeList)
        binding.rvMekanik.adapter = adapterHistory
        binding.rvMekanik.layoutManager = layoutManager
        binding.tvNamamekanik.text = DataUser.nama
        presenter.getHistory(DataToken.idUser)
    }

    override fun onSucces(history: HistoryGetResponse?) {
        history?.history?.forEach { it ->
            if (it.status == "Selesai"){
                it.prices.forEach {price ->
                    if (price.description_service == "Biaya Pemakaian Aplikasi"){
                        sum += price.price.toInt()
                        presenter.updateTagihan(DataToken.userId, sum.toString())
                    }
                }
            }
        }
    }

    override fun onFailed(msg: String) {
        Log.d("Error Update", "Gagal tagihan")
    }

    override fun onSuccesUpdate(tagihan: TagihanResponse?) {
        if (tagihan != null) {
            Handler(requireActivity().mainLooper).post{
                binding.tvtagihan.text = "Rp ${sum.formatDecimalSeparator()}"
            }
        }
    }
    fun Int.formatDecimalSeparator(): String {
        return toString()
            .reversed()
            .chunked(3)
            .joinToString(".")
            .reversed()
    }

}