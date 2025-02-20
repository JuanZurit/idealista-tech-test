package com.juanzurita.framework.remote

import androidx.room.Ignore
import javax.annotation.Nonnull

sealed class DTO<T> {
    @Nonnull
    /**
     * Convierte este objeto de dominio (database o network)
     * en un objeto de negocio (viewmodel)
     */
    abstract fun convert(): T
}

abstract class NetworkDTO<N> : DTO<N>() {
}

abstract class DatabaseDTO<D>(cacheLifeTime: Long = -1) : DTO<D>() {

    @Ignore
    open val cacheLifetime: Long = cacheLifeTime
    open var createdAt: Long = 0

    /**
     * Comprueba si el objeto almacenado en base de datos ha superado el tiempo de vida de caché.
     *
     * Para que este método funcione correctamente, se necesita cumplir dos condiciones:
     *      1. Al insertar el objeto en ROOM, establecer la variable createdAt con System.currentTimeMillis()
     *      2. Definir el tiempo de vida util a la variable cacheLifeTime, de la siguiente forma: TimeUnit.MINUTES.toMillis(5)
     *      Alternativamente, se puede pasar un valor indicando el tiempo de vida de la caché por parámetro
     *
     * @param cacheTime Indica la duración de la caché que tiene el objeto.
     *
     * @return Boleano indicando si la caché ha expirado
     *
     * Ejemplo:
     *
     * myCar.isCacheExpired()
     */
    fun isCacheExpired(): Boolean {
        // obtenemos el tiempo que lleva este objeto almacenado en room
        val storeLifetime = System.currentTimeMillis() - createdAt

        // comprobamos si ha estado almacenado más tiempo que el tiempo límite de cache
        return (storeLifetime > cacheLifetime)
    }

}

/**
 * Extensión: convierte una lista de objetos de dominio (ej: List<AdItemNetwork>) nullable
 * a una lista de objeto de negocio (ej: List<AdItem>)
 */
fun <T> List<DTO<T>?>.convert(): List<T> {
    return filterNotNull().map { it.convert() }
}

