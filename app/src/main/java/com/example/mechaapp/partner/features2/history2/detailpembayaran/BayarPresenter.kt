package com.example.mechaapp.partner.features2.history2.detailpembayaran

import com.example.mechaapp.data.Api.OrderAPI
import com.example.mechaapp.data.Api.UserAPI
import com.example.mechaapp.data.Network.ResponseStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class BayarPresenter(
    private val view: BayarContract,
    private val api: OrderAPI,
    private val apiUser: UserAPI,
    uiContext: CoroutineContext = Dispatchers.Main
) {
    private val supervisorJob: Job = SupervisorJob()
    private val scope = CoroutineScope(supervisorJob + uiContext)

    fun onAttach(view: BayarContract){
        this.view
    }

    fun postPriceByName(id_service: String, desc: String, price: String){
        api.postPriceByName(id_service, desc, price){
            scope.launch {
                when(it){
                    is ResponseStatus.Success -> view.onSuccesPrice(it.data)
                    is ResponseStatus.Failed -> view.onErrorPrice(it.message)
                }
            }
        }
    }

    fun postPrice(id_service: String, desc: String, price: String){
        api.postPriceById(id_service, desc, price){
            scope.launch {
                when(it){
                    is ResponseStatus.Success -> view.onSuccesPrice(it.data)
                    is ResponseStatus.Failed -> view.onErrorPrice(it.message)
                }
            }
        }
    }

    fun getPriceById(id: String, id_service: String){
        api.getPriceId(id, id_service){
            when(it){
                is ResponseStatus.Success -> view.onSuccesGetPrice(it.data)
                is ResponseStatus.Failed -> view.onErrorGetPrice(it.message)
            }
        }
    }

    fun getAllUser(){
        apiUser.getAllUser {
            scope.launch {
                when(it){
                    is ResponseStatus.Success -> view.onSuccesuser(it.data)
                    is ResponseStatus.Failed -> view.onError(it.message)
                }
            }
        }
    }

    fun updateStatus(status: String, id_service: String){
        api.updateStatus(id_service, status){
            scope.launch{
                when(it){
                    is ResponseStatus.Success -> view.onSuccesUpdate(it.data)
                    is ResponseStatus.Failed -> view.onError(it.message)
                }
            }
        }
    }
}