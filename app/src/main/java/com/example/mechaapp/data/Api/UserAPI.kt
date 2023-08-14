package com.example.mechaapp.data.Api

import com.example.mechaapp.data.Model.DataToken
import com.example.mechaapp.data.Model.LoginResponse
import com.example.mechaapp.data.Model.UserList
import com.example.mechaapp.data.Model.UserModel
import com.example.mechaapp.data.Network.NetworkClient
import com.example.mechaapp.data.Network.ResponseStatus
import com.example.mechaapp.data.Network.deserializeJson
import com.example.mechaapp.data.Network.mapFailedResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class UserAPI {
    private val userEndpoint = "/users"
    private val loginEndpoint = "/login"
    private val registerEndpoint = "/register"
    private val tokenEndpoint = "/users/token"

    fun getUser(onResponse: (ResponseStatus<List<UserModel>?>) -> Unit){
        val request = NetworkClient.getWithBearerToken(tokenEndpoint, DataToken.token)
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
                        val data = deserializeJson<UserList>(response.body?.string() ?: "") ?: UserList(404)
                        onResponse.invoke(
                            ResponseStatus.Success(
                                data = data.user,
                                method = "GET",
                                status = true
                            )
                        )
                    }
                    response.body?.close()
                }

            })
    }

    fun loginUser(email:String, password: String, onResponse: (ResponseStatus<LoginResponse?>) -> Unit){
        val request = NetworkClient.requestLogin(loginEndpoint, email, password)
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
                        val moshi = Moshi.Builder().build()
                        val adapter: JsonAdapter<LoginResponse> = moshi.adapter(LoginResponse::class.java)
                        val user = adapter.fromJson(response.body?.string() ?: "")
                        onResponse.invoke(
                            ResponseStatus.Success(
                                data = user,
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
}