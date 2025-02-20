package com.juanzurita.core.domain.models

sealed interface Error {
    class Server(val code: Int) : Error
    object Connectivity : Error
    object NullableData : Error
    class Unknown(val message: String) : Error

}