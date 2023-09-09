package com.example.mechaapp.partner.features2.ambilpesan

import com.example.mechaapp.data.Api.OrderAPI
import com.example.mechaapp.data.Network.ResponseStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AmbilPesanPresenter(
    private val view: AmbilPesanContract,
    private val api: OrderAPI,
    uiContext: CoroutineContext = Dispatchers.Main
) {
    private val supervisorJob: Job = SupervisorJob()
    private val scope = CoroutineScope(supervisorJob + uiContext)

    fun onAttach(view: AmbilPesanContract){
        this.view
    }

    fun getOrder(){
        api.getAllOrder {
            scope.launch {
                when(it){
                    is ResponseStatus.Success -> view.onSuccessGet(it.data)
                    is ResponseStatus.Failed -> view.onFailedGet(it.message)
                }
            }
        }
    }
}