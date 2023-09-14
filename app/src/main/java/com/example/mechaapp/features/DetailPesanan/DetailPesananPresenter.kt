package com.example.mechaapp.features.DetailPesanan

import com.example.mechaapp.data.Api.OrderAPI
import com.example.mechaapp.data.Network.ResponseStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class DetailPesananPresenter(
    private val view: DetailPesananContract,
    private val api: OrderAPI,
    uiContext: CoroutineContext = Dispatchers.Main
) {
    private val supervisorJob: Job = SupervisorJob()
    private val scope = CoroutineScope(supervisorJob + uiContext)

    fun onAttach(view: DetailPesananContract){
        this.view
    }

    fun getPriceById(id: String, id_service: String){
        api.getPriceId(id, id_service){
            when(it){
                is ResponseStatus.Success -> view.onSuccesGetPrice(it.data)
                is ResponseStatus.Failed -> view.onErrorGetPrice(it.message)
            }
        }
    }
}