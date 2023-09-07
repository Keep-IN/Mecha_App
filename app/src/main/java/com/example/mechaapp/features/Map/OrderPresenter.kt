package com.example.mechaapp.features.Map

import com.example.mechaapp.data.Api.OrderAPI
import com.example.mechaapp.data.Network.ResponseStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class OrderPresenter(
    private  val view: OrderContract,
    private val api: OrderAPI,
    uiContext: CoroutineContext = Dispatchers.Main
) {
    private val supervisorJob: Job = SupervisorJob()
    private val scope = CoroutineScope(supervisorJob + uiContext)
    fun onAttach(view: OrderContract){
        this.view
    }
    fun postOrder(service: String, status: String, address: String, mapurl: String){
        api.postOrder(service, status, address, mapurl){
            scope.launch {
                when(it){
                    is ResponseStatus.Success -> view.onSuccesOrder(it.data)
                    is ResponseStatus.Failed -> view.onErrorOrder(it.message)
                }
            }
        }
    }
}