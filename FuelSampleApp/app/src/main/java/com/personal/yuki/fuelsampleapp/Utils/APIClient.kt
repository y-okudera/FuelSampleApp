package com.personal.yuki.fuelsampleapp

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.util.FuelRouting
import com.github.kittinunf.result.Result

sealed class APIResult<T> {
    data class Success<T>(val value: T): APIResult<T>()
    data class Failure<T>(val reason: Exception): APIResult<T>()
}

object APIClient {

    /**
     * APIリクエスト
     * @param route FuelRouting
     * @param deserializer ResponseDeserializable<T>
     * @param handler (APIResult<T>) -> Unit)
     */
    fun <T : Any> requtest(route: FuelRouting, deserializer: ResponseDeserializable<T>, handler: (APIResult<T>) -> Unit) {
        Fuel.request(route).responseObject(deserializer) { request, response, result ->

            // response handling

            println("Request\n$request")

            when (result) {
                is Result.Success -> {
                    handler(APIResult.Success(result.value))
                }
                is Result.Failure -> {
                    handler(APIResult.Failure(result.getException()))
                }
            }
        }
    }
}