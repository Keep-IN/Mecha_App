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

    fun postHistory(name: String, service: String, status: String, address: String, mapurl: String, id_service: String){
        api.postHistory(name, service, status, address, mapurl, id_service){
            scope.launch{
                when(it){
                    is ResponseStatus.Success -> view.onSuccesHistory(it.data)
                    is ResponseStatus.Failed -> view.onErrorhistory(it.message)
                }
            }
        }
    }

    fun postPriceOrder(id_service: String, desc: String, price: String){
        api.postPriceOrder(id_service,desc,price){
            scope.launch {
                when(it){
                    is ResponseStatus.Success -> view.onSuccessPrice(it.data)
                    is ResponseStatus.Failed -> view.onErrorPrice(it.message)
                }
            }
        }
    }

    fun postPriceHistory(id_service: String, desc: String, price: String){
        api.postPriceHistory(id_service, desc, price){
            when(it){
                is ResponseStatus.Success -> view.onSuccessPrice(it.data)
                is ResponseStatus.Failed -> view.onErrorPrice(it.message)
            }
        }
    }

    fun getOrderById(id: String){
        api.getOrderById(id){
            scope.launch {
                when(it){
                    is ResponseStatus.Success -> view.onSuccesGetOrder(it.data)
                    is ResponseStatus.Failed -> view.onErrorgetOrder(it.message)
                }
            }
        }
    }
}