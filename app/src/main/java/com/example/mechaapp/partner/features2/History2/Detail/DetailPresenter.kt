package com.example.mechaapp.partner.features2.History2.Detail

import com.example.mechaapp.data.Api.OrderAPI
import com.example.mechaapp.data.Network.ResponseStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailPresenter(
    private val view: DetailContract,
    private val api: OrderAPI,
    uiContext: CoroutineContext = Dispatchers.Main
) {
    private val supervisorJob: Job = SupervisorJob()
    private val scope = CoroutineScope(supervisorJob + uiContext)

    fun onAttach(view: DetailContract){
        this.view
    }

    fun postHistory(service: String, status: String, address: String, mapurl: String, id_service: String){
        api.postHistory(service, status, address, mapurl, id_service){
            scope.launch{
                when(it){
                    is ResponseStatus.Success -> view.onSuccesHistory(it.data)
                    is ResponseStatus.Failed -> view.onErrorHistory(it.message)
                }
            }
        }
    }

    fun updateStatus(status: String, id_service: String){
        api.updateStatus(status, id_service){
            scope.launch {
                when(it){
                    is ResponseStatus.Success -> view.onSucceUpdate(it.data)
                    is ResponseStatus.Failed -> view.onErrorUpdate(it.message)
                }
            }
        }
    }

    fun deleteOrder(id: String){
        api.delOrder(id) {
            scope.launch {
                when(it){
                    is ResponseStatus.Success -> view.onSuccessDelete(it.data)
                    is ResponseStatus.Failed -> view.onErrorDelete(it.message)
                }
            }
        }
    }
}