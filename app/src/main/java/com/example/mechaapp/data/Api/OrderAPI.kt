package com.example.mechaapp.data.Api

import com.example.mechaapp.data.Model.DataToken
import com.example.mechaapp.data.Model.OrderModel
import com.example.mechaapp.data.Model.OrderResponse
import com.example.mechaapp.data.Model.RegisResponse
import com.example.mechaapp.data.Model.UserModel
import com.example.mechaapp.data.Network.NetworkClient
import com.example.mechaapp.data.Network.ResponseStatus
import com.example.mechaapp.data.Network.deserializeJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class OrderAPI {
    private val orderEndpoint = "/orders"

    fun getOrder(onResponse: (ResponseStatus<List<OrderModel>>)-> Unit){
        val request = NetworkClient.getWithBearerToken(orderEndpoint, DataToken.token)
        NetworkClient
            .client.newCall(request)
            .enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    onResponse.invoke(
                        ResponseStatus.Failed(
                            code = -1,
                            message = e.message.toString(),
                            throwable = e
                        )
                    )
                }

                override fun onResponse(call: Call, response: Response) {
                    val data = deserializeJson<UserModel>(response.body?.string() ?: "")
                    if (response.isSuccessful){
                        if (data != null) {
                            onResponse.invoke(ResponseStatus.Success(
                                data = data.order,
                                method = "GET",
                                status = true
                            ))
                        }
                    }
                }

            })
    }

    fun postOrder(service: String, status: String, address: String, mapUrl:String, onResponse: (ResponseStatus<OrderResponse?>) -> Unit){
        val request = NetworkClient.requestOrder(orderEndpoint, DataToken.token, service, status, address, mapUrl)
        NetworkClient
            .client
            .newCall(request)
            .enqueue(object: Callback{
                override fun onFailure(call: Call, e: IOException) {
                    onResponse.invoke(
                        ResponseStatus.Failed(
                            code = -1,
                            message = e.message.toString(),
                            throwable = e
                        )
                    )
                }

                override fun onResponse(call: Call, response: Response) {
                   if(response.isSuccessful){
                       val moshi = Moshi.Builder().build()
                       val adapter: JsonAdapter<OrderResponse> = moshi.adapter(OrderResponse::class.java)
                       val data = adapter.fromJson(response.body?.string() ?: "")
                       onResponse.invoke(
                           ResponseStatus.Success(
                               data = data,
                               method = "POST",
                               status = true
                           )
                       )
                   }
                }

            })
    }
}