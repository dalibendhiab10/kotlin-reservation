package com.example.mykotlin.api

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.example.mykotlin.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@SuppressLint("StaticFieldLeak")
object ApiService {

    private var context: Context? = null
    private var retrofit: Retrofit? = null
    var api: ApiInterface? = null

    fun initialize(context: Context) {
        this.context = context.applicationContext  // Use application context to avoid memory leaks
    }

    private fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build()
        }
        return retrofit!!
    }

    fun getApiInterface(): ApiInterface {
        if (api == null) {
            api = getRetrofit().create(ApiInterface::class.java)
        }
        return api!!
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .build()
    }

    private class AuthInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val token =Constants.TOKEN
            val request = chain.request().newBuilder().apply {
                token?.let {
                    addHeader("Authorization", "Bearer $it")
                }
            }.build()
            return chain.proceed(request)
        }

    }
}
