package com.itzephir.whererubles.core.network.common

const val BASE_URL = "https://shmr-finance.ru/api/v1"

fun url(vararg urls: String): String {
    val urls = urls.fold(initial = "") { acc, string -> "$acc/$string" }
    return "$BASE_URL$urls".also {
        println("url: $it")
    }
}