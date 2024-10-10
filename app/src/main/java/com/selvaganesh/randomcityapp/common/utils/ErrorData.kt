package com.selvaganesh.randomcityapp.common.utils

data class ErrorData(
    val success: Boolean? = false,
    val code: String? = "",
    val developerMessage: String? = "",
    val userMessage: String? = ""
)
