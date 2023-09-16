package com.example.mechaapp.data.Api

import android.util.Log
import com.example.mechaapp.data.Model.DataToken
import com.example.mechaapp.data.Model.HistoryGetResponse
import com.example.mechaapp.data.Model.OrderGetResponse
import com.example.mechaapp.data.Model.OrderModel
import com.example.mechaapp.data.Model.OrderResponse
import com.example.mechaapp.data.Model.PriceGetResponse
import com.example.mechaapp.data.Model.PriceModel
import com.example.mechaapp.data.Model.PriceResponse
import com.example.mechaapp.data.Model.RegisResponse
import com.example.mechaapp.data.Model.StatusResponse
import com.example.mechaapp.data.Model.TagihanResponse
import com.example.mechaapp.data.Model.UserModel
import com.example.mechaapp.data.Network.NetworkClient
import com.example.mechaapp.data.Network.ResponseStatus
import com.example.mechaapp.data.Network.deserializeJson
import com.example.mechaapp.data.Network.mapFailedResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class OrderAPI {
    private val orderEndpoint = "/orders"
    private val historyEndpoint = "/histories"
    private val updateStatEndpoint = "/update/status"
    private val updateNameEndpoint = "/update/name"
    private val priceEndpointOrder = "/prices/orders/"
    private val priceEndpointHistory = "/prices/histories/"
    private val priceEndpointById = "/prices/histories/users/"
    private val priceEndpointIdService = "/prices"
    private val updateTagihanEndpoint = "/users/update/tagihan"

    fun getAllOrder(onResponse: (ResponseStatus<OrderGetResponse?>)-> Unit){
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
                    Log.d("Response", "Status Code: ${response.code} Msg: ${response.body.toString()}")
                    if (response.isSuccessful){
                        val data = deserializeJson<OrderGetResponse>(response.body?.string() ?: "") ?: OrderGetResponse(0)
                        onResponse.invoke(ResponseStatus.Success(
                            data = data,
                            method = "GET",
                            status = true
                        ))
                    } else {
                        onResponse.invoke(
                            mapFailedResponse(response)
                        )
                    }
                    response.body?.close()
                }

            })
    }

    fun getOrderById(id: String, onResponse: (ResponseStatus<OrderGetResponse?>) -> Unit){
        val request = NetworkClient.requestById(historyEndpoint, DataToken.token, DataToken.idUser)
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
                    val data = deserializeJson<OrderGetResponse>(response.body?.string() ?: "") ?: OrderGetResponse(0)
                    if (response.isSuccessful){
                        onResponse.invoke(ResponseStatus.Success(
                            data = data,
                            method = "GET",
                            status = true
                        ))
                    } else {
                        onResponse.invoke(
                            mapFailedResponse(response)
                        )
                    }
                    response.body?.close()
                }

            })
    }

    fun getHistory(id: String,onResponse: (ResponseStatus<HistoryGetResponse?>)-> Unit){
        val request = NetworkClient.requestById(historyEndpoint, DataToken.token, DataToken.idUser)
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
                    if (response.isSuccessful){
                        val data = deserializeJson<HistoryGetResponse>(response.body?.string() ?: "") ?: HistoryGetResponse(0)
                        onResponse.invoke(ResponseStatus.Success(
                            data = data,
                            method = "GET",
                            status = true
                        ))
                    } else {
                        onResponse.invoke(
                            mapFailedResponse(response)
                        )
                    }
                    response.body?.close()
                }

            })
    }

    fun getPrice(id_service: String, onResponse: (ResponseStatus<PriceGetResponse?>) -> Unit){
        val request = NetworkClient.requestById(priceEndpointIdService, DataToken.token, id_service)
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
                    if (response.isSuccessful){
                        val data = deserializeJson<PriceGetResponse>(response.body?.string() ?: "") ?: PriceGetResponse(0, "")
                        onResponse.invoke(ResponseStatus.Success(
                            data = data,
                            method = "GET",
                            status = true
                        ))
                    } else {
                        onResponse.invoke(
                            mapFailedResponse(response)
                        )
                    }
                    response.body?.close()
                }

            })
    }

    fun getPriceId(id: String, id_service: String, onResponse: (ResponseStatus<PriceGetResponse?>) -> Unit){
        val request = NetworkClient.getPriceById(priceEndpointIdService, DataToken.token, id, id_service)
        NetworkClient
            .client.newCall(request)
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
                    if (response.isSuccessful){
                        val data = deserializeJson<PriceGetResponse>(response.body?.string() ?: "") ?: PriceGetResponse(0, "")
                        onResponse.invoke(ResponseStatus.Success(
                            data = data,
                            method = "GET",
                            status = true
                        ))
                    }else {
                        onResponse.invoke(
                            mapFailedResponse(response)
                        )
                    }
                    response.body?.close()
                }

            })
    }

    fun postOrder(service: String, status: String, address: String, mapUrl: String, onResponse: (ResponseStatus<OrderResponse?>) -> Unit){
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
                    Log.d("Response", "Status Code: ${response.code}")
                   if(response.isSuccessful){
                       try{
                           val data = deserializeJson(response.body?.string() ?: "") ?: OrderResponse()
                           onResponse.invoke(
                               ResponseStatus.Success(
                                   data = data,
                                   method = "POST",
                                   status = true
                               )
                           )
                       } catch (e: JsonDataException){
                           Log.e("Response", "JSON Parsing Error: ${e.message}")
                           onResponse.invoke(
                               ResponseStatus.Failed(
                                   code = -1,
                                   message = "Failed to parse JSON response",
                                   throwable = e
                               )
                           )
                       }
                   } else {
                       onResponse.invoke(
                           mapFailedResponse(response)
                       )
                   }
                    response.body?.close()
                }
            })
    }

    fun postHistory (name:String, service: String, status: String, address: String, mapUrl: String, id_service: String, onResponse: (ResponseStatus<OrderResponse?>) -> Unit) {
        val request = NetworkClient.requestHistory(historyEndpoint, DataToken.token, name, service, status, address, mapUrl, id_service)
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
                    Log.d("Response", "Status Code: ${response.code}")
                    if(response.isSuccessful){
                        try{
                            val data = deserializeJson(response.body?.string() ?: "") ?: OrderResponse()
                            onResponse.invoke(
                                ResponseStatus.Success(
                                    data = data,
                                    method = "POST",
                                    status = true
                                )
                            )
                        } catch (e: JsonDataException){
                            Log.e("Response", "JSON Parsing Error: ${e.message}")
                            onResponse.invoke(
                                ResponseStatus.Failed(
                                    code = -1,
                                    message = "Failed to parse JSON response",
                                    throwable = e
                                )
                            )
                        }
                    } else {
                        onResponse.invoke(
                            mapFailedResponse(response)
                        )
                    }
                    response.body?.close()
                }
            })
    }

    fun delOrder(id: String, onResponse: (ResponseStatus<OrderResponse?>)-> Unit){
        val request = NetworkClient.deleteRequest(orderEndpoint, DataToken.token, id)
        NetworkClient
            .client
            .newCall(request)
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
                    Log.d("Response", "Status Code: ${response.code} Msg: ${response.body.toString()}")
                    if (response.isSuccessful) {
                        onResponse.invoke(
                            ResponseStatus.Success(
                                data = null,
                                method = "DELETE",
                                status = true
                            )
                        )
                    } else {
                        onResponse.invoke(
                            mapFailedResponse(response)
                        )
                    }
                    response.body?.close()
                }

            })
    }

    fun postPriceOrder(id_service: String, desc: String, price: String, onResponse: (ResponseStatus<PriceResponse?>) -> Unit){
        val request = NetworkClient.requestPrice(priceEndpointOrder, DataToken.token, id_service, desc, price)
        NetworkClient
            .client
            .newCall(request)
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
                    if(response.isSuccessful){
                        val data = deserializeJson(response.body?.string() ?: "") ?: PriceResponse("")
                        onResponse.invoke(
                            ResponseStatus.Success(
                                data = data,
                                method = "POST",
                                status = true
                            )
                        )
                    } else {
                        onResponse.invoke(
                            mapFailedResponse(response)
                        )
                    }
                    response.body?.close()
                }

            })
    }

    fun postPriceHistory(id_service: String, desc: String, price: String, onResponse: (ResponseStatus<PriceResponse?>) -> Unit){
        val request = NetworkClient.requestPrice(priceEndpointHistory, DataToken.token, id_service, desc, price)
        NetworkClient
            .client
            .newCall(request)
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
                    Log.d("Response", "Status Code: ${response.code}")
                    if(response.isSuccessful){
                        val data = deserializeJson(response.body?.string() ?: "") ?: PriceResponse("")
                        onResponse.invoke(
                            ResponseStatus.Success(
                                data = data,
                                method = "POST",
                                status = true
                            )
                        )
                    } else {
                        onResponse.invoke(
                            mapFailedResponse(response)
                        )
                    }
                    response.body?.close()
                }

            })
    }

    fun postPriceById(id: String, desc: String, price: String, onResponse: (ResponseStatus<PriceResponse?>) -> Unit){
        val request = NetworkClient.requestPrice(priceEndpointHistory, DataToken.token, id, desc, price)
        NetworkClient
            .client
            .newCall(request)
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
                    Log.d("Response", "Status Code: ${response.code}")
                    if(response.isSuccessful){
                        val data = deserializeJson(response.body?.string() ?: "") ?: PriceResponse("")
                        onResponse.invoke(
                            ResponseStatus.Success(
                                data = data,
                                method = "POST",
                                status = true
                            )
                        )
                    } else {
                        onResponse.invoke(
                            mapFailedResponse(response)
                        )
                    }
                    response.body?.close()
                }

            })
    }

    fun postPriceByName(id: String, desc: String, price: String, onResponse: (ResponseStatus<PriceResponse?>) -> Unit){
        val request = NetworkClient.requestPrice(priceEndpointById, DataToken.token, id, desc, price)
        NetworkClient
            .client
            .newCall(request)
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
                    Log.d("Response", "Status Code: ${response.code}")
                    if(response.isSuccessful){
                        val data = deserializeJson(response.body?.string() ?: "") ?: PriceResponse("")
                        onResponse.invoke(
                            ResponseStatus.Success(
                                data = data,
                                method = "POST",
                                status = true
                            )
                        )
                    } else {
                        onResponse.invoke(
                            mapFailedResponse(response)
                        )
                    }
                    response.body?.close()
                }

            })
    }

    fun updateStatus(status: String, id_service: String, onResponse: (ResponseStatus<StatusResponse?>) -> Unit){
        val request = NetworkClient.updateRequest("${historyEndpoint}${updateStatEndpoint}", DataToken.token, id_service, status)
        NetworkClient
            .client
            .newCall(request)
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
                    if(response.isSuccessful){
                        val data = deserializeJson(response.body?.string() ?: "") ?: StatusResponse(0, "")
                        onResponse.invoke(
                            ResponseStatus.Success(
                                data = data,
                                method = "PUT",
                                status = true
                            )
                        )
                    } else {
                        onResponse.invoke(
                            mapFailedResponse(response)
                        )
                    }
                    response.body?.close()
                }

            })
    }

    fun updateNameHistory(name: String, id: String, id_service:String, onResponse: (ResponseStatus<StatusResponse?>) -> Unit){
        val request = NetworkClient.updateReqName("${historyEndpoint}${updateNameEndpoint}", DataToken.token, name, id, id_service)
        NetworkClient
            .client
            .newCall(request)
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
                    if(response.isSuccessful){
                        val data = deserializeJson(response.body?.string() ?: "") ?: StatusResponse(0, "")
                        onResponse.invoke(
                            ResponseStatus.Success(
                                data = data,
                                method = "PUT",
                                status = true
                            )
                        )
                    } else {
                        onResponse.invoke(
                            mapFailedResponse(response)
                        )
                    }
                    response.body?.close()
                }

            })
    }
    fun updateTagihan(id: String, tagihan: String, onResponse: (ResponseStatus<TagihanResponse?>) -> Unit){
        val request = NetworkClient.updateTagihan(updateTagihanEndpoint, DataToken.token, id, tagihan)
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
                    if (response.isSuccessful){
                        val data = deserializeJson(response.body?.string() ?: "") ?: TagihanResponse(0, "", "")
                        onResponse.invoke(
                            ResponseStatus.Success(
                                data = data,
                                method = "PUT",
                                status = true
                            )
                        )
                    } else{
                        onResponse.invoke(
                            mapFailedResponse(response)
                        )
                    }
                    response.body?.close()
                }

            })
    }
}