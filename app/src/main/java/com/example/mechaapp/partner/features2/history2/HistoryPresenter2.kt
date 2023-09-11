package com.example.mechaapp.partner.features2.history2

import com.example.mechaapp.data.Api.OrderAPI
import com.example.mechaapp.data.Network.ResponseStatus
import com.example.mechaapp.features.History.HistoryContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HistoryPresenter2(
    private val view: HistoryContract2,
    private val api: OrderAPI,
    uiContext: CoroutineContext = Dispatchers.Main
) {
    private val supervisorJob: Job = SupervisorJob()
    private val scope = CoroutineScope(supervisorJob + uiContext)
    fun onAttach(view: HistoryContract2){
        this.view
    }
    fun getHistory(id: String){
        api.getHistory(id) {
            scope.launch {
                when(it){
                    is ResponseStatus.Success -> view.onSucces(it.data)
                    is ResponseStatus.Failed -> view.onFailed(it.message)
                }
            }
        }
    }
}