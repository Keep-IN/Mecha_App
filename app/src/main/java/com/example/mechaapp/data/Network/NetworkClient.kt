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
        private const val BASE_URL = "https://5558-182-2-83-137.ngrok-free.app/api"
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
                .callTimeout(timeout = 5L, unit = TimeUnit.SECONDS)
                .connectTimeout(timeout = 2L, unit = TimeUnit.SECONDS)
                .build()
        }

        fun requestBuilder(endpoint: String, token:String, method: METHOD = METHOD.GET, jsonBody: String? = null): Request {
            val request = Request
                .Builder()
                .url("$BASE_URL$endpoint")
                .header("Get User Data", "Bearer $token")

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

//        fun makeCallApi(endpoint: String, method: METHOD = METHOD.GET, jsonBody: String? = null): Call {
//            val request = requestBuilder(endpoint, method, jsonBody)
//            return client.newCall(request)
//        }
    }

    enum class METHOD {
        GET,
        POST,
        PATCH
    }
}