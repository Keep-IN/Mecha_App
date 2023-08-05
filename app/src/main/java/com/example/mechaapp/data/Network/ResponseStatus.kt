package com.example.mechaapp.data.Network

sealed class ResponseStatus<out T> {
    data class Failed(
        val code: Int,
        val message: String,
        val throwable: Throwable? = null): ResponseStatus<Nothing>()

    data class Success<out T>(
        val data: T,
        val method: String = "",
        val status: Boolean = true): ResponseStatus<T>()
}