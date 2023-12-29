package com.grantham.showplace.data

import com.grantham.showplace.Constants
import com.grantham.showplace.data.models.Band
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class BandService(
    private val client: HttpClient
) {

    @NativeCoroutines
    suspend fun getBand(id: Int): Band {
        return client.get(Constants.BAND) {
            parameter("id", id)
        }.body()
    }

    @NativeCoroutines
    suspend fun getBands(): List<Band> {
        return client.get(Constants.BANDS).body()
    }
}