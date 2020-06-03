package com.app.whatsapptools.model

sealed class ResultData<out T> {
    data class Loading(val nothing: Nothing? = null): ResultData<Nothing>()
    data class Success<out T>(val data: T): ResultData<T>()
    data class Failed(val errorMessage: String? = null): ResultData<Nothing>()
}