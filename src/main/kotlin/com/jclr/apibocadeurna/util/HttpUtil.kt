package com.jclr.apibocadeurna.util

import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.util.MultiValueMap

fun responseOf(
    code: Int,
    message: String? = null,
    headers: MultiValueMap<String, String>? = null
) = ResponseEntity<String?>(message, headers, HttpStatusCode.valueOf(code))

fun success(message: String? = null) = responseOf(200, message)