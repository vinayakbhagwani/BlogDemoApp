package com.demo.data.network.utils

import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest {

    suspend fun <T : Any> safeApiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body() ?: throw ApiException("error")
        } else {
            val responseErr = response.errorBody()?.toString()
            val message = StringBuilder()
            responseErr?.let { errorResBody ->
                try {
                    message.append(JSONObject(errorResBody).getString("error"))
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            Log.d("TAG", "safeApiRequest: $message")
            throw ApiException(message.toString())
        }
    }

}