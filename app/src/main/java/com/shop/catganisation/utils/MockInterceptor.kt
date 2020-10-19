package com.shop.catganisation.utils

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

/**
 * Created by Ovidiu Florin Luca on 19/10/2020.
 */

const val loginResponse = """
{"token":"e51009b2-ce0a-471b-bf44-1f97ab68be0c"}
"""

class MockInterceptor : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {

        var response: Response

        val uri = chain.request().url.toUri().toString()
        if (uri.endsWith("login")) {

            val responseString = loginResponse

            response = chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(
                    responseString.toByteArray()
                        .toResponseBody("application/json".toMediaTypeOrNull())
                )
                .addHeader("content-type", "application/json")
                .build()
        } else {
            //just to be on safe side.
            response = chain.proceed(chain.request());
        }

        return response
    }
}