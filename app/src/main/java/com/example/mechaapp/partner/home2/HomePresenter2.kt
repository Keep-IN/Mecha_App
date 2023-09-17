package com.example.mechaapp.partner.home2

import com.example.mechaapp.data.Api.OrderAPI
import com.example.mechaapp.data.Network.ResponseStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class HomePresenter2(
    private val view: HomeContract2,
    private val api: OrderAPI,
    uiContext: CoroutineContext = Dispatchers.Main
) {
    private val supervisorJob: Job = SupervisorJob()
    private val scope = CoroutineScope(supervisorJob + uiContext)
    fun onAttach(view: HomeContract2){
        this.view
    }
    fun getHistory(id: String){
        api.getHistory(id){
            when(it){
                is ResponseStatus.Success -> view.onSucces(it.data)
                is ResponseStatus.Failed -> view.onFailed(it.message)
            }
        }
    }

    fun updateTagihan(id: String, tagihan: String){
        api.updateTagihan(id, tagihan){
            when(it){
                is ResponseStatus.Success -> view.onSuccesUpdate(it.data)
                is ResponseStatus.Failed -> view.onFailed(it.message)
            }
        }
    }
}