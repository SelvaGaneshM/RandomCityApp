package com.selvaganesh.randomcityapp.common.utils

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor(private val pref: SharedPrefs) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = pref.getToken()
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", token)
            .addHeader("staffId", pref.getStaffId())
            .addHeader("staffProfileId", pref.getStaffProfileId())
            .addHeader("staffRole", pref.getStaffRole())
            .build()
        return chain.proceed(newRequest)
    }
}