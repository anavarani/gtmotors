package com.varani.gtmotors.api

sealed class NetworkResult<out T> {
    data class Success<out T>(val value: T) : NetworkResult<T>()
    data class Failure(val throwable: Throwable) : NetworkResult<Nothing>()
}