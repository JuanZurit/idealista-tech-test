package com.juanzurita.core.util.extensions


import android.util.Log
import arrow.core.Either
import arrow.core.left
import arrow.core.right
import java.io.IOException
import com.juanzurita.core.domain.models.Error

fun Throwable.toError(): Error = when (this) {
    is IOException -> Error.Connectivity
    is retrofit2.HttpException -> {
        Log.d("ERROR", code().toString())
        Error.Server(code())
    }

    else -> {
        Log.d("ERROR",message.orEmpty())
        Error.Unknown(message.orEmpty())
    }
}

suspend fun <T> tryCall(action: suspend () -> T): Either<Error, T> = try {
    action().right()
} catch (e: Exception) {
    e.toError().left()
}

val <T> T.exhaustive: T
    get() = this
