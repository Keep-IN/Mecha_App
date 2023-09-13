package com.example.mechaapp.data.Network

import io.bitfactory.pincodelayout.BuildConfig
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class NetworkClient {
    companion object {
        private const val BASE_URL = "https://68c3-180-252-117-142.ngrok-free.app/api"
        private val headerInterceptor: Interceptor = Interceptor {
            val request = it.request().newBuilder()
            request
                .addHeader("Content-Type", "application/json")

            return@Interceptor it.proceed(request.build())

        }

        val client: OkHttpClient by lazy {
            OkHttpClient
                .Builder()
                .addInterceptor(headerInterceptor)
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level =
                            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                    }
                )
                .callTimeout(timeout = 8L, unit = TimeUnit.SECONDS)
                .connectTimeout(timeout = 4L, unit = TimeUnit.SECONDS)
                .build()
        }

        fun requestBuilder(endpoint: String, method: METHOD = METHOD.GET, jsonBody: String? = null): Request {
            val request = Request
                .Builder()
                .url("$BASE_URL$endpoint")

            if (jsonBody != null)
                request.method(method.name, jsonBody.toRequestBody())

            return request.build()
        }

        fun getWithBearerToken(endpoint: String, token:String, method: METHOD = METHOD.GET, jsonBody: String? = null): Request {
            val request = Request.Builder()
                .url("$BASE_URL$endpoint")
                .header("Authorization", "Bearer $token")
                .get()

            if (jsonBody != null)
                request.method(method.name, jsonBody.toRequestBody())

            return request.build()
        }

        fun requestById(endpoint: String, token:String, id: String, method: METHOD = METHOD.GET, jsonBody: String? = null): Request{
            val request = Request.Builder()
                .url("$BASE_URL$endpoint/$id")
                .header("Authorization", "Bearer $token")
                .get()

            if (jsonBody != null)
                request.method(method.name, jsonBody.toRequestBody())

            return request.build()
        }

        fun requestLogin(endpoint: String,email: String, password: String, method: METHOD = METHOD.POST, jsonBody: String? = null): Request {
            val requestBody = FormBody.Builder()
                .add("email", email)
                .add("password", password)
                .build()
            val request = Request.Builder()
                .url("$BASE_URL$endpoint")
                .post(requestBody)

            if(jsonBody != null){
                request.method(method.name, jsonBody.toRequestBody())
            }

            return request.build()
        }

        fun requestResgis(endpoint: String, nama: String, no_telp: String, email: String, password: String, method: METHOD = METHOD.POST, jsonBody: String? = null): Request{
            val requestBody = FormBody.Builder()
                .add("name", nama)
                .add("no_telp", no_telp)
                .add("email", email)
                .add("password", password)
                .build()
            val request = Request.Builder()
                .url("$BASE_URL$endpoint")
                .post(requestBody)

            if (jsonBody != null){
                request.method(method.name, jsonBody.toRequestBody())
            }

            return request.build()
        }

        // TODO: ADD ORDER REQUEST BODY WITH ORDER ITEM MODEL
        fun postWithBearerToken (endpoint: String, token:String, method: METHOD = METHOD.POST, jsonBody: String? = null): Request {
            val requestBody = FormBody.Builder()
                .build()
            val request = Request.Builder()
                .url("$BASE_URL$endpoint")
                .header("Order_token", "Bearer $token")
                .post(requestBody)

            if (jsonBody != null)
                request.method(method.name, jsonBody.toRequestBody())

            return request.build()
        }

        fun requestOrder(endpoint: String, token: String, service: String, status: String, address: String, mapUrl: String, method: METHOD = METHOD.POST, jsonBody: String? = null): Request{
            val requestBody = FormBody.Builder()
                .add("name_service", service)
                .add("status", status)
                .add("address", address)
                .add("map_url", mapUrl)
                .build()
            val request = Request.Builder()
                .url("$BASE_URL$endpoint")
                .header("Authorization", "Bearer $token")
                .post(requestBody)

            if (jsonBody != null)
                request.method(method.name, jsonBody.toRequestBody())

            return request.build()
        }

        fun requestHistory(endpoint: String, token: String, name: String, service: String, status: String, address: String, mapUrl: String, id_service: String, method: METHOD = METHOD.POST, jsonBody: String? = null): Request{
            val requestBody = FormBody.Builder()
                .add("name", name)
                .add("name_service", service)
                .add("status", status)
                .add("address", address)
                .add("map_url", mapUrl)
                .add("id_service", id_service)
                .build()
            val request = Request.Builder()
                .url("$BASE_URL$endpoint")
                .header("Authorization", "Bearer $token")
                .post(requestBody)

            if (jsonBody != null)
                request.method(method.name, jsonBody.toRequestBody())

            return request.build()
        }

        fun updateReqName(endpoint: String, token: String, name: String, id: String, method:METHOD = METHOD.PUT, jsonBody: String? = null): Request{
            val requestBody = FormBody.Builder()
                .add("name", name)
                .build()
            val request = Request.Builder()
                .url("$BASE_URL$endpoint/$id")
                .header("Authorization", "Bearer $token")
                .put(requestBody)
            if (jsonBody != null)
                request.method(method.name, jsonBody.toRequestBody())

            return request.build()
        }

        fun updateRequest(endpoint: String, token: String, status: String, id_service: String, method: METHOD = METHOD.PUT, jsonBody: String? = null): Request{
            val requestBody = FormBody.Builder()
                .add("status", status)
                .build()
            val request = Request.Builder()
                .url("$BASE_URL$endpoint/$id_service")
                .header("Authorization", "Bearer $token")
                .put(requestBody)
            if (jsonBody != null)
                request.method(method.name, jsonBody.toRequestBody())

            return request.build()
        }

        fun requestPrice(endpoint: String, token: String, id_service: String, desc: String, price: String, method: METHOD = METHOD.POST, jsonBody: String? = null): Request{
            val requestBody = FormBody.Builder()
                .add("description_service", desc)
                .add("price", price)
                .build()
            val request = Request.Builder()
                .url("$BASE_URL$endpoint$id_service")
                .header("Authorization", "Bearer $token")
                .post(requestBody)
            if(jsonBody != null)
                request.method(method.name, jsonBody.toRequestBody())

            return request.build()
        }

        fun deleteRequest(endpoint: String, token: String, id: String, method: METHOD = METHOD.DELETE, jsonBody: String? = null): Request{
            val request = Request.Builder()
                .url("$BASE_URL$endpoint/$id")
                .delete()
                .header("Authorization", "Bearer $token")

            return request.build()
        }

//        fun makeCallApi(endpoint: String, method: METHOD = METHOD.GET, jsonBody: String? = null): Call {
//            val request = requestBuilder(endpoint, method, jsonBody)
//            return client.newCall(request)
//        }
    }

    enum class METHOD {
        GET,
        POST,
        DELETE,
        PUT
    }
}