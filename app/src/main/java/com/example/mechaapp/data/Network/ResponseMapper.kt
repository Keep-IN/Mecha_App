package com.example.mechaapp.data.Network

import okhttp3.Response
import org.json.JSONObject

fun mapFailedResponse(response: Response): ResponseStatus.Failed {
    val jsonBody = response.body?.string().let { if(!it.isNullOrEmpty()) JSONObject(it) else JSONObject() }
    return ResponseStatus.Failed(response.code, jsonBody.optString("message", ""))
}