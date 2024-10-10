package com.selvaganesh.randomcityapp.common.utils

import com.google.gson.annotations.SerializedName

data class WrappedListResponse<T>(
    var code: Int,
    @SerializedName("message") var message: String,
    @SerializedName("status") var status: Boolean,
    @SerializedName("errors") var errors: List<ErrorData>? = null,
    @SerializedName("data") var data: List<T>? = null
)


data class WrappedResponse<T>(
    @SerializedName("status") var codes: String,
    @SerializedName("success") var success: Boolean,
    @SerializedName("developerMessage") var developerMessage: String,
    @SerializedName("userMessage") var userMessage: String,
    @SerializedName("errors") var errors: List<ErrorData>? = null,
    @SerializedName("results") var data: T? = null
)